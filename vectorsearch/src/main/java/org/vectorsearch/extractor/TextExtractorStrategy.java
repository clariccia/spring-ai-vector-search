package org.vectorsearch.extractor;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public abstract class TextExtractorStrategy {

    public abstract String extract(MultipartFile file) throws IOException;
}
