package org.vectorsearch.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiModelSupplier {

    @Value("${ollama.baseurl}")
    private String ollamaBaseUrl;

    @Bean
    @Primary
    public EmbeddingModel embeddingModel() {
        OllamaOptions ollamaOptions = OllamaOptions.builder()
                .withModel(OllamaModel.NOMIC_EMBED_TEXT.id())
                .build();

        return OllamaEmbeddingModel.builder()
                .withOllamaApi(new OllamaApi(this.ollamaBaseUrl))
                .withDefaultOptions(ollamaOptions)
                .build();
    }

    @Bean
    @Primary
    public ChatModel chatModel() {
        OllamaOptions ollamaOptions = OllamaOptions.builder()
                .withModel(OllamaModel.ORCA_MINI.id())
                .build();

        return OllamaChatModel.builder()
                .withOllamaApi(new OllamaApi(this.ollamaBaseUrl))
                .withDefaultOptions(ollamaOptions)
                .build();
    }

}
