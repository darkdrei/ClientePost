/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.impresion;

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
import logica.db.BaseDao;
import logica.db.FabricaDaos;
import logica.db.Factura;

/**
 *
 * @author Toshiba
 */
public class ObjetoDeImpresion implements Printable {

    private int id_fac;
    String r = "";
    ArrayList<Objeto> b;
    private int n;

    public ObjetoDeImpresion(String r) {
        this.r = r;
    }

    public ObjetoDeImpresion(int id_fac) {
        this.id_fac = id_fac;
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
            if (this.getId_fac() > 0) {
                Graphics2D g2 = (Graphics2D) g;
                int x1 = (int) (300 * 0.3);
                int x2 = (int) (300 * 0.3) - 20;
                int y1 = (int) (100* 0.3);
                int centrado = 30;
                int inc = 0;
                int i = 10;
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                Font font = new Font("arial", Font.PLAIN, 8);
                g.setFont(font);
                /**
                 * *********************************************************************
                 */
                logica.db.BaseDao b = new logica.db.BaseDao();
                
                /**
                 * ********************************************************************
                 */
                //aqui podria dibujar alguna figura pero no la muestra	
                b = FabricaDaos.producirPersistenia(9);
                
                g.drawString("ORIGAMIS", x1 - centrado + 60, y1 + inc);
                inc += i;
                g.drawString("N 73189363-6", x1 - centrado + 55, y1 + inc);
                inc += i;
                g.drawString("Centro Historico calle", x1 - centrado+40, y1 + inc);
                inc += i;
                g.drawString("Gastelbondo", x1 - centrado + 10+45, y1 + inc);
                inc += i;
                g.drawString("Régimen Simplificado", x1 - centrado + 40, y1 + inc);
                inc += 12;
                g.drawString("Fecha " + ft.format(dNow), x1 - centrado - 69 + 30+40, y1 + inc);
                inc += 20;
                int menos = 30;
                g.drawString("Artículo", x1 - x2 - menos + 30, y1 + inc);
                g.drawString("Cantidad", x1 + 150 - x2 - menos, y1 + inc);
                g.drawString("Valor", x1 +230 - x2 - menos, y1 + inc);
                inc += (i + 5);
                g.drawString("========", x1 - x2 - menos + 30, y1 + inc);
                g.drawString("========", x1 + 150 - x2 - menos, y1 + inc);
                g.drawString("======", x1 + 230 - x2 - menos, y1 + inc);
                inc += (i);
                float t = 0;
                b = FabricaDaos.producirPersistenia(9);
                logica.DescripcionFactura des;
                for (Object re : b.getDatos(this.getId_fac())) {
                    des = ((logica.DescripcionFactura) re);
                    g.drawString(des.getPlato(), x1 - x2+10-menos+10, y1 + inc);
                    g.drawString("" + des.getCantidad(), x1 + 150 - x2 + 10-menos, y1 + inc);
                    g.drawString("" + des.getPrecio(), x1 + 220 - x2-menos, y1 + inc);
                    t += (des.getCantidad() * des.getPrecio());
                    inc += (i + 2);
                }
                /*for (Objeto l1 : b) {
                 g.drawString(l1.getNombre(), x1 - x2+10-menos+10, y1 + inc);
                 g.drawString("" + l1.getCantidad(), x1 + 90 - x2 + 10-menos, y1 + inc);
                 g.drawString("" + l1.getPrecio(), x1 + 170 - x2-menos, y1 + inc);
                 t += (l1.getCantidad() * l1.getPrecio());
                 inc += (i + 2);
                 }
                for (int j = 0; j < 200; j++) {
                    g.drawString("=", 5 + j, y1 + inc);
                }
                 inc += (i + 2);*/
                for (int j = 10; j < 250; j++) {
                    g.drawString("=", 5 + j, y1 + inc);
                }
                inc += (i + 2);
                b = FabricaDaos.producirPersistenia(8);
                Object res[][] = b.getObjetoMatriz(this.getId_fac());
                g.drawString("#fact " + ((int) res[0][0]), 200, (int) (100* 0.3));
                logica.db.ConteFac c = new logica.db.ConteFac();
                
                g.drawString("Sub total $", x1 + 90 - x2 - menos, y1 + inc);
                g.drawString("" + ((float) res[0][1]), x1 + 225 - x2 - menos, y1 + inc);
                inc += (i + 2);
                g.drawString(((String) res[0][3])+" $", x1 + 80 - x2 - menos, y1 + inc);
                g.drawString("" + (float) res[0][4], x1 + 230 - x2 - menos, y1 + inc);
                inc += (i + 2);
                g.drawString("Total $", x1 + 90 - x2 - menos, y1 + inc);
                g.drawString("" + ((float) res[0][5]), x1 + 225 - x2 - menos, y1 + inc);
                inc += (i + 2);/*roundTwoDecimals - redondear decimal*/

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
        DecimalFormat twoDForm = new DecimalFormat("#.#", simbolos);
        return "" + Float.valueOf(twoDForm.format(d));
    }

    public int getId_fac() {
        return id_fac;
    }

    public void setId_fac(int id_fac) {
        this.id_fac = id_fac;
    }

}
