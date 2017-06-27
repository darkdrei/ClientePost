/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica;

/**
 *
 * @author eugenio
 */
public class Descripcion {

    private int id;
    private int factura_id;
    private int articulo_id;
    private float cantidad;
    private String codigo;
    private float valor_unitario;
    private float total;
    private String nombre;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Descripcion(int id,  String codigo, String nombre,float cantidad, float valor_unitario, float total) {
        this.id = id;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.valor_unitario = valor_unitario;
        this.total = total;
        this.nombre = nombre;
    }
    
    

    public Descripcion(int factura_id, int articulo_id, float cantidad) {
        this.factura_id = factura_id;
        this.articulo_id = articulo_id;
        this.cantidad = cantidad;
    }

    public Descripcion(int id, int factura_id, int articulo_id, float cantidad, String codigo, float valor_unitario) {
        this.id = id;
        this.factura_id = factura_id;
        this.articulo_id = articulo_id;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.valor_unitario = valor_unitario;
        this.total = valor_unitario*this.cantidad;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(int factura_id) {
        this.factura_id = factura_id;
    }

    public int getArticulo_id() {
        return articulo_id;
    }

    public void setArticulo_id(int articulo_id) {
        this.articulo_id = articulo_id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Descripcion{" + "id=" + id + ", factura_id=" + factura_id + ", articulo_id=" + articulo_id + ", cantidad=" + cantidad + '}';
    }
}
