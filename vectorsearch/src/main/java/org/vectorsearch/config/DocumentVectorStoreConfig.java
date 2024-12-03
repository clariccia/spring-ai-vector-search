package org.vectorsearch.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.MongoDBAtlasVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DocumentVectorStoreConfig {

    @Value("${mongodb.atlas.initialize-schema}")
    private Boolean initSchema;

    private static final String COLLECTION_NAME = "documents";
    private static final String INDEX_NAME = "document_vector_index";
    private static final String EMBEDDING_FIELD = "embedding";

    @Bean
    public MongoDBAtlasVectorStore vectorStore(MongoTemplate mongoTemplate, EmbeddingModel embeddingModel) {
        MongoDBAtlasVectorStore.MongoDBVectorStoreConfig config = MongoDBAtlasVectorStore.MongoDBVectorStoreConfig.builder()
                .withCollectionName(COLLECTION_NAME)
                .withVectorIndexName(INDEX_NAME)
                .withPathName(EMBEDDING_FIELD)
                .build();

        return new MongoDBAtlasVectorStore(mongoTemplate, embeddingModel, config, this.initSchema);
    }
}
