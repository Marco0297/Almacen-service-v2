/**
 * Endpoint sin el uso de jpaRepository
 */
package com.almace.almacenservicev2.controller;

import com.almace.almacenservicev2.model.AlmacenModel;
import com.almace.almacenservicev2.service.IInventoryDowloandPdfService;
import com.almace.almacenservicev2.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api.v2/almace-service")
public class AlmacenFindByOtherController {

    @Autowired
    private IInventoryService service;

    @Autowired
    private IInventoryDowloandPdfService pdfService;

    /**
     * Endpoit que controla la peticion para la busqueda por nombre de producto
     * @param name
     * @return
     */
    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<List<AlmacenModel>> findByNameProduct(@PathVariable String name){
        return service.findByName(name);
    }

    @GetMapping("/exportToPdf")
    public ResponseEntity<byte[]>exportToPdf(){
        List<AlmacenModel> products = service.getAll().getBody();
        ByteArrayInputStream pdfStream = pdfService.generatePdf(products);

        if (pdfStream == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar PDF".getBytes());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=productos.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }
}
