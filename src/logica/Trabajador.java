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
public class Trabajador extends Usuario{
    private int id_trabajador;

    public Trabajador(int id_trabajador, String user, String nombre, String apellidos, String identificacion) {
        super(user, nombre, apellidos, identificacion);
        this.id_trabajador = id_trabajador;
    }

    public Trabajador(int id_trabajador, int id_user, String user, String clave, int tipo, String nombre, String apellidos, String identificacion, String direccion, String tel, String correo) {
        super(id_user, user, clave, tipo, nombre, apellidos, identificacion, direccion, tel, correo);
        this.id_trabajador = id_trabajador;
    }
    
    public Trabajador(int id_trabajador, int id_user, String user, String nombre, String apellidos, String identificacion, String direccion, String tel, String correo) {
        super(id_user, user, "", -1, nombre, apellidos, identificacion, direccion, tel, correo);
        this.id_trabajador = id_trabajador;
    }

    public Trabajador(String user, String clave, String nombre, String apellidos, String identificacion, String direccion, String tel, String correo) {
        super(user, clave, nombre, apellidos, identificacion, direccion, tel, correo);
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "id_trabajador=" + id_trabajador + '}';
    }
    
    
}
