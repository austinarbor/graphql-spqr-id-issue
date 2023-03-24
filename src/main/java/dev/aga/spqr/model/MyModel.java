package dev.aga.spqr.model;

import io.leangen.graphql.annotations.GraphQLId;

public class MyModel {
    @GraphQLId
    public Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
