package org.vectorsearch.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.ai.document.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@Data
@Builder
public class DocumentEntity extends AbstractBaseEntity<String> {

    public static final String TITLE_FIELD = "title";
    public static final String AUTHOR_FIELD = "author";
    public static final String PUBLICATION_DATE_FIELD = "publicationDate";

    @Field
    private String title;

    @Field
    private String author;

    @Field
    private Instant publicationDate;

    private String content;

    @Override
    public Document toDocument() {
        Map<String, Object> fieldMap = Map.of(
                TITLE_FIELD, this.title,
                AUTHOR_FIELD, this.author,
                PUBLICATION_DATE_FIELD, this.publicationDate
        );

        return new Document(this.content, fieldMap);
    }

    public static DocumentEntity fromDocument(Document document) {
        return DocumentEntity.builder()
                .title((String) document.getMetadata().get(DocumentEntity.TITLE_FIELD))
                .author((String) document.getMetadata().get(DocumentEntity.AUTHOR_FIELD))
                .publicationDate(((Date) document.getMetadata().get(DocumentEntity.PUBLICATION_DATE_FIELD)).toInstant())
                .content(document.getContent())
                .build();
    }

}
