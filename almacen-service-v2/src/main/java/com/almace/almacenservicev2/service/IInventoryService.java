package com.almace.almacenservicev2.service;

import com.almace.almacenservicev2.model.AlmacenModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInventoryService {

    /**
     * save producto nuevo
     * @param almacenModel
     * @return
     */
    public ResponseEntity<AlmacenModel> save(AlmacenModel almacenModel);

    /**
     * Traera una lista de  todos los productos que están guardados
     * @return
     */
    public ResponseEntity<List<AlmacenModel>>getAll();

    /**
     * Actualiza producto
     * @param almacenModel
     * @return
     */
    public ResponseEntity<List<AlmacenModel>> update(AlmacenModel almacenModel, Long id);

    /**
     * ELimina inventario
     */
    public void deleteInventory(Long id);

    /**
     * Busca por id
     * @param id
     * @return
     */
    ResponseEntity<List<AlmacenModel>>findById(Long id);

    /**
     * Busqueda por nombre de producto
     * @param name
     * @return json donde encontrara el nombre
     */
    ResponseEntity<List<AlmacenModel>>findByName(String name);

}
