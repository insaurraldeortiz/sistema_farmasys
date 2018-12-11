<%@page import="modelos.Articulos"%>
<%@page import="modelos.Compras"%>
<%@page import="controladores.ComprasDetalleControlador"%>
<%@page import="modelos.ComprasDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compradetalle = Integer.parseInt(request.getParameter("id_compradetalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    ComprasDetalle compradetalle = ComprasDetalleControlador.buscarId(id_compradetalle);
    if (compradetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        compradetalle = new ComprasDetalle();
        compradetalle.setId_compradetalle(0);

        Compras compra = new Compras();
        compra.setId_compra(0);
        compradetalle.setCompra(compra);

        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_articulo("");
        compradetalle.setArticulo(articulo);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_compradetalle", String.valueOf(compradetalle.getId_compradetalle()));
    System.out.println("--->" + compradetalle.getId_compradetalle());
    obj.put("id_compra", String.valueOf(compradetalle.getCompra().getId_compra()));
    System.out.println("--->" + compradetalle.getCompra().getId_compra());
    obj.put("id_articulo", String.valueOf(compradetalle.getArticulo().getId_articulo()));
    System.out.println("--->" + compradetalle.getArticulo().getId_articulo());
    obj.put("nombre_articulo", compradetalle.getArticulo().getNombre_articulo());
    System.out.println("--->" + compradetalle.getArticulo().getNombre_articulo());
    obj.put("cantidad_compradetalle", String.valueOf(compradetalle.getCantidad_compradetalle()));

    out.print(obj);
    out.flush();
%>