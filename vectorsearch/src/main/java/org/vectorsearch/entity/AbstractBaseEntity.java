package org.vectorsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ai.document.Document;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public abstract class AbstractBaseEntity<ID> {

    public static final String ID_FIELD = "_id";

    @Id
    protected ID id;

    public abstract Document toDocument();
}
