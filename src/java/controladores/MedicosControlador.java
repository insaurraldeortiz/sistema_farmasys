/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Medicos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author alumno
 */
public class MedicosControlador {
    public static boolean agregar(Medicos medico){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into medicos (nombre_medico)" 
                    + "values ('" + medico.getNombre_medico() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(MedicosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Medicos medico){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update medicos set nombre_medico='" + medico.getNombre_medico() + "'"
                    + " where id_medico=" + medico.getId_medico();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(MedicosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Medicos medico){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from medicos where id_medico=" + medico.getId_medico();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(MedicosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Medicos buscarId(Medicos medico) {
        if (Conexion.conectar()){
            String sql = "select * from medicos where id_medico ='"+medico.getId_medico()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    medico.setId_medico(rs.getInt("id_medico"));
                    medico.setNombre_medico(rs.getString("nombre_medico"));
                } else {
                    medico.setId_medico(0);
                    medico.setNombre_medico("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return medico;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from medicos where upper(nombre_medico) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_medico offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_medico") + "</td>"
                                + "<td>" + rs.getString("nombre_medico") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
