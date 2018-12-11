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
public class Sustancias {
    int id_sustancia;
    String nombre_sustancia;

    public Sustancias() {
    }

    public Sustancias(int id_sustancia, String nombre_sustancia) {
        this.id_sustancia = id_sustancia;
        this.nombre_sustancia = nombre_sustancia;
    }

    public int getId_sustancia() {
        return id_sustancia;
    }

    public void setId_sustancia(int id_sustancia) {
        this.id_sustancia = id_sustancia;
    }

    public String getNombre_sustancia() {
        return nombre_sustancia;
    }

    public void setNombre_sustancia(String nombre_sustancia) {
        this.nombre_sustancia = nombre_sustancia;
    }
    
    
}
