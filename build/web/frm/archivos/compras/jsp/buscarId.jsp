<%@page import="controladores.ComprasDetalleControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Compras compras = ComprasControlador.buscarId(id_compra);
    if (compras != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        compras = new Compras();
        compras.setId_compra(0);
        compras.setFecha_compra(null);
  
        Proveedores proveedor = new Proveedores();
        compras.setCliente(proveedor);
        }
    
    String contenido_detalle = ComprasDetalleControlador.buscarIdCompra(id_compra);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_compra", String.valueOf(compras.getId_compra()));
    obj.put("fecha_compra", String.valueOf(compras.getFecha_compra()));
    obj.put("id_cliente", String.valueOf(compras.getCliente().getId_cliente()));
    obj.put("nombre_cliente", compras.getCliente().getNombre_cliente());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>