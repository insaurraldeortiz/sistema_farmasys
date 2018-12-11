/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ADMINISTRADOR
 */
public class DevolucionesDetalle {
    int id_devoluciondetalle;
    Devoluciones devolucion;
    Articulos articulo;
    String lote_devolucion;
    int cantidad_devolucion;

    public DevolucionesDetalle(int id_devoluciondetalle, Devoluciones devolucion, Articulos articulo, String lote_devolucion, int cantidad_devolucion) {
        this.id_devoluciondetalle = id_devoluciondetalle;
        this.devolucion = devolucion;
        this.articulo = articulo;
        this.lote_devolucion = lote_devolucion;
        this.cantidad_devolucion = cantidad_devolucion;
    }

    public DevolucionesDetalle() {
    }

    public int getId_devoluciondetalle() {
        return id_devoluciondetalle;
    }

    public void setId_devoluciondetalle(int id_devoluciondetalle) {
        this.id_devoluciondetalle = id_devoluciondetalle;
    }

    public Devoluciones getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Devoluciones devolucion) {
        this.devolucion = devolucion;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public String getLote_devolucion() {
        return lote_devolucion;
    }

    public void setLote_devolucion(String lote_devolucion) {
        this.lote_devolucion = lote_devolucion;
    }

    public int getCantidad_devolucion() {
        return cantidad_devolucion;
    }

    public void setCantidad_devolucion(int cantidad_devolucion) {
        this.cantidad_devolucion = cantidad_devolucion;
    }
}
