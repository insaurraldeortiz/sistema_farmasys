/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author FABIAN
 */
public class ComprasDetalle {
    int id_compradetalle;
    Compras compra;
    String lote_compradetalle;
    Articulos articulo;
    int cantidad_compradetalle;

    public ComprasDetalle() {
    }

    public ComprasDetalle(int id_compradetalle, Compras compra, String lote_compradetalle, Articulos articulo, int cantidad_compradetalle) {
        this.id_compradetalle = id_compradetalle;
        this.compra = compra;
        this.lote_compradetalle = lote_compradetalle;
        this.articulo = articulo;
        this.cantidad_compradetalle = cantidad_compradetalle;
    }

    public int getId_compradetalle() {
        return id_compradetalle;
    }

    public void setId_compradetalle(int id_compradetalle) {
        this.id_compradetalle = id_compradetalle;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public String getLote_compradetalle() {
        return lote_compradetalle;
    }

    public void setLote_compradetalle(String lote_compradetalle) {
        this.lote_compradetalle = lote_compradetalle;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public int getCantidad_compradetalle() {
        return cantidad_compradetalle;
    }

    public void setCantidad_compradetalle(int cantidad_compradetalle) {
        this.cantidad_compradetalle = cantidad_compradetalle;
    }
}
