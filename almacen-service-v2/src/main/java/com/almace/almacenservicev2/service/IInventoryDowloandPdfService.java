package com.almace.almacenservicev2.service;

import com.almace.almacenservicev2.model.AlmacenModel;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface IInventoryDowloandPdfService {
    /**
     * Genera PDF de los productos
     * @param products
     * @return
     */
    public ByteArrayInputStream generatePdf(List<AlmacenModel> products);
}
