package com.almace.almacenservicev2.service;

import com.almace.almacenservicev2.model.AlmacenModel;
import com.almace.almacenservicev2.repository.IInventoryRepository;
import com.almace.almacenservicev2.util.Operaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InventoryImplementService implements IInventoryService{

    @Autowired
    private IInventoryRepository repository;

    /**
     * save producto nuevo
     *
     * @param almacenModel
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<AlmacenModel> save(AlmacenModel almacenModel) {
        Operaciones precio = new Operaciones();
        /**
         * calculamos precio unitario por cantidad
         */
        almacenModel.setValorTotalProductoAlmacen(precio.precioGralPorProducto(almacenModel.getPrecioUnitario(), almacenModel.getCantidadAlmacen()));

        //Guardamos
        AlmacenModel saveProduct = repository.save(almacenModel);

        //Obtenemos todos los productos
        List<AlmacenModel>todosModelos= repository.findAll();

        //calcula el precio de todo el almacen
        Double totalPrecioAlmacen = precio.precioTotalAlmacen(todosModelos);

        //guarda y actualiza el precioGral en todos los productos donde aparece la opcion
        for (AlmacenModel modelo: todosModelos){
            modelo.setPrecioGralAlmacen(totalPrecioAlmacen);
        }
        //Guarda todos los producttos
        repository.saveAll(todosModelos);

        return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
    }

    /**
     * Traera una lista de todos los productos que están guardados
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<AlmacenModel>> getAll() {
        List<AlmacenModel>list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Actualiza producto
     *
     * @param almacenModel
     * @return
     */
    @Override
    public ResponseEntity<List<AlmacenModel>> update(AlmacenModel almacenModel, Long id) {
        Optional<AlmacenModel> optionalAlmacenModel = repository.findById(id);
        List<AlmacenModel>list = new ArrayList<>();
        if (optionalAlmacenModel.isPresent()){
            optionalAlmacenModel.get().setFchIn(almacenModel.getFchIn());
            optionalAlmacenModel.get().setProductoAlmacen(almacenModel.getProductoAlmacen());
            optionalAlmacenModel.get().setProveedorAlmacen(almacenModel.getProveedorAlmacen());
            optionalAlmacenModel.get().setColor(almacenModel.getColor());
            optionalAlmacenModel.get().setObservaciones(almacenModel.getObservaciones());
            optionalAlmacenModel.get().setCantidadAlmacen(almacenModel.getCantidadAlmacen());
            optionalAlmacenModel.get().setPrecioUnitario(almacenModel.getPrecioUnitario());
            optionalAlmacenModel.get().setValorTotalProductoAlmacen(almacenModel.getValorTotalProductoAlmacen());
            optionalAlmacenModel.get().setPrecioGralAlmacen(almacenModel.getPrecioGralAlmacen());

            AlmacenModel modelToUpdate = repository.save(optionalAlmacenModel.get());
            if (modelToUpdate != null){
                list.add(modelToUpdate);
                return new ResponseEntity<>(list,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * ELimina inventario
     */
    @Override
    public void deleteInventory(Long id) {
        repository.deleteById(id);
    }

    /**
     * Busca por id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<AlmacenModel>> findById(Long id) {
        Optional<AlmacenModel> find = repository.findById(id);
        List<AlmacenModel> list = new ArrayList<>();
        if (find.isPresent()){
            list.add(find.get());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
       else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Busqueda por nombre de producto
     * @param name
     * @return json donde encontrara el nombre
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<AlmacenModel>> findByName(String name) {
       List<AlmacenModel> list = repository.findByProductoAlmacen(name);
       if (!list.isEmpty()){
           return new ResponseEntity<>(list,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    /**
     * ELimina TODO el inventario
     */
    @Override
    public void deleteAllInventory() {
        repository.deleteAll();
    }

}
