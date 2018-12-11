/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Articulos;
import modelos.Ventas;
import modelos.VentasDetalle;
import utiles.Conexion;
import utiles.Utiles;

public class VentasDetalleControlador {
    
    public static VentasDetalle buscarId(int id) {
        VentasDetalle ventadetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas_detalle dp "+
                             "left join ventas p on p.id_venta=dp.id_venta "+
                             "left join articulos a on a.id_articulo=dp.id_articulo "+
                             "where dp.id_ventadetalle=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ventadetalle = new VentasDetalle();
                        ventadetalle.setId_ventadetalle(rs.getInt("id_ventadetalle"));
                        ventadetalle.setCantidad_ventadetalle(rs.getInt("cantidad_ventadetalle"));
                        
                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                        ventadetalle.setArticulo(articulo);
                        
                        Ventas venta = new Ventas();
                        venta.setId_venta(rs.getInt("id_venta"));
                        ventadetalle.setVenta(venta);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventadetalle;
    }
    
    public static String buscarIdVenta(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas_detalle dp "+
                        "left join ventas p on p.id_venta=dp.id_venta "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where dp.id_venta="+id+" "+
                        "order by id_ventadetalle";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_ventadetalle");
                        total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_ventadetalle") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>" 
                               + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_ventadetalle")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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
    
    public static String buscarNombre(String nombre, int pagina ) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas_detalle dp "+
                        "left join ventas p on p.id_venta=dp.id_venta "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where upper(a.nombre_articulo) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_ventadetalle "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_ventadetalle") + "</td>"
                               + "<td>" + rs.getString("id_venta") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>"
                               + "<td>" + rs.getInt("cantidad_ventadetalle") + "</td>" 
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(VentasDetalle ventadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ventas_detalle "
                    + "(id_venta,id_articulo,cantidad_ventadetalle) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ventadetalle.getVenta().getId_venta());
                ps.setInt(2, ventadetalle.getArticulo().getId_articulo());
                ps.setInt(3, ventadetalle.getCantidad_ventadetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(VentasDetalle ventadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas_detalle set "
                    + "id_venta=?,"
                    + "id_articulo=?,"
                    + "cantidad_ventadetalle=? "
                    + "where id_ventadetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ventadetalle.getVenta().getId_venta());
                ps.setInt(2, ventadetalle.getArticulo().getId_articulo());
                ps.setInt(3, ventadetalle.getCantidad_ventadetalle());
                ps.setInt(4,ventadetalle.getId_ventadetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(VentasDetalle ventadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas_detalle where id_ventadetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ventadetalle.getId_ventadetalle());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean eliminarArticuloVenta(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas_detalle where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + venta.getId_venta());
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
}
