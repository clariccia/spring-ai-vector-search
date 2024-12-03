package org.vectorsearch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTOResponse {

    private String id;
    private String title;
    private String author;
    private Instant publicationDate;
}
