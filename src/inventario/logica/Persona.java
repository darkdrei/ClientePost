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
public class Persona {
    private int id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private int negocio;

    public Persona(int id, String nombre, String apellidos, String usuario, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Persona(int id, String nombre, String apellidos, String usuario, String clave, int negocio) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
        this.negocio = negocio;
    }
    

    public Persona(int id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getNegocio() {
        return negocio;
    }

    public void setNegocio(int negocio) {
        this.negocio = negocio;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", usuario=" + usuario + ", clave=" + clave + ", negocio=" + negocio + '}';
    }
}
