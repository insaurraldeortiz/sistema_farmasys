/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author ADMINISTRADOR
 */
public class Recetas {
    int id_receta;
    String descripcion_receta;
    Date fecha_receta;
    Date caducidad_receta;
    Medicos medico;

    public Recetas() {
    }

    public Recetas(int id_receta, String descripcion_receta, Date fecha_receta, Date caducidad_receta, Medicos medico) {
        this.id_receta = id_receta;
        this.descripcion_receta = descripcion_receta;
        this.fecha_receta = fecha_receta;
        this.caducidad_receta = caducidad_receta;
        this.medico = medico;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public String getDescripcion_receta() {
        return descripcion_receta;
    }

    public void setDescripcion_receta(String descripcion_receta) {
        this.descripcion_receta = descripcion_receta;
    }

    public Date getFecha_receta() {
        return fecha_receta;
    }

    public void setFecha_receta(Date fecha_receta) {
        this.fecha_receta = fecha_receta;
    }

    public Date getCaducidad_receta() {
        return caducidad_receta;
    }

    public void setCaducidad_receta(Date caducidad_receta) {
        this.caducidad_receta = caducidad_receta;
    }

    public Medicos getMedico() {
        return medico;
    }

    public void setMedico(Medicos medico) {
        this.medico = medico;
    }
}    