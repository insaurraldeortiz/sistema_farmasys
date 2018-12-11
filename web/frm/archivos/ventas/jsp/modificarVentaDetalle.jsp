<%@page import="modelos.VentasDetalle"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Articulos"%>
<%@page import="controladores.VentasDetalleControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int cantidad_ventadetalle = Integer.parseInt(request.getParameter("cantidad_ventadetalle"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    VentasDetalle ventadetalle = new VentasDetalle();
    //ventadetalle.setId_ventadetalle(id_ventadetalle);
    ventadetalle.setCantidad_ventadetalle(cantidad_ventadetalle);
    
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
    ventadetalle.setVenta(venta);
    ventadetalle.setArticulo(articulo);
      
    if (VentasDetalleControlador.modificar(ventadetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>