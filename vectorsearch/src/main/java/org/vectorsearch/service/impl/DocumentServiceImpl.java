package org.vectorsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vectorsearch.dto.request.DocumentDTORequest;
import org.vectorsearch.dto.request.DocumentSearchDTORequest;
import org.vectorsearch.dto.response.DocumentDTOResponse;
import org.vectorsearch.dto.response.PromptDTOResponse;
import org.vectorsearch.entity.DocumentEntity;
import org.vectorsearch.exception.BadRequestException;
import org.vectorsearch.extractor.PdfTextExtractorStrategy;
import org.vectorsearch.extractor.TextExtractorStrategy;
import org.vectorsearch.repository.DocumentRepository;
import org.vectorsearch.repository.PromptRepository;
import org.vectorsearch.service.DocumentService;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final PromptRepository promptRepository;

    @Override
    public void addDocument(DocumentDTORequest documentDTORequest, MultipartFile file) {
        if (file.isEmpty()) throw new BadRequestException("Cannot process empty documents");

        TextExtractorStrategy textExtractorStrategy = new PdfTextExtractorStrategy();
        String content;

        try {
            content = textExtractorStrategy.extract(file);
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage(), e);
        }

        DocumentEntity documentEntity = DocumentEntity.builder()
                .author(documentDTORequest.getAuthor())
                .title(documentDTORequest.getTitle())
                .publicationDate(documentDTORequest.getPublicationDate())
                .content(content)
                .build();

        this.documentRepository.addDocument(documentEntity);
    }

    @Override
    public List<DocumentDTOResponse> findDocumentBySimilarity(DocumentSearchDTORequest documentSearchDTORequest) {
        List<DocumentEntity> documents = this.findDocuments(documentSearchDTORequest);

        return documents.stream()
                .map(d -> DocumentDTOResponse.builder()
                        .id(d.getId())
                        .title(d.getTitle())
                        .author(d.getAuthor())
                        .publicationDate(d.getPublicationDate())
                        .build())
                .toList();
    }

    @Override
    public PromptDTOResponse prompt(DocumentSearchDTORequest documentSearchDTORequest) {
        String promptMessage = "Using these information %s the question is: %s";

        String documentContents = this.findDocuments(documentSearchDTORequest)
                .stream()
                .map(DocumentEntity::getContent)
                .collect(Collectors.joining());

        ChatResponse chatResponse = this.promptRepository.prompt(
                String.format(promptMessage, documentContents, documentSearchDTORequest.getPrompt())
        );

        String response = chatResponse.getResults()
                .stream()
                .map(generation -> generation.getOutput().getContent())
                .collect(Collectors.joining());

        return PromptDTOResponse.builder()
                .response(response)
                .prompt(documentSearchDTORequest.getPrompt())
                .timestamp(Instant.now())
                .build();
    }

    private List<DocumentEntity> findDocuments(DocumentSearchDTORequest documentSearchDTORequest) {
        DocumentEntity documentEntity = DocumentEntity.builder()
                .content(documentSearchDTORequest.getPrompt())
                .author(documentSearchDTORequest.getAuthor())
                .publicationDate(documentSearchDTORequest.getFrom())
                .build();

        return this.documentRepository.findBySimilarity(documentEntity);
    }
}
