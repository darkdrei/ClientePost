/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author CTesting
 */
public class Plato {
    private int id;
    private String nombre;
    private String descripcion;
    private int tipo_id;
    private String tipo_nom;
    private float precio;

    public Plato(int id, String nombre, String descripcion, int tipo_id, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo_id = tipo_id;
        this.precio = precio;
    }

    public Plato(String nombre, String descripcion, int tipo_id, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo_id = tipo_id;
        this.precio = precio;
    }

    public Plato(String nombre, String descripcion, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Plato(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    

    public Plato(int id,String nombre, String descripcion, String tipo_nom, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo_nom = tipo_nom;
        this.precio = precio;
    }

    public String getTipo_nom() {
        return tipo_nom;
    }

    public void setTipo_nom(String tipo_nom) {
        this.tipo_nom = tipo_nom;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Plato{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo_id=" + tipo_id + ", precio=" + precio + '}';
    }
    
    
}
