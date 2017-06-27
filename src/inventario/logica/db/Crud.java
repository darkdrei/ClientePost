/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logica.db;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author eugenio
 */
public interface Crud {
    public void insertarDato(Object o);
    public void sateDato(ArrayList<Object> o,int id);
    public void sateDato(int id);
    public void sateDato(Object o);
    public void sateDato();
    public void updateDato(Object o,int id);
    public void updateDato(Object o);
    public void updateDato(int o);
    public Object[] validInsert(ArrayList<Object> l);
    public Object[] validInsert(ArrayList<Object> l,int n);
    public Object validInsert(Object l, int id);
    public Object validInsert(int id);
    public Object validInsert(Object l,String n);
    public void deleteDato(Object o);
    public void deleteDato(Object o,int id);
    public void deleteAll();
    public void deleteAll(int id);
    public ArrayList<Object> getDatos();
    public ArrayList<Object> getDatos(int id);
    public ArrayList<Object> getDatos(String... id);
    public Object getDato(int id);
    public Object getDato(String... cad);
    public Object get_sDato();
    public Object[][] getObjetoMatriz();
    public Object[][] getObjetoMatriz(int id);
    public int getEndObjeto();
    public int getEndObjeto(int id);
    public Vector<Integer> getIds();
    public String[] getNombres();
    public ArrayList<Object> getDato();
    public Object findObjeto(Object b);
    public int size();
}
