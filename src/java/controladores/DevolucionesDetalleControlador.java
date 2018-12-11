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
import modelos.Devoluciones;
import modelos.DevolucionesDetalle;
import modelos.Proveedores;
import utiles.Conexion;

/**
 *
 * @author ADMINISTRADOR
 */
public class DevolucionesDetalleControlador {

    public static boolean agregar(DevolucionesDetalle devoluciondetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into devoluciones_detalle (id_devolucion, id_articulo, lote_devolucion, cantidad_devolucion)"
                    + "values ("
                    + "" + devoluciondetalle.getDevolucion().getId_devolucion() + ","
                    + "" + devoluciondetalle.getArticulo().getId_articulo() + ","
                    + "'" + devoluciondetalle.getLote_devolucion() + "',"
                    + "" + devoluciondetalle.getCantidad_devolucion() + ""
                    + ")";

            try {
                Conexion.getSt().executeUpdate(sql);

                valor = true;

            } catch (SQLException ex) {
                System.out.println("AGREGAR DEV DETALLE--->" + ex.getLocalizedMessage());
            }
        }

        return valor;

    }

    public static boolean modificar(DevolucionesDetalle devoluciondetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update devoluciones_detalle set "
                    + "id_devolucion=" + devoluciondetalle.getDevolucion().getId_devolucion() + ","
                    + "id_articulo=" + devoluciondetalle.getArticulo().getId_articulo() + ","
                    + "lote_devolucion='" + devoluciondetalle.getLote_devolucion() + "',"
                    + "cantidad_devolucion=" + devoluciondetalle.getCantidad_devolucion()
                    + " where id_devoluciondetalle=" + devoluciondetalle.getId_devoluciondetalle();

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;

            } catch (SQLException ex) {
                System.out.println("MODIFICAR DEV DETALLE--->" + ex.getLocalizedMessage());
            }
        }

        return valor;

    }

    public static boolean eliminar(DevolucionesDetalle devoluciondetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from devoluciones_detalle where id_devoluciondetalle=" + devoluciondetalle.getId_devoluciondetalle();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ELIMINAR DV DETALLE--->" + ex.getLocalizedMessage());
            }
        }
        return valor;
    }

    public static DevolucionesDetalle buscarId(DevolucionesDetalle devoluciondetalle) {
        if (Conexion.conectar()) {
            String sql = "select * from devoluciones_detalle dd join devoluciones d "
                    + "on dd.id_devolucion = d.id_devolucion "
                    + "join articulos a on dd.id_articulo = a.id_articulo "
                    + " where id_devoluciondetalle ='" + devoluciondetalle.getId_devoluciondetalle() + "'";
            System.out.println("SQL--->"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Devoluciones devolucion = new Devoluciones();
                    Articulos articulo = new Articulos();

                    devolucion.setId_devolucion(rs.getInt("id_devolucion"));

                    articulo.setId_articulo(rs.getInt("id_articulo"));
                    articulo.setNombre_articulo(rs.getString("nombre_articulo"));

                    devoluciondetalle.setLote_devolucion(rs.getString("lote_devolucion"));
                    devoluciondetalle.setCantidad_devolucion(rs.getInt("cantidad_devolucion"));

                    devoluciondetalle.setDevolucion(devolucion);
                    devoluciondetalle.setArticulo(articulo);
                } else {
                    Devoluciones devolucion = new Devoluciones();
                    Articulos articulo = new Articulos();

                    devolucion.setId_devolucion(0);

                    articulo.setId_articulo(0);
                    articulo.setNombre_articulo("");

                    devoluciondetalle.setLote_devolucion("");
                    devoluciondetalle.setCantidad_devolucion(0);

                    devoluciondetalle.setDevolucion(devolucion);
                    devoluciondetalle.setArticulo(articulo);
                }
            } catch (SQLException ex) {
                System.out.println("BUSCAR ID : " + ex.getLocalizedMessage());
            }
        }
        return devoluciondetalle;
    }
    
    public static String buscarIdDevolucion(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from devoluciones_detalle dp "+
                        "left join devoluciones p on p.id_devolucion=dp.id_devolucion "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where p.id_devolucion="+id+" "+
                        "order by dp.id_devoluciondetalle";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_devolucion");
                        total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_devoluciondetalle") + "</td>"
                               + "<td>" + rs.getString("id_articulo") + "</td>"
                               + "<td>" + rs.getString("nombre_articulo") + "</td>" 
                               + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_devoluciondetalle")+")'"
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
}
