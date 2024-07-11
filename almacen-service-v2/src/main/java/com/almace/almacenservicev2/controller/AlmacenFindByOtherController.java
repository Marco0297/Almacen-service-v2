/**
 * Endpoint sin el uso de jpaRepository
 */
package com.almace.almacenservicev2.controller;

import com.almace.almacenservicev2.model.AlmacenModel;
import com.almace.almacenservicev2.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api.v2/almace-service")
public class AlmacenFindByOtherController {

    @Autowired
    private IInventoryService service;

    /**
     * Endpoit que controla la peticion para la busqueda por nombre de producto
     * @param name
     * @return
     */
    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<List<AlmacenModel>> findByNameProduct(@PathVariable String name){
        return service.findByName(name);
    }
}
