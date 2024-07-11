package com.almace.almacenservicev2.repository;

import com.almace.almacenservicev2.model.AlmacenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IInventoryRepository extends JpaRepository<AlmacenModel, Long> {
    /**
     * Busqueda por nombre de producto
     * @param productName
     * @return devuelce una lista de los objectos encontrados
     */
    List<AlmacenModel> findByProductoAlmacen(String productName);
}
