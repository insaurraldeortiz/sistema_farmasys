/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author FABIAN
 */
public class Composiciones {
    int id_composicion;
    Articulos articulo;
    Sustancias sustancia;
    String cantidadsustancia_composicion;

    public Composiciones() {
    }

    public Composiciones(int id_composicion, Articulos articulo, Sustancias sustancia, String cantidadsustancia_composicion) {
        this.id_composicion = id_composicion;
        this.articulo = articulo;
        this.sustancia = sustancia;
        this.cantidadsustancia_composicion = cantidadsustancia_composicion;
    }

    public int getId_composicion() {
        return id_composicion;
    }

    public void setId_composicion(int id_composicion) {
        this.id_composicion = id_composicion;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public Sustancias getSustancia() {
        return sustancia;
    }

    public void setSustancia(Sustancias sustancia) {
        this.sustancia = sustancia;
    }

    public String getCantidadsustancia_composicion() {
        return cantidadsustancia_composicion;
    }

    public void setCantidadsustancia_composicion(String cantidadsustancia_composicion) {
        this.cantidadsustancia_composicion = cantidadsustancia_composicion;
    }
}
