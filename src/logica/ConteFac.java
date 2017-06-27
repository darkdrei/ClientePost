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
public class ConteFac extends General {

    private int id;
    private int plato_id;
    private int cantidad;
    private int factura_id;

    public ConteFac(int id, int plato_id, int cantidad, int factura_id) {
        super(id);
        this.plato_id = plato_id;
        this.cantidad = cantidad;
        this.factura_id = factura_id;
    }

    public ConteFac(int cantidad) {
        this.cantidad = cantidad;
    }
    
    

    public ConteFac(int plato_id, int cantidad, int factura_id) {
        this.plato_id = plato_id;
        this.cantidad = cantidad;
        this.factura_id = factura_id;
    }

    public int getPlato_id() {
        return plato_id;
    }

    public void setPlato_id(int plato_id) {
        this.plato_id = plato_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(int factura_id) {
        this.factura_id = factura_id;
    }
    
    

}
