package org.vectorsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vectorsearch.dto.request.DocumentDTORequest;
import org.vectorsearch.dto.request.DocumentSearchDTORequest;
import org.vectorsearch.dto.response.PromptDTOResponse;
import org.vectorsearch.service.DocumentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public PromptDTOResponse prompt(@Valid DocumentSearchDTORequest documentSearchDTORequest) {
        return this.documentService.prompt(documentSearchDTORequest);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addDocument(
            @Valid @NotNull @RequestPart("fileMetadata") DocumentDTORequest documentDTORequest,
            @Valid @NotNull @RequestPart("file") MultipartFile file) {

        this.documentService.addDocument(documentDTORequest, file);
    }
}
