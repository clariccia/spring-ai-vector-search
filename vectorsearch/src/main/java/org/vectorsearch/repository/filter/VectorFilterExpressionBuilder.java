package org.vectorsearch.repository.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VectorFilterExpressionBuilder {

    private final List<VectorFilter> filters = new ArrayList<>();

    private VectorFilterExpressionBuilder() {}

    public static VectorFilterExpressionBuilder builder() {
        return new VectorFilterExpressionBuilder();
    }

    public String build() {
        return this.filters.stream()
                .map(VectorFilter::toString)
                .collect(Collectors.joining(VectorOperation.AND.getOp()));
    }

    public VectorFilterExpressionBuilder with(VectorFilter vectorFilter) {
        if (vectorFilter.getKey() != null && vectorFilter.getValue() != null) this.filters.add(vectorFilter);

        return this;
    }
}
