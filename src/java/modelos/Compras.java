/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

public class Compras {
    int id_compra;
    Date fecha_compra;
    Proveedores proveedor;    

    public Compras() {
    }

    public Compras(int id_compra, Date fecha_compra, Proveedores proveedor) {
        this.id_compra = id_compra;
        this.fecha_compra = fecha_compra;
        this.proveedor = proveedor;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }
}
