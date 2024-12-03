package org.vectorsearch.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VectorFilter {

    private VectorOperation type;
    private String key;
    private Object value;

    public String toString() {
        return key + type.getOp() + value;
    }
}
