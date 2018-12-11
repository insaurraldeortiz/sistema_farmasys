<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Compras"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente")); 

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Proveedores proveedor = new Proveedores();
    proveedor.setId_cliente(id_cliente);
    
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    compra.setCliente(proveedor);
   
    if (ComprasControlador.modificar(compra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>