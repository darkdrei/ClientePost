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
public class Configuracion {
    private String nom;
    private String pass;
    private int id;
    private String correo;
    private float ingrediente;
    private int unidades;

    public Configuracion(String nom, String pass, int id, String correo,int uni,float ing) {
        this.nom = nom;
        this.pass = pass;
        this.id = id;
        this.correo = correo;
        this.unidades =uni;
        this.ingrediente = ing;
    }

    public Configuracion(String nom, String pass, String correo,int uni,float ing) {
        this.nom = nom;
        this.pass = pass;
        this.correo = correo;
        this.unidades =uni;
        this.ingrediente = ing;
    }

    public Configuracion(String nom, int id, String correo,int uni,float ing) {
        this.nom = nom;
        this.id = id;
        this.correo = correo;
        this.unidades =uni;
        this.ingrediente = ing;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public float getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(float ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getUnidades() {
        return unidades;
    }

    @Override
    public String toString() {
        return "Configuracion{" + "nom=" + nom + ", pass=" + pass + ", id=" + id + ", correo=" + correo + ", ingrediente=" + ingrediente + ", unidades=" + unidades + '}';
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    
    
}
