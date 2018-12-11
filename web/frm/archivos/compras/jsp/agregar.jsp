<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Proveedores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente")); 

    
    String sfecha_compra = request.getParameter("fecha_compra");
    java.sql.Date fecha_compra = Utiles.stringToSqlDate(sfecha_compra);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Proveedores proveedor = new Proveedores();
    proveedor.setId_cliente(id_cliente);
    
    
    
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    compra.setCliente(proveedor);
    compra.setFecha_compra(fecha_compra);
      
    
    if (ComprasControlador.agregar(compra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_compra", String.valueOf(compra.getId_compra()));
    out.print(obj);
    out.flush();
    
%>