package org.vectorsearch.extractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PdfTextExtractorStrategy extends TextExtractorStrategy {

    /**
     * Extract content from PDF file. IOException is thrown in case of error during the extraction.
     *
     * @param   file            file to extract
     * @return                  file content
     * @throws  IOException     exception occurs when fail to read file
     * */
    @Override
    public String extract(MultipartFile file) throws IOException {
        String content;

        try (final PDDocument document = PDDocument.load(file.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            content = pdfStripper.getText(document);
        }

        return content;
    }
}
