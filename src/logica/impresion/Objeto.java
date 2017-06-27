/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.impresion;

/**
 *
 * @author Toshiba
 */
public class Objeto {
    private int id;
    private String nombre;
    private int cantidad;
    private long precio;

    public Objeto(String nombre, int cantidad, long precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id =0;
    }

    public Objeto(int id, String nombre,  int cantidad, long precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    

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


    public long getPrecio() {
        return precio;
    }

 
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

 

    public void setPrecio(long precio) {
        this.precio = precio;
    }



}
