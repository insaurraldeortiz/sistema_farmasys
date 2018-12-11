
<%@page import="controladores.PresentacionesControlador"%>
<%@page import="modelos.Presentaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presentacion = Integer.parseInt(request.getParameter("id_presentacion"));
    String nombre_presentacion = request.getParameter("nombre_presentacion");

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Presentaciones presentacion = new Presentaciones();
    presentacion.setId_presentacion(id_presentacion);
    presentacion.setNombre_presentacion(nombre_presentacion);

    if (PresentacionesControlador.agregar(presentacion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

