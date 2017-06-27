/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.*;
import java.awt.print.*;
import java.awt.print.PrinterJob.*;
import java.awt.print.PageFormat.*;
import vista.Principal;

public class Imprimir {
    private Principal base;
    public Imprimir() {
    }

    public Imprimir(Principal base) {
        this.base = base;
    }
       
    
    public void main(String args) {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pag = new PageFormat();
        Paper p = new Paper();
        p.setSize(80, 297);
        pag.setPaper(p);
        //job.setPrintable(new ObjetoDeImpresion(base.getLista()),pag);
        if (job.printDialog()) {
            //job.setPrintable(new ObjetoDeImpresion(base.getLista()),pag);
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
        //job.setPrintable(new ObjetoDeImpresion(base.getLista()),pag);
        if (job.printDialog()) {
            //job.setPrintable(new ObjetoDeImpresion(base.getLista()),pag);
            try {
                job.print();
            } catch (PrinterException e) {
                System.out.println(e);
            }
        }
    }
}
