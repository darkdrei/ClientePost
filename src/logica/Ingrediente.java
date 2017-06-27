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
public class Ingrediente extends General {

    private int categoria;
    private float existencias;

    public Ingrediente(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    public Ingrediente(int categoria, float existencias, int id, String nombre) {
        super(id, nombre);
        this.categoria = categoria;
        this.existencias = existencias;
    }

    public float getExistencias() {
        return existencias;
    }

    public void setExistencias(float existencias) {
        this.existencias = existencias;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Ingrediente(int id, String nombre, String descripcion, int categoria) {
        super(id, nombre, descripcion);
        this.categoria = categoria;
    }

    public Ingrediente(String nombre, String descripcion, int categoria) {
        super(nombre, descripcion);
        this.categoria = categoria;
    }

    public Ingrediente(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

}
