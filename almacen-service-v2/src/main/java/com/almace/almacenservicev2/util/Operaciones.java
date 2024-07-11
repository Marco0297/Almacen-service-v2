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

    /**
     * Determina el precio de todos los productos guardados en Almacen
     * @param modelos
     */
    public Double precioTotalAlmacen(List<AlmacenModel> modelos){

        Double totalProductosAlmacen = 0.0;
        for (int i = 0; i<modelos.size(); i++){
            Double precio = modelos.get(i).getValorTotalProductoAlmacen();
            if (precio != null){
                totalProductosAlmacen += precio;
            }
        }
            return totalProductosAlmacen;
    }
}
