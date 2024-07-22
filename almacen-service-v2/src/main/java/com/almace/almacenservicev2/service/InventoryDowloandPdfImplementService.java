package com.almace.almacenservicev2.service;

import com.almace.almacenservicev2.model.AlmacenModel;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class InventoryDowloandPdfImplementService implements IInventoryDowloandPdfService {
    /**
     * Genera PDF de los productos
     *
     * @param products
     * @return
     */
    @Override
    public ByteArrayInputStream generatePdf(List<AlmacenModel> products) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float yStart = 750f;
                float tableWidth = 500f;
                float yPosition = yStart;
                float rowHeight = 20f;
                float tableHeight = rowHeight * (products.size() + 1); // Add 1 for header row
                float cellMargin = 5f;

                // Draw table header
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(25, yPosition);
                contentStream.showText("ID");
                contentStream.newLineAtOffset(100, 0); // Adjust position for next column
                contentStream.showText("Nombre");
                contentStream.newLineAtOffset(200, 0); // Adjust position for next column
                contentStream.showText("Precio");
                contentStream.endText();

                // Draw table rows
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                yPosition -= rowHeight; // Move to next row position

                for (AlmacenModel product : products) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(25, yPosition);
                    contentStream.showText(String.valueOf(product.getId()));
                    contentStream.newLineAtOffset(100, 0); // Adjust position for next column
                    contentStream.showText(product.getProductoAlmacen());
                    contentStream.newLineAtOffset(200, 0); // Adjust position for next column
                    contentStream.showText(String.valueOf(product.getPrecioGralAlmacen()));
                    contentStream.endText();
                    yPosition -= rowHeight; // Move to next row position
                }
            }

            document.save(out);
        } catch (IOException e) {
            e.printStackTrace();
            return new ByteArrayInputStream("Error generating PDF".getBytes());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}

