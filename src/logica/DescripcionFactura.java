/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author dark
 */
public class DescripcionFactura extends General {
    private String plato;
    private int cantidad;
    private float precio;
    private float total;

    public DescripcionFactura(String plato, float total) {
        super();
        this.plato = plato;
        this.total = total;
    }    
   
    public DescripcionFactura(int id, String plato, int cantidad, float precio, float total) {
        super(id);
        this.plato = plato;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public DescripcionFactura(int id, String plato,float precio, int cantidad, float total) {
        super(id);
        this.plato = plato;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }
    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DescripcionFactura{" + "id=" + id + ", plato=" + plato + ", cantidad=" + cantidad + ", precio=" + precio + ", total=" + total + '}';
    }
    
    
}
