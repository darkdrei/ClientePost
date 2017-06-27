/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author dark
 */
public class Factura extends General{
    private int d,m,a,mesa_id,tipo;
    private float total;
    private String fecha;
    
    public Factura(int id) {
        super(id);
    }

    public Factura(int d, int m, int a, int id) {
        super(id);
        this.d = d;
        this.m = m;
        this.a = a;
    }
    
    

    public Factura(float total) {
        this.total = total;
    }
    
    

    public Factura(int mesa_id,int id) {
        this.mesa_id = mesa_id;
    }
    
    public Factura(String fecha,int id){
        super(id);
        this.fecha=fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Factura(int tipo, float total) {
        super();
        this.tipo = tipo;
        this.total = total;
    }
    
    

    public Factura(int d, int m, int a, int mesa_id, int id) {
        super(id);
        this.d = d;
        this.m = m;
        this.a = a;
        this.mesa_id = mesa_id;
    }

    public Factura(String fceha,int d, int m, int a,int id) {
        this.d = d;
        this.m = m;
        this.a = a;
        this.fecha=fceha;
    }

    public int getMesa_id() {
        return mesa_id;
    }

    public void setMesa_id(int mesa_id) {
        this.mesa_id = mesa_id;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Factura{" + "d=" + d + ", m=" + m + ", a=" + a + ", mesa_id=" + mesa_id + ", tipo=" + tipo + ", total=" + total + ", fecha=" + fecha + '}';
    }
    
      
}
