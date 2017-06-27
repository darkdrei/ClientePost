/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;

/**
 *
 * @author CTesting
 */
public class Tipo {
    private int id;
    private int categoria_id;
    private String categoria_nom;
    private String nombre;
    private String descripcion;
    private Color colo;
    private String color;

    public Tipo(int id, int categoria_id, String nombre, String descripcion,String color) {
        this.id = id;
        this.categoria_id = categoria_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
    }

    public Tipo(int id, int categoria_id, String nombre, String descripcion, Color colo) {
        this.id = id;
        this.categoria_id = categoria_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colo = colo;
    }

    public Tipo(int categoria_id, String nombre, String descripcion, Color colo) {
        this.categoria_id = categoria_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colo = colo;
    }

    public Tipo(int id, String categoria_nom, String nombre, String descripcion, Color colo) {
        this.id = id;
        this.categoria_nom = categoria_nom;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colo = colo;
    }

    public Tipo( Color colo,int id, int categoria_id, String nombre, String descripcion) {
        this.id = id;
        this.categoria_id = categoria_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colo = colo;
    }
    

    public Tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getCategoria_nom() {
        return categoria_nom;
    }

    public void setCategoria_nom(String categoria_nom) {
        this.categoria_nom = categoria_nom;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
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

    public Color getColo() {
        return colo;
    }

    public void setColo(Color colo) {
        this.colo = colo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Tipo{" + "id=" + id + ", categoria_id=" + categoria_id + ", categoria_nom=" + categoria_nom + ", nombre=" + nombre + ", descripcion=" + descripcion + ", colo=" + colo + ", color=" + color + '}';
    }
    
    
}

