package dev.aga.spqr.service;

import org.springframework.stereotype.Service;

import dev.aga.spqr.model.MyModel;
import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class Queries {

    @GraphQLQuery
    public MyModel myModel(@GraphQLId @GraphQLNonNull Integer id) {
        var model = new MyModel();
        model.setId(id);
        return model;
    }
}
