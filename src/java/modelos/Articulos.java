/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Fabian
 */
public class Articulos {
    int id_articulo;
    String nombre_articulo;
    String codigo_articulo;
    int preciocompra_articulo;
    int precioventa_articulo;
    Marcas marca;
    Rubros rubro;
    Categorias categoria;
    Presentaciones presentacion;
    Proveedores proveedor;
    String iva_articulo;

    public Articulos() {
    }

    public Articulos(int id_articulo, String nombre_articulo, String codigo_articulo, int preciocompra_articulo, int precioventa_articulo, Marcas marca, Rubros rubro, Categorias categoria, Presentaciones presentacion, Proveedores proveedor, String iva_articulo) {
        this.id_articulo = id_articulo;
        this.nombre_articulo = nombre_articulo;
        this.codigo_articulo = codigo_articulo;
        this.preciocompra_articulo = preciocompra_articulo;
        this.precioventa_articulo = precioventa_articulo;
        this.marca = marca;
        this.rubro = rubro;
        this.categoria = categoria;
        this.presentacion = presentacion;
        this.proveedor = proveedor;
        this.iva_articulo = iva_articulo;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public int getPreciocompra_articulo() {
        return preciocompra_articulo;
    }

    public void setPreciocompra_articulo(int preciocompra_articulo) {
        this.preciocompra_articulo = preciocompra_articulo;
    }

    public int getPrecioventa_articulo() {
        return precioventa_articulo;
    }

    public void setPrecioventa_articulo(int precioventa_articulo) {
        this.precioventa_articulo = precioventa_articulo;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Rubros getRubro() {
        return rubro;
    }

    public void setRubro(Rubros rubro) {
        this.rubro = rubro;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Presentaciones getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentaciones presentacion) {
        this.presentacion = presentacion;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public String getIva_articulo() {
        return iva_articulo;
    }

    public void setIva_articulo(String iva_articulo) {
        this.iva_articulo = iva_articulo;
    }
    
}
