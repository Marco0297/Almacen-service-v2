/**
 * Los Endpoints presentados en esta clase son con el uso de JPA Repository
 */
package com.almace.almacenservicev2.controller;

import com.almace.almacenservicev2.model.AlmacenModel;
import com.almace.almacenservicev2.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/almace-service")
public class AlmacenController {

    @Autowired
    private IInventoryService service;

    /**
     * Endpoint para guardar nuevos Productos
     * @param almacenModel
     * @return
     */
    @PostMapping("/saveProduct")
    public ResponseEntity saveProduct(@RequestBody AlmacenModel almacenModel){
         return service.save(almacenModel);
    }

    /**
     * Endopoint para mostrar todos los productos
     * @return
     */
    @GetMapping("/getProducts")
    public ResponseEntity<List<AlmacenModel>> showAll(){
        return service.getAll();
    }

    /**
     * Actualiza algun model por id
     * @param model
     * @param id
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<List<AlmacenModel>>updateModel(@RequestBody AlmacenModel model, @PathVariable Long id){
        ResponseEntity<List<AlmacenModel>> response = service.update(model, id);
        return response;
    }

    /**
     * Elimina producto por id
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteInventory(id);
    }

    /**
     * Busca por id
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public ResponseEntity<List<AlmacenModel>> findById(@PathVariable Long id){
        return service.findById(id);
    }
}
