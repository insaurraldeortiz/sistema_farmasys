package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Presentaciones;
import utiles.Conexion;
import utiles.Utiles;

public class PresentacionesControlador {
    public static boolean agregar(Presentaciones presentacion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into presentaciones (nombre_presentacion)" 
                    + "values ('" + presentacion.getNombre_presentacion() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(PresentacionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Presentaciones presentacion){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update presentaciones set nombre_presentacion='" + presentacion.getNombre_presentacion() + "'"
                    + " where id_presentacion=" + presentacion.getId_presentacion();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(PresentacionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Presentaciones presentacion){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from presentaciones where id_presentacion=" + presentacion.getId_presentacion();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(PresentacionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Presentaciones buscarId(Presentaciones presentacion) {
        if (Conexion.conectar()){
            String sql = "select * from presentaciones where id_presentacion ='"+presentacion.getId_presentacion()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    presentacion.setId_presentacion(rs.getInt("id_presentacion"));
                    presentacion.setNombre_presentacion(rs.getString("nombre_presentacion"));
                } else {
                    presentacion.setId_presentacion(0);
                    presentacion.setNombre_presentacion("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return presentacion;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from presentaciones where upper(nombre_presentacion) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_presentacion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_presentacion") + "</td>"
                                + "<td>" + rs.getString("nombre_presentacion") + "</td>"
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
