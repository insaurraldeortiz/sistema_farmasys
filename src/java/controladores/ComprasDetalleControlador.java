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
import modelos.Compras;
import modelos.ComprasDetalle;
import modelos.Compras;
import modelos.ComprasDetalle;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author FABIAN
 */
public class ComprasDetalleControlador {

    public static ComprasDetalle buscarId(int id) {
        ComprasDetalle compradetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras_detalle dp "+
                             "left join compras p on p.id_compra=dp.id_compra "+
                             "left join articulos a on a.id_articulo=dp.id_articulo "+
                             "where dp.id_compradetalle=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        compradetalle = new ComprasDetalle();
                        compradetalle.setId_compradetalle(rs.getInt("id_compradetalle"));
                        compradetalle.setCantidad_compradetalle(rs.getInt("cantidad_compradetalle"));
                        
                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                        compradetalle.setArticulo(articulo);
                        
                        Compras compra = new Compras();
                        compra.setId_compra(rs.getInt("id_compra"));
                        compradetalle.setCompra(compra);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compradetalle;
    }
    
    public static String buscarIdCompra(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras_detalle dp "+
                        "left join compras p on p.id_compra=dp.id_compra "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where dp.id_compra="+id+" "+
                        "order by id_compradetalle";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_compradetalle");
                        total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_compradetalle") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>" 
                               + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_compradetalle")+")'"
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
                String sql = "select * from compras_detalle dp "+
                        "left join compras p on p.id_compra=dp.id_compra "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where upper(a.nombre_articulo) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_compradetalle "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_compradetalle") + "</td>"
                               + "<td>" + rs.getString("id_compra") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>"
                               + "<td>" + rs.getInt("cantidad_compradetalle") + "</td>" 
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

    public static boolean agregar(ComprasDetalle compradetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into compras_detalle "
                    + "(id_compra,id_articulo,cantidad_compradetalle) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compradetalle.getCompra().getId_compra());
                ps.setInt(2, compradetalle.getArticulo().getId_articulo());
                ps.setInt(3, compradetalle.getCantidad_compradetalle());
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

    public static boolean modificar(ComprasDetalle compradetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras_detalle set "
                    + "id_compra=?,"
                    + "id_articulo=?,"
                    + "cantidad_compradetalle=? "
                    + "where id_compradetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compradetalle.getCompra().getId_compra());
                ps.setInt(2, compradetalle.getArticulo().getId_articulo());
                ps.setInt(3, compradetalle.getCantidad_compradetalle());
                ps.setInt(4,compradetalle.getId_compradetalle());
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

    public static boolean eliminar(ComprasDetalle compradetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from compras_detalle where id_compradetalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compradetalle.getId_compradetalle());
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
    
    public static boolean eliminarArticuloCompra(Compras compra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from compras_detalle where id_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compra.getId_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + compra.getId_compra());
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
