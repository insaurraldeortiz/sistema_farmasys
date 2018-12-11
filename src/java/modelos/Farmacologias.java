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
public class Farmacologias {
    int id_farmacologia;
    String nombre_farmacologia;

    public Farmacologias() {
    }

    public Farmacologias(int id_farmacologia, String nombre_farmacologia) {
        this.id_farmacologia = id_farmacologia;
        this.nombre_farmacologia = nombre_farmacologia;
    }

    public int getId_farmacologia() {
        return id_farmacologia;
    }

    public void setId_farmacologia(int id_farmacologia) {
        this.id_farmacologia = id_farmacologia;
    }

    public String getNombre_farmacologia() {
        return nombre_farmacologia;
    }

    public void setNombre_farmacologia(String nombre_farmacologia) {
        this.nombre_farmacologia = nombre_farmacologia;
    }
}
