/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Toshiba
 */
public class ObjetoDeImpresion implements Printable {

    String r = "";
    ArrayList<Objeto> b;
    private int n;

    public ObjetoDeImpresion(String r) {
        this.r = r;
    }

    public ObjetoDeImpresion(ArrayList<Objeto> l) {
        this.b = l;
    }

    public ObjetoDeImpresion(ArrayList<Objeto> b, int n) {
        this.b = b;
        this.n = n;
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex == 0) {
            if (b.size() > 0) {
                Graphics2D g2 = (Graphics2D) g;
                int x1 = (int) (300 * 0.3);
                int x2 = (int) (300 * 0.3) - 20;
                int y1 = (int) (300 * 0.3);
                int centrado = 30;
                int inc = 0;
                int i = 10;
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                Font font = new Font("arial", Font.PLAIN, 8);
                g.setFont(font);
                //aqui podria dibujar alguna figura pero no la muestra	
                g.drawString("# fact " + this.n, 150, 50);
                g.drawString("ORIGAMIS", x1 - centrado+20, y1 + inc);
                inc += i;
                g.drawString("N 73189363-6", x1 - centrado + 5, y1 + inc);
                inc += i;
                g.drawString("Centro Historico calle", x1 - centrado , y1 + inc);
                inc += i;
                g.drawString("Gastelbondo", x1 - centrado+10, y1 + inc);
                inc += i;
                g.drawString("Régimen Simplificado", x1 - centrado + 0, y1 + inc);
                inc += 12;
                g.drawString("Fecha " + ft.format(dNow), x1 - centrado - 69+30, y1 + inc);
                inc += 20;
                int menos=30;
                g.drawString("Artículo", x1 - x2-menos+30, y1 + inc);
                g.drawString("Cantidad", x1 + 90 - x2-menos, y1 + inc);
                g.drawString("Valor", x1 + 170 - x2-menos, y1 + inc);
                inc += (i + 5);
                g.drawString("========", x1 - x2-menos+30, y1 + inc);
                g.drawString("========", x1 + 90 - x2-menos, y1 + inc);
                g.drawString("======", x1 + 170 - x2-menos, y1 + inc);
                inc += (i);
                float t = 0;
                for (Objeto l1 : b) {
                    g.drawString(l1.getNombre(), x1 - x2+10-menos+10, y1 + inc);
                    g.drawString("" + l1.getCantidad(), x1 + 90 - x2 + 10-menos, y1 + inc);
                    g.drawString("" + l1.getPrecio(), x1 + 170 - x2-menos, y1 + inc);
                    t += (l1.getCantidad() * l1.getPrecio());
                    inc += (i + 2);
                }
                for (int j = 0; j < 200; j++) {
                    g.drawString("=", 5 + j, y1 + inc);
                }
                inc += (i + 2);
                g.drawString("Sub total $", x1 + 90 - x2-menos, y1 + inc);
                g.drawString("" + t, x1 + 170 - x2-menos, y1 + inc);
                inc += (i + 2);
                g.drawString("Propina Sugerida(10%) $", x1 + 30 - x2-menos, y1 + inc);
                g.drawString("" + roundTwoDecimals((long)(t * 0.1)), x1 + 180 - x2-menos, y1 + inc);
                inc += (i + 2);
                g.drawString("Total $", x1 + 90 - x2-menos, y1 + inc);
                g.drawString("" + roundTwoDecimals((long)(t * 1.1)), x1 + 170 - x2-menos, y1 + inc);
                inc += (i + 2);

            } else {
                g.drawString(r, 100, 200);
            }
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }
    
    public String roundTwoDecimals(long d) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat twoDForm = new DecimalFormat("#.#",simbolos);
        return ""+Float.valueOf(twoDForm.format(d));
    }   

}
