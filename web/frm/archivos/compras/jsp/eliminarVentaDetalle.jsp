<%@page import="controladores.ComprasDetalleControlador"%>
<%@page import="modelos.ComprasDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compradetalle = Integer.parseInt(request.getParameter("id_compradetalle"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    ComprasDetalle compradetalle = new ComprasDetalle();
    compradetalle.setId_compradetalle(id_compradetalle);

    if (ComprasDetalleControlador.eliminar(compradetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>