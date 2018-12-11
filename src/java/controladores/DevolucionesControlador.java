package controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Devoluciones;
import modelos.Proveedores;
import utiles.Conexion;
import utiles.Utiles;

public class DevolucionesControlador {

    public static boolean agregar(Devoluciones devolucion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into devoluciones (fecha_devolucion, id_proveedor)"
                    + "values ("
                    + "'" + devolucion.getFecha_devolucion() + "',"
                    + "" + devolucion.getProveedor().getId_proveedor() + ")";

            try {
                Conexion.getSt().executeUpdate(sql);

                valor = true;

            } catch (SQLException ex) {
                Logger.getLogger(DevolucionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return valor;

    }

    public static boolean modificar(Devoluciones devolucion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update devoluciones set "
                    + "fecha_devolucion='" + devolucion.getFecha_devolucion() + "',"
                    + "id_proveedor=" + devolucion.getProveedor().getId_proveedor()
                    + " where id_devolucion=" + devolucion.getId_devolucion();

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;

            } catch (SQLException ex) {
                Logger.getLogger(DevolucionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return valor;

    }

    public static boolean eliminar(Devoluciones devolucion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from devoluciones where id_devolucion=" + devolucion.getId_devolucion();

            try {
                Conexion.getSt().executeUpdate(sql);

                valor = true;

            } catch (SQLException ex) {
                Logger.getLogger(DevolucionesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return valor;

    }

    public static Devoluciones buscarId(Devoluciones devolucion) {
        if (Conexion.conectar()) {
            String sql = "select * from devoluciones d join proveedores p "
                    + "on d.id_proveedor = p.id_proveedor where id_devolucion ='" + devolucion.getId_devolucion() + "'";
            System.out.println("BUSCAR ID DEVOLUCION");
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Proveedores proveedor = new Proveedores();
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    devolucion.setId_devolucion(rs.getInt("id_devolucion"));
                    devolucion.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                    devolucion.setProveedor(proveedor);
                } else {                    
                    Proveedores proveedor = new Proveedores();

                    devolucion.setId_devolucion(0);
                    devolucion.setFecha_devolucion(null);
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");

                    devolucion.setProveedor(proveedor);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR BUSCAR ID DEVOLUCION -->" + ex.getLocalizedMessage());
            }
        }
        return devolucion;
    }

    public static String buscarFecha(String fecha, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(fecha);
                String sql = "select * from devoluciones d join proveedores p "
                        + "on d.id_proveedor = p.id_proveedor where d.fecha_devolucion = '"
                        + fecha + "' "
                        + "order by id_devolucion offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_devolucion") + "</td>"
                                + "<td>" + rs.getString("fecha_devolucion") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
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
