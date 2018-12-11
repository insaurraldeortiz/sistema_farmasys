<%@page import="modelos.Compras"%>
<%@page import="controladores.ComprasDetalleControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="modelos.ComprasDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_compradetalle = Integer.parseInt(request.getParameter("id_compradetalle"));
    int cantidad_compradetalle = Integer.parseInt(request.getParameter("cantidad_compradetalle"));
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    ComprasDetalle compradetalle = new ComprasDetalle();
    //compradetalle.setId_compradetalle(id_compradetalle);
    compradetalle.setCantidad_compradetalle(cantidad_compradetalle);
    
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
    compradetalle.setCompra(compra);
    compradetalle.setArticulo(articulo);
      
    if (ComprasDetalleControlador.agregar(compradetalle)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>