package org.vectorsearch.service;

import org.springframework.web.multipart.MultipartFile;
import org.vectorsearch.dto.request.DocumentDTORequest;
import org.vectorsearch.dto.request.DocumentSearchDTORequest;
import org.vectorsearch.dto.response.DocumentDTOResponse;
import org.vectorsearch.dto.response.PromptDTOResponse;

import java.util.List;

public interface DocumentService {

    void addDocument(DocumentDTORequest documentDTORequest, MultipartFile file);
    List<DocumentDTOResponse> findDocumentBySimilarity(DocumentSearchDTORequest documentSearchDTORequest);
    PromptDTOResponse prompt(DocumentSearchDTORequest documentSearchDTORequest);
}
