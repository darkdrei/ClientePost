/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package origami2;

/**
 *
 * @author carotech
 */
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
 
public class CellRendererImagen extends DefaultTableCellRenderer {
 
    private static final long serialVersionUID = 1L;
 
    static class TableModel extends DefaultTableModel{
 
    private static final long serialVersionUID = 1L;
 
        public TableModel(){
            addColumn("Imagen");
            addColumn("A");
            addColumn("B");
            addColumn("C");
        }
    }
    public CellRendererImagen() {
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
                setIcon(new javax.swing.ImageIcon("C:\\Users\\CTesting\\Pictures\\descarga.jpg"));//modf
            }
        }
    }
    
    static class Frame extends JFrame{
        private static final long serialVersionUID = 1L;
 
        public Frame(){
 
            TableModel tableModel = new TableModel();
            JTable table = new JTable(tableModel);
            table.getColumnModel().getColumn(0).setCellRenderer(new CellRendererImagen());
            JScrollPane scrollPane=new JScrollPane(table);
            getContentPane().add(scrollPane);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(500,200);
 
            tableModel.addRow(new Object[]{"OK","A","B","C"});
            tableModel.addRow(new Object[]{"OK","A","B","C"});
            tableModel.addRow(new Object[]{"OK","A","B","C"});
            tableModel.addRow(new Object[]{"OK","A","B","C"});
 
            tableModel.addRow(new Object[]{"FAIL","A","B","C"});
            tableModel.addRow(new Object[]{"FAIL","A","B","C"});
        }
        
        public static void main(String args[]){
            SwingUtilities.invokeLater(new Runnable() {
 
                @Override
                public void run() {
                    new Frame().setVisible(true);
 
                }
            });
        }
    }
}
