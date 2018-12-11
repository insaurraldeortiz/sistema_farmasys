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
public class Medicos {
    int id_medico;
    String nombre_medico;

    public Medicos() {
    }

    public Medicos(int id_medico, String nombre_medico) {
        this.id_medico = id_medico;
        this.nombre_medico = nombre_medico;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombre_medico() {
        return nombre_medico;
    }

    public void setNombre_medico(String nombre_medico) {
        this.nombre_medico = nombre_medico;
    }
    
    
}
