package dev.aga.spqr.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLContext;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class GraphQLController {

    private final GraphQL graphQL;

    public GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @RequestMapping("queries")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> graphQlQuery(@RequestBody Map<String, Object> request,
                                            HttpServletRequest req) {
        ExecutionResult result = executeGraphQl(request, GraphQLContext.newContext().build());
        return result.toSpecification();
    }

    private ExecutionResult executeGraphQl(Map<String, Object> request, GraphQLContext context) {
        return graphQL.execute(
            ExecutionInput.newExecutionInput()
                .query((String) request.get("query"))
                .operationName((String) request.get("operationName"))
                .variables(
                    Optional.ofNullable((Map<String, Object>) request.get("variables")).orElse(
                        Collections.emptyMap()))
                .context(context)
                .build());
    }
}
