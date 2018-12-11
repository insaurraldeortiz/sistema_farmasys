
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
   ArticulosControlador.buscarId(articulo);
    if (articulo.getId_articulo()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_articulo", articulo.getId_articulo());
    obj.put("codigo_articulo", articulo.getCodigo_articulo());
    obj.put("nombre_articulo", articulo.getNombre_articulo());
    obj.put("id_marca", articulo.getMarca().getId_marca());
    obj.put("nombre_marca", articulo.getMarca().getNombre_marca());
    obj.put("preciocompra_articulo", articulo.getPreciocompra_articulo());
    obj.put("precioventa_articulo", articulo.getPrecioventa_articulo());
    obj.put("id_rubro", articulo.getRubro().getId_rubro());
    obj.put("nombre_rubro", articulo.getRubro().getNombre_rubro());
    obj.put("id_categoria", articulo.getCategoria().getId_categoria());
    obj.put("nombre_categoria", articulo.getCategoria().getNombre_categoria());
    obj.put("id_presentacion", articulo.getPresentacion().getId_presentacion());
    obj.put("nombre_presentacion", articulo.getPresentacion().getNombre_presentacion());
    obj.put("id_proveedor", articulo.getProveedor().getId_proveedor());
    obj.put("nombre_proveedor", articulo.getProveedor().getNombre_proveedor());
    obj.put("iva_articulo", articulo.getIva_articulo());
    out.print(obj);
    out.flush();
%>
