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
public class Adicion extends General {

    private int categoria_id;
    private String categoria_nom;
    private float precio;

    public Adicion(String categoria_nom, float precio, int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
        this.categoria_nom = categoria_nom;
        this.precio = precio;
    }

    public String getCategoria_nom() {
        return categoria_nom;
    }

    public void setCategoria_nom(String categoria_nom) {
        this.categoria_nom = categoria_nom;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    

    public Adicion(int categoria_id, String nombre, String descripcion) {
        super(nombre, descripcion);
        this.categoria_id = categoria_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;

    }
    
    public Adicion(int categoria_id, float precio, String nombre, String descripcion) {
        super(nombre, descripcion);
        this.categoria_id = categoria_id;
        this.precio = precio;
    }

    public Adicion( String nombre, String descripcion,int categoria_id, float precio) {
        super(nombre, descripcion);
        this.categoria_id = categoria_id;
        this.precio = precio;
    }

    public Adicion(String nombre, String descripcion,float precio) {
        super(nombre, descripcion);
        this.precio = precio;
    }

    public Adicion(int id,String nombre, String descripcion,String categoria_nom, float precio) {
        super(id, nombre, descripcion);
        this.categoria_nom = categoria_nom;
        this.precio = precio;
    }
    
    
    
}
