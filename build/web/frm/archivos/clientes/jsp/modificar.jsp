<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String nombre_cliente = request.getParameter("nombre_cliente");
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);

    if (ClientesControlador.modificar(cliente)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
