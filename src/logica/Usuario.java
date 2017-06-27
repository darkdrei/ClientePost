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
public class Usuario extends Persona {
    private int id_user;
    private String user;
    private String clave;
    private int tipo;

    public String getUser() {
        return user;
    }

    public Usuario(String user) {
        this.user = user;
    }

    public Usuario(String user, String nombre, String apellidos, String identificacion) {
        super(nombre, apellidos, identificacion);
        this.user = user;
    }
    
        
    public void setUser(String user) {
        this.user = user;
    }

    public Usuario(int tipo) {
        this.tipo = tipo;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Usuario(int id_user, String user, String clave, int tipo, String nombre, String apellidos, String identificacion, String direccion, String tel, String correo) {
        super(nombre, apellidos, identificacion, direccion, tel, correo);
        this.id_user = id_user;
        this.user = user;
        this.clave = clave;
        this.tipo = tipo;
    }
    
        public Usuario(String user, String clave, String nombre, String apellidos, String identificacion, String direccion, String tel, String correo) {
        super(nombre, apellidos, identificacion, direccion, tel, correo);
        this.user = user;
        this.clave = clave;
    }

    public Usuario(String user, String clave) {
        this.user = user;
        this.clave = clave;
    }

    public Usuario(String user, String clave, int tipo) {
        this.user = user;
        this.clave = clave;
        this.tipo = tipo;
    }
    
    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario(String user, String clave, int tipo, String nombre, String apellidos, String identificacion, String direccion, String tel, String correo) {
        super(nombre, apellidos, identificacion, direccion, tel, correo);
        this.user = user;
        this.clave = clave;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_user=" + id_user + ", user=" + user + ", clave=" + clave + ", tipo=" + tipo + '}';
    }
    
    

}
