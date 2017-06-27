/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.impresion;

import java.io.*;
import java.awt.print.*;
import java.awt.print.PrinterJob.*;
import java.awt.print.PageFormat.*;
import java.util.ArrayList;
import vista.factura;


public class Imprimir {
    private factura base;
    public Imprimir() {
    }

    public Imprimir(factura base) {
        this.base = base;
    }
       
    
    public void main(int id_fact) {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pag = new PageFormat();
        Paper p = new Paper();
        p.setSize(80, 297);
        pag.setPaper(p);
        job.setPrintable(new ObjetoDeImpresion(id_fact),pag);
        if (job.printDialog()) {
            job.setPrintable(new ObjetoDeImpresion(id_fact),pag);
            try {
                job.print();
            } catch (PrinterException e) {
                System.out.println(e);
            }
        }
    }
    
    public void main() {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pag = new PageFormat();
        Paper p = new Paper();
        p.setSize(80, 297);
        pag.setPaper(p);
        job.setPrintable(new ObjetoDeImpresion(new ArrayList<>()),pag);
        if (job.printDialog()) {
            job.setPrintable(new ObjetoDeImpresion(new ArrayList<>()),pag);
            try {
                job.print();
            } catch (PrinterException e) {
                System.out.println(e);
            }
        }
    }
}
