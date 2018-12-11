<%@page import="modelos.Articulos"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.VentasDetalleControlador"%>
<%@page import="modelos.VentasDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ventadetalle = Integer.parseInt(request.getParameter("id_ventadetalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    VentasDetalle ventadetalle = VentasDetalleControlador.buscarId(id_ventadetalle);
    if (ventadetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        ventadetalle = new VentasDetalle();
        ventadetalle.setId_ventadetalle(0);

        Ventas venta = new Ventas();
        venta.setId_venta(0);
        ventadetalle.setVenta(venta);

        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_articulo("");
        ventadetalle.setArticulo(articulo);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_ventadetalle", String.valueOf(ventadetalle.getId_ventadetalle()));
    System.out.println("--->" + ventadetalle.getId_ventadetalle());
    obj.put("id_venta", String.valueOf(ventadetalle.getVenta().getId_venta()));
    System.out.println("--->" + ventadetalle.getVenta().getId_venta());
    obj.put("id_articulo", String.valueOf(ventadetalle.getArticulo().getId_articulo()));
    System.out.println("--->" + ventadetalle.getArticulo().getId_articulo());
    obj.put("nombre_articulo", ventadetalle.getArticulo().getNombre_articulo());
    System.out.println("--->" + ventadetalle.getArticulo().getNombre_articulo());
    obj.put("cantidad_ventadetalle", String.valueOf(ventadetalle.getCantidad_ventadetalle()));

    out.print(obj);
    out.flush();
%>