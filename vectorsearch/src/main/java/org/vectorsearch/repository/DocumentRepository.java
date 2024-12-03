package org.vectorsearch.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Repository;
import org.vectorsearch.entity.DocumentEntity;
import org.vectorsearch.repository.filter.VectorFilter;
import org.vectorsearch.repository.filter.VectorFilterExpressionBuilder;
import org.vectorsearch.repository.filter.VectorOperation;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class DocumentRepository {

    private final VectorStore vectorStore;

    public void addDocument(DocumentEntity documentEntity) {
        this.addDocuments(List.of(documentEntity));
    }

    public void addDocuments(List<DocumentEntity> documentEntities) {
        this.vectorStore.add(documentEntities
                .stream()
                .map(DocumentEntity::toDocument)
                .toList()
        );
    }

    public List<DocumentEntity> findBySimilarity(DocumentEntity documentEntity) {
        SearchRequest searchRequest = SearchRequest.query(documentEntity.getContent()).withTopK(5);

        VectorFilter authorEqFilter = new VectorFilter(VectorOperation.EQ, DocumentEntity.AUTHOR_FIELD, documentEntity.getAuthor());
        VectorFilter publicationDateGteFilter = new VectorFilter(VectorOperation.GTE, DocumentEntity.PUBLICATION_DATE_FIELD, documentEntity.getPublicationDate());

        String filterExpression = VectorFilterExpressionBuilder.builder()
                .with(authorEqFilter)
                .with(publicationDateGteFilter)
                .build();

        if (StringUtils.isNotEmpty(filterExpression)) searchRequest.withFilterExpression(filterExpression);

        return this.findBySimilarity(searchRequest);
    }

    public List<DocumentEntity> findBySimilarity(SearchRequest searchRequest) {

        return this.vectorStore.similaritySearch(searchRequest).stream()
                .map(DocumentEntity::fromDocument)
                .toList();
    }
}
