/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


public class VentasDetalle {

    private int id_ventadetalle;
    private Ventas venta;
    private Articulos articulo;
    String lote_ventadetalle;
    private int cantidad_ventadetalle;

    public VentasDetalle() {
    }

    public VentasDetalle(int id_ventadetalle, Ventas venta, Articulos articulo, String lote_ventadetalle, int cantidad_ventadetalle) {
        this.id_ventadetalle = id_ventadetalle;
        this.venta = venta;
        this.articulo = articulo;
        this.lote_ventadetalle = lote_ventadetalle;
        this.cantidad_ventadetalle = cantidad_ventadetalle;
    }

    public int getId_ventadetalle() {
        return id_ventadetalle;
    }

    public void setId_ventadetalle(int id_ventadetalle) {
        this.id_ventadetalle = id_ventadetalle;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public String getLote_ventadetalle() {
        return lote_ventadetalle;
    }

    public void setLote_ventadetalle(String lote_ventadetalle) {
        this.lote_ventadetalle = lote_ventadetalle;
    }

    public int getCantidad_ventadetalle() {
        return cantidad_ventadetalle;
    }

    public void setCantidad_ventadetalle(int cantidad_ventadetalle) {
        this.cantidad_ventadetalle = cantidad_ventadetalle;
    }
}