<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Presentaciones"%>
<%@page import="modelos.Categorias"%>
<%@page import="modelos.Rubros"%>
<%@page import="modelos.Marcas"%>
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    int id_presentacion = Integer.parseInt(request.getParameter("id_presentacion"));
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    int preciocompra_articulo = Integer.parseInt(request.getParameter("preciocompra_articulo"));
    int precioventa_articulo = Integer.parseInt(request.getParameter("precioventa_articulo"));
    String nombre_articulo = request.getParameter("nombre_articulo");
    String codigo_articulo = request.getParameter("codigo_articulo");
    String iva_articulo = request.getParameter("iva_articulo");
    
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    
    Rubros rubro = new Rubros();
    rubro.setId_rubro(id_rubro);
    
    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);
    
    Presentaciones presentacion = new Presentaciones();
    presentacion.setId_presentacion(id_presentacion);
    
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    articulo.setCodigo_articulo(codigo_articulo);
    articulo.setNombre_articulo(nombre_articulo);
    articulo.setPreciocompra_articulo(preciocompra_articulo);
    articulo.setPrecioventa_articulo(precioventa_articulo);
    articulo.setIva_articulo(iva_articulo);
    articulo.setMarca(marca);
    articulo.setRubro(rubro);
    articulo.setCategoria(categoria);
    articulo.setPresentacion(presentacion);
    articulo.setProveedor(proveedor);

    if (ArticulosControlador.modificar(articulo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
