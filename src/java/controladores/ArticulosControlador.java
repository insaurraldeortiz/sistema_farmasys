package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Articulos;
import modelos.Categorias;
import modelos.Marcas;
import modelos.Presentaciones;
import modelos.Proveedores;
import modelos.Rubros;
import utiles.Conexion;
import utiles.Utiles;

public class ArticulosControlador {

    public static boolean agregar(Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into articulos (codigo_articulo, nombre_articulo, id_marca, preciocompra_articulo, precioventa_articulo, id_rubro, id_categoria, id_presentacion, id_proveedor, iva_articulo)"
                    + "values (" + articulo.getCodigo_articulo() + ",'" + articulo.getNombre_articulo() + "'," + articulo.getMarca().getId_marca() + "," + articulo.getPreciocompra_articulo() + "," + articulo.getPrecioventa_articulo() + "," + articulo.getRubro().getId_rubro() + "," + articulo.getCategoria().getId_categoria() + "," + articulo.getPresentacion().getId_presentacion() + "," + articulo.getProveedor().getId_proveedor() + "," + articulo.getIva_articulo() + ")";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR AGREGAR ARTICULO -->" + ex.getLocalizedMessage());
            }
        }
        return valor;
    }

    public static boolean modificar(Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update articulos set "
                    + "codigo_articulo=" + articulo.getCodigo_articulo() + ","
                    + "nombre_articulo='" + articulo.getNombre_articulo() + "',"
                    + "id_marca=" + articulo.getMarca().getId_marca() + ","
                    + "preciocompra_articulo=" + articulo.getPreciocompra_articulo() + ","
                    + "precioventa_articulo=" + articulo.getPrecioventa_articulo() + ","
                    + "id_rubro=" + articulo.getRubro().getId_rubro() + ","
                    + "id_categoria=" + articulo.getCategoria().getId_categoria() + ","
                    + "id_presentacion=" + articulo.getPresentacion().getId_presentacion() + ","
                    + "id_proveedor=" + articulo.getProveedor().getId_proveedor() + ","
                    + "iva_articulo=" + articulo.getIva_articulo() + " "
                    + "where id_articulo=" + articulo.getId_articulo();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ERROR MODIFICAR ARTICULO -->" + ex.getLocalizedMessage());
            }
        }
        return valor;
    }

    public static boolean eliminar(Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from articulos where id_articulo=" + articulo.getId_articulo();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valor;
    }

    public static Articulos buscarId(Articulos articulo) {
        if (Conexion.conectar()) {
            String sql = "select * from articulos a "
                    + "join marcas m on a.id_marca=m.id_marca "
                    + "join rubros r on a.id_rubro=r.id_rubro "
                    + "join categorias c on a.id_categoria=c.id_categoria "
                    + "join presentaciones p on a.id_presentacion=p.id_presentacion "
                    + "join proveedores pro on a.id_proveedor=pro.id_proveedor "
                    + "where id_articulo ='" + articulo.getId_articulo() + "'";

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    articulo.setId_articulo(rs.getInt("id_articulo"));
                    articulo.setCodigo_articulo(rs.getString("codigo_articulo"));
                    articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                    articulo.setPreciocompra_articulo(rs.getInt("preciocompra_articulo"));
                    articulo.setPrecioventa_articulo(rs.getInt("precioventa_articulo"));

                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));

                    Rubros rubro = new Rubros();
                    rubro.setId_rubro(rs.getInt("id_rubro"));
                    rubro.setNombre_rubro(rs.getString("nombre_rubro"));

                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setNombre_categoria(rs.getString("nombre_categoria"));

                    Presentaciones presentacion = new Presentaciones();
                    presentacion.setId_presentacion(rs.getInt("id_presentacion"));
                    presentacion.setNombre_presentacion(rs.getString("nombre_presentacion"));

                    Proveedores proveedor = new Proveedores();
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    
                    articulo.setIva_articulo(rs.getString("iva_articulo"));

                    articulo.setMarca(marca);
                    articulo.setRubro(rubro);
                    articulo.setCategoria(categoria);
                    articulo.setPresentacion(presentacion);
                    articulo.setProveedor(proveedor);
                } else {
                    articulo.setId_articulo(0);
                    articulo.setCodigo_articulo("");
                    articulo.setNombre_articulo("");
                    articulo.setPreciocompra_articulo(0);
                    articulo.setPrecioventa_articulo(0);

                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");

                    Rubros rubro = new Rubros();
                    rubro.setId_rubro(0);
                    rubro.setNombre_rubro("");

                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(0);
                    categoria.setNombre_categoria("");

                    Presentaciones presentacion = new Presentaciones();
                    presentacion.setId_presentacion(0);
                    presentacion.setNombre_presentacion("");

                    Proveedores proveedor = new Proveedores();
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");
                    
                    articulo.setIva_articulo("");

                    articulo.setMarca(marca);
                    articulo.setRubro(rubro);
                    articulo.setCategoria(categoria);
                    articulo.setPresentacion(presentacion);
                    articulo.setProveedor(proveedor);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return articulo;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from articulos a "
                        + "join marcas m on a.id_marca=m.id_marca "
                        + "join rubros r on a.id_rubro=r.id_rubro "
                        + "join categorias c on a.id_categoria=c.id_categoria "
                        + "join presentaciones p on a.id_presentacion=p.id_presentacion "
                        + "join proveedores pro on a.id_proveedor=pro.id_proveedor "
                        + "where upper(nombre_articulo) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_articulo offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("codigo_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td>" + rs.getString("preciocompra_articulo") + "</td>"
                                + "<td>" + rs.getString("precioventa_articulo") + "</td>"
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
