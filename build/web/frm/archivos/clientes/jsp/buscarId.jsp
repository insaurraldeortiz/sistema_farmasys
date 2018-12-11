
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
   ClientesControlador.buscarId(cliente);
    if (cliente.getId_cliente()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        cliente = new Clientes();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cliente", cliente.getId_cliente());
    obj.put("nombre_cliente", cliente.getNombre_cliente());
    out.print(obj);
    out.flush();
%>
