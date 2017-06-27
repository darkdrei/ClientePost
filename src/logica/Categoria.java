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
public class Categoria extends General{

    public Categoria(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }    

    public Categoria(String nombre, String descripcion) {
        super(nombre, descripcion);
    }
    
    
}
