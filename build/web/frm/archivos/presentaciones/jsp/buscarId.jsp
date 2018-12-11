
<%@page import="controladores.PresentacionesControlador"%>
<%@page import="modelos.Presentaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presentacion = Integer.parseInt(request.getParameter("id_presentacion"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Presentaciones presentacion = new Presentaciones();
    presentacion.setId_presentacion(id_presentacion);
    
   PresentacionesControlador.buscarId(presentacion);
    if (presentacion.getId_presentacion()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        presentacion = new Presentaciones();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_presentacion", presentacion.getId_presentacion());
    obj.put("nombre_presentacion", presentacion.getNombre_presentacion());
    out.print(obj);
    out.flush();
%>
