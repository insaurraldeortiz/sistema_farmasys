<%@page import="controladores.VentasDetalleControlador"%>
<%@page import="modelos.VentasDetalle"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ventadetalle = Integer.parseInt(request.getParameter("id_ventadetalle"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    VentasDetalle ventadetalle = new VentasDetalle();
    ventadetalle.setId_ventadetalle(id_ventadetalle);

    if (VentasDetalleControlador.eliminar(ventadetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>