/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Presentaciones {
    int id_presentacion;
    String nombre_presentacion;

    public Presentaciones() {
    }

    public Presentaciones(int id_presentacion, String nombre_presentacion) {
        this.id_presentacion = id_presentacion;
        this.nombre_presentacion = nombre_presentacion;
    }

    public int getId_presentacion() {
        return id_presentacion;
    }

    public void setId_presentacion(int id_presentacion) {
        this.id_presentacion = id_presentacion;
    }

    public String getNombre_presentacion() {
        return nombre_presentacion;
    }

    public void setNombre_presentacion(String nombre_presentacion) {
        this.nombre_presentacion = nombre_presentacion;
    }
    
    
}
