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
public class Articulo {
    private int id ; 
    private String nombre ; 
    private float precio ;
    private float existencias ;
    private int tipo ;
    private int negocio;
    private String codigo;

    public Articulo(int id, String nombre, float precio, float existencias, int tipo, int negocio,String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
        this.tipo = tipo;
        this.negocio = negocio;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public int getNegocio() {
        return negocio;
    }

    public void setNegocio(int negocio) {
        this.negocio = negocio;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getExistencias() {
        return existencias;
    }

    public void setExistencias(float existencias) {
        this.existencias = existencias;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", existencias=" + existencias + ", tipo=" + tipo + ", negocio=" + negocio + ", codigo=" + codigo + '}';
    }
    
    
}
