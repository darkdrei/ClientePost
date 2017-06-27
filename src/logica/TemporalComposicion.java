/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author dreicon
 */
public class TemporalComposicion {

    private int id;
    private int id_ingrediente;
    private String nombre;
    private float cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCantidad() {
        return cantidad;
    }

    public TemporalComposicion(int id, float cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

        
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    

    public TemporalComposicion(int id, String nombre, float cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public TemporalComposicion(int id, int id_ingrediente, float cantidad) {
        this.id = id;
        this.id_ingrediente = id_ingrediente;
        this.cantidad = cantidad;
    }
    
}
