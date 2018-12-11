
<%@page import="controladores.PresentacionesControlador"%>
<%@page import="modelos.Presentaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presentacion = Integer.parseInt(request.getParameter("id_presentacion"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Presentaciones presentacion = new Presentaciones();
    presentacion.setId_presentacion(id_presentacion);
    
    if (PresentacionesControlador.eliminar(presentacion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>