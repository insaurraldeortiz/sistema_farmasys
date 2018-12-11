/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Medicos;
import modelos.Recetas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ADMINISTRADOR
 */
public class RecetasControlador {

    public static boolean agregar(Recetas receta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into recetas (descripcion_receta, fecha_receta, caducidad_receta, id_medico)"
                    + "values ('" + receta.getDescripcion_receta() + "','"
                    + receta.getFecha_receta() + "','"
                    + receta.getCaducidad_receta() + "','"
                    + receta.getMedico().getId_medico() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);

                valor = true;

            } catch (SQLException ex) {
                System.out.println("ERROR--->" + ex);
            }
        }

        return valor;

    }

    public static boolean modificar(Recetas receta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update recetas set descripcion_receta='" + receta.getDescripcion_receta() + "',"
                    + "fecha_receta='" + receta.getFecha_receta() + "',"
                    + "caducidad_receta='" + receta.getCaducidad_receta() + "', "
                    + "id_medico= " + receta.getMedico().getId_medico()
                    + " where id_receta=" + receta.getId_receta();

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;

            } catch (SQLException ex) {
                System.out.println("ERROR--->" + ex);
            }
        }

        return valor;

    }

    public static boolean eliminar(Recetas receta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from recetas where id_receta=" + receta.getId_receta();

            try {
                Conexion.getSt().executeUpdate(sql);

                valor = true;

            } catch (SQLException ex) {
                System.out.println("ERROR--->" + ex);
            }
        }

        return valor;

    }

    public static Recetas buscarId(Recetas receta) {
        if (Conexion.conectar()) {
            String sql = "select * from recetas r join medicos m on r.id_medico = m.id_medico where r.id_receta =" + receta.getId_receta() + " ";
            System.out.println("SQL---->"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Medicos medico = new Medicos();

                    receta.setId_receta(rs.getInt("id_receta"));
                    receta.setDescripcion_receta(rs.getString("descripcion_receta"));
                    receta.setFecha_receta(rs.getDate("fecha_receta"));
                    receta.setCaducidad_receta(rs.getDate("caducidad_receta"));

                    medico.setId_medico(rs.getInt("id_medico"));
                    medico.setNombre_medico(rs.getString("nombre_medico"));
                    
                    receta.setMedico(medico);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR--->" + ex);
            }
        }
        return receta;
    }

    public static String buscarDescripcion(String descripcion, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(descripcion);
                String sql = "select * from recetas where upper(descripcion_receta) like '%"
                        + descripcion.toUpperCase() + "%'"
                        + "order by id_receta offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_receta") + "</td>"
                                + "<td>" + rs.getString("descripcion_receta") + "</td>"
                                + "<td>" + rs.getString("fecha_receta") + "</td>"
                                + "<td>" + rs.getString("caducidad_receta") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.out.println("ERROR--->" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.out.println("ERROR--->" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
