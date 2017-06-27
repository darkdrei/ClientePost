/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package origami2.table;

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author CTesting
 */
public class CellRenderImage extends DefaultTableCellRenderer {
    public CellRenderImage() {
        super();
    }
    
    @Override
    public void setValue(Object value) {
    if (value == null) setText("");
       else{
            if(value.toString().equals("OK")){
                //setIcon(new javax.swing.ImageIcon("C:\\Users\\CTesting\\Pictures\\descarga.jpg"));//modf
                setBackground(Color.red);
            }else{
                //setIcon(new javax.swing.ImageIcon("C:\\Users\\CTesting\\Pictures\\descarga.jpg"));//modf
            }
        }
    }
}
