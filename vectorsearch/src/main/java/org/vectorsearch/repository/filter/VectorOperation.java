package org.vectorsearch.repository.filter;

import lombok.Getter;

@Getter
public enum VectorOperation {

    AND("&&"),
    OR("||"),
    EQ("=="),
    NE("!="),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<="),
    IN("IN"),
    NIN("NIN"),
    NOT("NOT");

    private final String op;

    VectorOperation(String op) {
        this.op = op;
    }
}
