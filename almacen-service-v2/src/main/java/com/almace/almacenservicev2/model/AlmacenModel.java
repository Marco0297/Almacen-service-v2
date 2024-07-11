package com.almace.almacenservicev2.model;

import com.almace.almacenservicev2.constants.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.almace.almacenservicev2.constants.Constants.PRECIO_GENERAL_ALMACEN;

@Entity
@Data
@Table(name =Constants.NOMBRE_TABLA)
public class AlmacenModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Fecha de ingreso de los producto
     */
    @Column(name=Constants.FECHA_INICIO)
    private Date fchIn;

    /**
     * Nombre del producto
     */
    @Column(name=Constants.PRODUCTO_ALMACEN)
    private String productoAlmacen;

    /**
     * Nombre del proveedor
     */
   @Column(name=Constants.PROOVEDOR_ALMACEN)
    private String proveedorAlmacen;

    /**
     * Color del producto (caracteristicas)
     */
    @Column(name=Constants.COLOR)
    private String color;

    /**
     * Observaciones del producto
     */
    @Column(name=Constants.OBSERVACIONES)
    private String observaciones;

    /**
     * Caracteristicas del producto
     */
    @Column(name=Constants.CARACTERISTICAS)
    private String caracteristicasProducto;

    /**
     * Cantidad del producto que hay en almacen
     */
    @Column(name=Constants.CANTIDAD_ALMACEN)
    private Integer cantidadAlmacen;

    /**
     * Precio del producto por pieza
     */
    @Column(name=Constants.PRECIO_UNITARIO)
    private Double precioUnitario;

    /**
     * Valor total por cada producto que se encuentra en alacen
     */
    @Column(name=Constants.VALOR_TOTAL_PRODUCTOS_ALMACEN)
    private Double valorTotalProductoAlmacen;

    /**
     * Precio de todos los productos en general dentro del almacen
     */
    @Column(name=PRECIO_GENERAL_ALMACEN)
    private Double precioGralAlmacen;

}
