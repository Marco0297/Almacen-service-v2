package com.almace.almacenservicev2.util;

import com.almace.almacenservicev2.model.AlmacenModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encargara de las operaciones matematicas
 */
public class Operaciones {

    /**
     * Determina el precio en total por cada cantidad de producto
     * @param precioU
     * @param cantidad
     * @return
     */
    public Double precioGralPorProducto(Double precioU, Integer cantidad){
        Double precio = precioU * cantidad;
        return precio;
    }


    public void precioTotalAlmacen(AlmacenModel modelos){
        List<AlmacenModel> list = new ArrayList<>();
        list.add(modelos);
        for (int i = 0; i<list.size(); i++){

        }

    }
}
