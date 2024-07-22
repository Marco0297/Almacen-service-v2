package com.almace.almacenservicev2.service;

import com.almace.almacenservicev2.model.AlmacenModel;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;

@Service
public class InventoryDowloandPdfImplementService implements IInventoryDowloandPdfService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final float PAGE_WIDTH = 842; // A4 width in points
    private static final float PAGE_HEIGHT = 595; // A4 height in points
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
            //PDPage page = new PDPage();//HOJA VERTICAL
            PDPage page = new PDPage(new PDRectangle(PAGE_WIDTH, PAGE_HEIGHT));//HOJA HORIZONTAL
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                //HOJA VERTICAL
                /* float yStart = 750f;
                float tableWidth = 500f;
                float yPosition = yStart;
                float rowHeight = 20f;
                float tableHeight = rowHeight * (products.size() + 1); // Add 1 for header row
                float cellMargin = 5f;

                */
                //HOJA VERTICAL
                float yStart = PAGE_HEIGHT - 50; // Adjust the start position for the header
                float tableWidth = PAGE_WIDTH - 50; // Adjust the table width
                float yPosition = yStart;
                float rowHeight = 20f;
                float tableHeight = rowHeight * (products.size() + 1); // Add 1 for header row
                float cellMargin = 5f;

                // Draw table header
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(25, yPosition);
                contentStream.showText("Producto");
                contentStream.newLineAtOffset(75, 0); // Adjust position for next column
                contentStream.showText("Fecha de Ingreso");
                contentStream.newLineAtOffset(125, 0); // Adjust position for next column
                contentStream.showText("Proveedor");
                contentStream.newLineAtOffset(80, 0); // Adjust position for next column
                contentStream.showText("Cantidad en Almacen");
                contentStream.newLineAtOffset(140, 0); // Adjust position for next column
                contentStream.showText("Precio Unitario");
                contentStream.newLineAtOffset(150, 0); // Adjust position for next column
                contentStream.showText("Precio total");


                contentStream.endText();

                // Draw table rows
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                yPosition -= rowHeight; // Move to next row position

                for (AlmacenModel product : products) {
                    /**
                     * Nombre del producto
                     */
                    contentStream.beginText();
                    contentStream.newLineAtOffset(25, yPosition);
                    contentStream.showText(product.getProductoAlmacen());
                    /**
                     * Fecha de Ingreso a almacen
                     */

                    String formattedDate = DATE_FORMAT.format(product.getFchIn());
                    contentStream.newLineAtOffset(75, 0); // Adjust position for next column
                    contentStream.showText(formattedDate);
                    /**
                     * Nombre del Proveedor
                     */
                    contentStream.newLineAtOffset(125,0);
                    contentStream.showText(product.getProveedorAlmacen());
                    /**
                     * Cantidad en almacen
                     */
                    contentStream.newLineAtOffset(100,0);
                    contentStream.showText(String.valueOf(product.getCantidadAlmacen()));
                    /**
                     *Precio Unitario
                     */
                    contentStream.newLineAtOffset(140,0);
                    contentStream.showText(String.valueOf(product.getPrecioUnitario()));
                    /**
                     *Precio tota por producto
                     */
                    contentStream.newLineAtOffset(150,0);
                    contentStream.showText(String.valueOf(product.getValorTotalProductoAlmacen()));

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

