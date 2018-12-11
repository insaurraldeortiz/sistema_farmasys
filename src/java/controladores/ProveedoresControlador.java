package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Ciudades;
import modelos.Proveedores;
import utiles.Conexion;
import utiles.Utiles;

public class ProveedoresControlador {

    public static boolean agregar(Proveedores proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into proveedores (ruc_proveedor, nombre_proveedor, direccion_proveedor, id_ciudad, telefono_proveedor, email_proveedor)"
                    + "values ('"
                    + proveedor.getRuc_proveedor() + "','"
                    + proveedor.getNombre_proveedor() + "','"
                    + proveedor.getDireccion_proveedor() + "',"
                    + proveedor.getCiudad().getId_ciudad() + ",'"
                    + proveedor.getTelefono_proveedor() + "','"
                    + proveedor.getEmail_proveedor() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR AGREGAR PROVEEDOR: " + ex.getLocalizedMessage());
            }
        }

        return valor;

    }

    public static boolean modificar(Proveedores proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update proveedores set "
                    + "ruc_proveedor='" + proveedor.getRuc_proveedor() + "', "
                    + "nombre_proveedor='" + proveedor.getNombre_proveedor() + "', "
                    + "direccion_proveedor='" + proveedor.getDireccion_proveedor() + "',"
                    + "id_ciudad=" + proveedor.getCiudad().getId_ciudad() + ", "
                    + "telefono_proveedor='" + proveedor.getTelefono_proveedor() + "', "
                    + "email_proveedor='" + proveedor.getEmail_proveedor() + "' "
                    + "where id_proveedor=" + proveedor.getId_proveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR MODIFICAR PROVEEDOR: " + ex.getLocalizedMessage());
            }
        }

        return valor;

    }

    public static boolean eliminar(Proveedores proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from proveedores where id_proveedor=" + proveedor.getId_proveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR ELIMINAR PROVEEDOR: " + ex.getLocalizedMessage());
            }
        }
        return valor;
    }

    public static Proveedores buscarId(Proveedores proveedor) {
        if (Conexion.conectar()) {
            String sql = "select * from proveedores p join ciudades c on p.id_ciudad=c.id_ciudad where p.id_proveedor ='" + proveedor.getId_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));

                    proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                    proveedor.setEmail_proveedor(rs.getString("email_proveedor"));

                    proveedor.setCiudad(ciudad);
                } else {
                    proveedor.setId_proveedor(0);
                    proveedor.setRuc_proveedor("");
                    proveedor.setNombre_proveedor("");
                    proveedor.setDireccion_proveedor("");

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");

                    proveedor.setTelefono_proveedor("");
                    proveedor.setEmail_proveedor("");

                    proveedor.setCiudad(ciudad);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR BUSCAR ID PROVEEDOR: " + ex.getLocalizedMessage());
            }
        }
        return proveedor;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from proveedores where upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_proveedor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("ruc_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td>" + rs.getString("direccion_proveedor") + "</td>"
                                + "<td>" + rs.getString("telefono_proveedor") + "</td>"
                                + "<td>" + rs.getString("email_proveedor") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.out.println("ERROR BUSCAR NOMBRE PROVEEDOR: " + ex.getLocalizedMessage());
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.out.println("ERROR BUSCAR NOMBRE PROVEEDOR: " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
