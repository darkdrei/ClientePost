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
public class Factura {
    private int id ;
    private String nombre ;
    private String apellidos;
    private String direccion;
    private String fecha ;
    private boolean estado ;
    private boolean paga;
    private boolean enviada;

    public boolean isEnviada() {
        return enviada;
    }

    public void setEnviada(boolean enviada) {
        this.enviada = enviada;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public Factura(int id, String nombre, String apellidos, String direccion, String fecha, boolean estado, boolean paga) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fecha = fecha;
        this.estado = estado;
        this.paga = paga;
    }

    public Factura(int id, String nombre, String apellidos, String direccion, String fecha, boolean estado, boolean paga, boolean enviada) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fecha = fecha;
        this.estado = estado;
        this.paga = paga;
        this.enviada = enviada;
    }
    
    

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + ", fecha=" + fecha + ", estado=" + estado + ", paga=" + paga + '}';
    }
    
    
}
