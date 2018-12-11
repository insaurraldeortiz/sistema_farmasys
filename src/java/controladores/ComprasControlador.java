/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Proveedores;
import modelos.Compras;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ADMINISTRADOR
 */
public class ComprasControlador {

    public static Compras buscarId(int id) {
        Compras compras = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras c "
                        + "left join proveedores a on c.id_proveedor=a.id_proveedor "
                        + "where id_compra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        compras = new Compras();
                        compras.setId_compra(rs.getInt("id_compra"));
                        compras.setFecha_compra(rs.getDate("fecha_compra"));
                        Proveedores proveedor = new Proveedores();
                        proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                        proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                        compras.setProveedor(proveedor);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compras;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras p "
                        + "left join proveedores c on c.id_proveedor=p.id_proveedor "
                        + "where upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_compra") + "</td>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = compra.getProveedor().getId_proveedor();
            Date v2 = compra.getFecha_compra();
            String sql = "insert into compras(id_proveedor, fecha_compra) "
                    + "values('" + v1 + "','" + v2 + "')";
            System.out.println("AGREGAR VENTA--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_compra = keyset.getInt(1);
                    compra.setId_compra(id_compra);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR AGREGAR VENTA --> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }
        return valor;
    }

    public static boolean modificar(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set id_proveedor=? "
                    + "where id_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compra.getProveedor().getId_proveedor());
                ps.setInt(2, compra.getId_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR MODIFICAR VENTA --> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("ERROR MODIFICAR VENTA--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Compras compra  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from compras where id_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compra.getId_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR ELIMINAR VENTA --> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("ERROR ELIMINAR VENTA --> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
