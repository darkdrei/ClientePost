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
public class Cliente extends Persona{
    private String identificacion;
    public Cliente(int id, String nombre, String apellidos,String identificacion) {
        super(id, nombre, apellidos);
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "identificacion=" + identificacion + '}';
    }
}
