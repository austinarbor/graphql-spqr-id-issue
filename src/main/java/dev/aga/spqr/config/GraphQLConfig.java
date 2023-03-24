package dev.aga.spqr.config;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aga.spqr.service.Queries;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@Configuration
public class GraphQLConfig {

    private final Queries queries;

    public GraphQLConfig(Queries queries) {
        this.queries = queries;
    }

    @Bean
    GraphQLSchema graphQLSchema() {
        return new GraphQLSchemaGenerator()
            .withOperationsFromSingleton(queries, AopUtils.getTargetClass(queries))
            .withBasePackages("dev.aga.spqr")
            .withValueMapperFactory(
                JacksonValueMapperFactory.builder().withPrototype(new ObjectMapper()).build()
            ).generate();
    }

    @Bean
    GraphQL graphQL(GraphQLSchema schema) {
        return new GraphQL.Builder(schema).build();
    }
}
