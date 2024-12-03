package org.vectorsearch.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTORequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotNull
    private Instant publicationDate;
}
