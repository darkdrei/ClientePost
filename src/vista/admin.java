package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JList;
import logica.Validadora;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import logica.Configuracion;
import logica.Plato;
import logica.TemporalComposicion;
import logica.Tipo;
import logica.Trabajador;

import logica.db.Mesa;

import logica.db.FabricaDaos;

import logica.db.BaseDao;
import logica.db.Ingrediente;

/**
 *
 *
 * Manteca de cacao
 *
 * @autor dark
 *
 */
public class admin extends javax.swing.JFrame {
    
    private logica.db.BaseDao db = new logica.db.BaseDao();
    
    private Vector<Integer> id_categoria = new Vector<>();
    private Vector<Integer> id_tipos = new Vector<>();
    private Vector<Integer> id_platos = new Vector<>();
    private Vector<Integer> id_Ingredientes = new Vector<>();
    private Vector<Integer> id_Ingredientes_Inventario = new Vector<>();
    private Color color_tipo;
    private Object m[][];
    private boolean BANDERA_MODIFICARCOMPOSICION = false;
    private Vector<Integer> panel = new Vector<>();
    
    public Vector<Integer> getId_Ingredientes_Inventario() {
        return id_Ingredientes_Inventario;
    }
    
    public void setId_Ingredientes_Inventario(Vector<Integer> id_Ingredientes_Inventario) {
        this.id_Ingredientes_Inventario = id_Ingredientes_Inventario;
    }
    
    public Vector<Integer> getId_platos() {
        return id_platos;
    }
    
    public void setId_platos(Vector<Integer> id_platos) {
        this.id_platos = id_platos;
    }
    
    public Vector<Integer> getId_Ingredientes() {
        return id_Ingredientes;
    }
    
    public void setId_Ingredientes(Vector<Integer> id_Ingredientes) {
        this.id_Ingredientes = id_Ingredientes;
    }
    
    public Color getColor_tipo() {
        return color_tipo;
    }
    
    public Object[][] convertirMatriz(Object[][] o, int n) {
        Object m[][] = new Object[o.length][n];
        for (int i = 0; i < o.length; i++) {
            for (int j = 0; j < o[i].length; j++) {
                m[i][j] = o[i][j];
            }
            m[i][n - 1] = new Boolean(false);
        }
        return m;
    }
    
    public void setColor_tipo(Color color_tipo) {
        this.color_tipo = color_tipo;
    }

    /**
     *
     * Creates new form admin
     *
     */
    public admin() {
        
        initComponents();
        
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ima/barra/logo2.png"));
        setIconImage(icon);
//        setVisible(true);
        tablaRegistro.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/mesa2.png")));
        tablaRegistro.setIconAt(1, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/categoria.png")));
        tablaRegistro.setIconAt(2, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/tipo.png")));
        tablaRegistro.setIconAt(3, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/plato.png")));
        tablaRegistro.setIconAt(4, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/trabajador.png")));
        tablaRegistro.setIconAt(5, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/ingredients.png")));
        tablaRegistro.setIconAt(6, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/organization.png")));
        tablaRegistro.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //IMAGENES PARA EL PANEL ADMINISTRADOR
        jTabbedPane1.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/ima/estadistica_menu.png")));
        jTabbedPane1.setIconAt(1, new javax.swing.ImageIcon(getClass().getResource("/ima/form_menu.png")));
        jTabbedPane1.setIconAt(2, new javax.swing.ImageIcon(getClass().getResource("/ima/edit_factura_menu.png")));
        jTabbedPane1.setIconAt(3, new javax.swing.ImageIcon(getClass().getResource("/ima/inventario_menu.png")));
        jTabbedPane1.setIconAt(4, new javax.swing.ImageIcon(getClass().getResource("/ima/configuracion.png")));
        jTabbedPane1.setIconAt(5, new javax.swing.ImageIcon(getClass().getResource("/ima/email.png")));
        //TABLA DE ESTADISTICAS 
        jTabbedPane2.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/estadistica_menu.png")));
        actualizarTabla(1);
//        jtFactura.add("Modificar",new facturaadmin());
        jtFactura.add("Modificar Factura", new adminfactura());
        jtFactura.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/edit_factura_menu.png")));

        //TABLA DE INVENTARIO
        ptInventario.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/existencias.png")));
        ptInventario.setIconAt(1, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/surtir.png")));
        //
        jTabbedPane3.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/ima/barra/confi_submenu.png")));
    }
    
    public static class GenerarTabla {
        
        private static final GenerarTabla g = new GenerarTabla();
        
        private GenerarTabla() {
            
        }
        
        public static Object[][] producirResulTabla(int op) {
            logica.db.Reporte r = new logica.db.Reporte();
            if (op == 1) {
                return r.getObjetoMatriz(r.getReporteDia());
            } else if (op == 3) {
                return r.getObjetoMatrizNormal(r.getReporteDiaTipo());
            } else if (op == 4) {
                return r.getObjetoMatrizNormal(r.getTotalDiaPlato());
            } else if (op == 2) {
                return r.getObjetoMatrizNormal(r.getTotalDiaCategoria());
            }
            return new Object[1][2];
        }
        
        public static String[] producirCabeceraTabla(int op) {
            logica.db.Reporte r = new logica.db.Reporte();
            return r.cabecerasTablas(op);
        }
    }
    
    public void actualizarTabla(int op) {
        logica.db.Reporte r = new logica.db.Reporte();
        jtResultados.setModel(new javax.swing.table.DefaultTableModel(
                GenerarTabla.producirResulTabla(op),
                GenerarTabla.producirCabeceraTabla(op)
        ) {
            
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Float.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, false
            };
            
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            @Override
            public void setValueAt(Object value, int row, int col) {
            }
            
        });
        this.pack();
        this.repaint();
    }
    
    public BaseDao getDb() {
        
        return db;
        
    }
    
    public void setDb(BaseDao db) {
        this.db = db;
    }
    
    public void updateTabla(ArrayList<logica.DescripcionFactura> d) {
        
    }

    /**
     *
     * This method is called from within the constructor to initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is always
     *
     * regenerated by the Form Editor.
     *
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        optPago = new javax.swing.JRadioButton();
        optTipo = new javax.swing.JRadioButton();
        optCategoria = new javax.swing.JRadioButton();
        optPlato = new javax.swing.JRadioButton();
        jpContedorTabla = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtResultados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        tablaRegistro = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombeMesa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaMesa = new javax.swing.JTextArea();
        btnSaveMesa = new javax.swing.JButton();
        btnClearMesa = new javax.swing.JButton();
        btnNewMesa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtMesa = new javax.swing.JTable();
        btnEliminarMesa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombeCategoria = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaCategoria = new javax.swing.JTextArea();
        btnSaveCategoria = new javax.swing.JButton();
        btnCancelCategoria = new javax.swing.JButton();
        btnNewCateroria = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtCategoria = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombeTipo = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaTipo = new javax.swing.JTextArea();
        btnGuardarTipo = new javax.swing.JButton();
        btnLimpiarTipo = new javax.swing.JButton();
        btnNuevoTipo = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtColorTipo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        btnModificarTipo = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtTipo = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombrePlato = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaPlato = new javax.swing.JTextArea();
        btnSavePlato = new javax.swing.JButton();
        btnClearPlato = new javax.swing.JButton();
        btnNewPlato = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbPlato = new javax.swing.JComboBox();
        txtPrecioPlato = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnEditPlato = new javax.swing.JButton();
        btnNuevoPlato = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtPlato = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCedulaTrabajador = new javax.swing.JTextField();
        txtNombreTrabajador = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        areaTrabajador = new javax.swing.JTextArea();
        btnSaveTrabajador = new javax.swing.JButton();
        btnClearTrabajador = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTelefonoTrabajador = new javax.swing.JTextField();
        txtCorreoTrabajador = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtApellidosTrabajador = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbclave = new javax.swing.JLabel();
        lbClave2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        clave1 = new javax.swing.JPasswordField();
        clave2 = new javax.swing.JPasswordField();
        btnModificarTrabajador = new javax.swing.JButton();
        btnNuevoTrabajador = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtTrabajador = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtNombreIngrediente = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        areaIngrediente = new javax.swing.JTextArea();
        btnSaveMesa1 = new javax.swing.JButton();
        btnClearMesa1 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        rbngrediente = new javax.swing.JRadioButton();
        rbBebida = new javax.swing.JRadioButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        tablaIngredientes = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        lbPlatoComposicion = new javax.swing.JLabel();
        cbPlatoComposicion = new javax.swing.JComboBox();
        jPanel24 = new javax.swing.JPanel();
        btnClearComposisicon = new javax.swing.JButton();
        btnSaveComposicion = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        cbIngredienteComposicion = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        txtCantidadComposicion = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        tablaComposicionTemporal = new javax.swing.JTable();
        btnEliminarComposicion = new javax.swing.JButton();
        btnNuevoComposicion = new javax.swing.JButton();
        btnGuardarComposicion = new javax.swing.JButton();
        lbTemporalPlato = new javax.swing.JLabel();
        btnGuardarEdicionComposicion = new javax.swing.JButton();
        btnNuevoComposicionInvisible = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tablaComposicion = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtFactura = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        ptInventario = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtBuscarExistencia = new javax.swing.JTextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        listaSurtido = new javax.swing.JList();
        txtBuscarSurtir = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtIngredienteSurtir = new javax.swing.JTextField();
        btnLimpiarSurtir = new javax.swing.JButton();
        btnQuitarSurtir = new javax.swing.JButton();
        btnAgregarSurtir = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtTemporalSurtidoResultado = new javax.swing.JTextField();
        txtNombreDecimal = new javax.swing.JTextField();
        lbNombreExistencia = new javax.swing.JLabel();
        lbTemporalSurtir = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        txtCorreoConfiguracion = new javax.swing.JTextField();
        txtUsuarioAdmin = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtIngredienteAdmin = new javax.swing.JTextField();
        txtUnidadesAdmin = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel33 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Origamis Admin");
        setResizable(false);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setName("Registro"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
        });

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione Tipo de Reporte")));

        buttonGroup1.add(optPago);
        optPago.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        optPago.setSelected(true);
        optPago.setText("Opcion pago");
        optPago.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optPagoStateChanged(evt);
            }
        });
        optPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optPagoActionPerformed(evt);
            }
        });

        buttonGroup1.add(optTipo);
        optTipo.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        optTipo.setText("Tipo");
        optTipo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optTipoStateChanged(evt);
            }
        });
        optTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optTipoActionPerformed(evt);
            }
        });

        buttonGroup1.add(optCategoria);
        optCategoria.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        optCategoria.setText("Categoria");
        optCategoria.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optCategoriaStateChanged(evt);
            }
        });
        optCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optCategoriaActionPerformed(evt);
            }
        });

        buttonGroup1.add(optPlato);
        optPlato.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        optPlato.setText("Plato");
        optPlato.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optPlatoStateChanged(evt);
            }
        });
        optPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optPlatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(optPago)
                .addGap(18, 18, 18)
                .addComponent(optCategoria)
                .addGap(27, 27, 27)
                .addComponent(optTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optPlato)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optPago)
                    .addComponent(optTipo)
                    .addComponent(optCategoria)
                    .addComponent(optPlato))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jtResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jtResultados);

        javax.swing.GroupLayout jpContedorTablaLayout = new javax.swing.GroupLayout(jpContedorTabla);
        jpContedorTabla.setLayout(jpContedorTablaLayout);
        jpContedorTablaLayout.setHorizontalGroup(
            jpContedorTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContedorTablaLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jpContedorTablaLayout.setVerticalGroup(
            jpContedorTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContedorTablaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpContedorTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Reporte del Dia", jPanel17);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Estadisticas", jPanel16);

        tablaRegistro.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tablaRegistroStateChanged(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel2.setText("Descripcion");

        jLabel3.setText("Nombre");
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        areaMesa.setColumns(20);
        areaMesa.setRows(5);
        jScrollPane1.setViewportView(areaMesa);

        btnSaveMesa.setText("Guardar");
        btnSaveMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMesaActionPerformed(evt);
            }
        });

        btnClearMesa.setText("Limpiar");
        btnClearMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMesaActionPerformed(evt);
            }
        });

        btnNewMesa.setText("Nuevo");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombeMesa))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnSaveMesa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClearMesa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNewMesa)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombeMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveMesa)
                    .addComponent(btnClearMesa)
                    .addComponent(btnNewMesa))
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jtMesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtMesa);

        btnEliminarMesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/delete_item.png"))); // NOI18N
        btnEliminarMesa.setText("Eliminar");
        btnEliminarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminarMesa)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnEliminarMesa)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaRegistro.addTab("Mesa", jPanel6);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel5.setText("Descripcion");

        jLabel6.setText("Nombre");
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        areaCategoria.setColumns(20);
        areaCategoria.setRows(5);
        jScrollPane3.setViewportView(areaCategoria);

        btnSaveCategoria.setText("Guardar");
        btnSaveCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCategoriaActionPerformed(evt);
            }
        });

        btnCancelCategoria.setText("Limpiar");
        btnCancelCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelCategoriaActionPerformed(evt);
            }
        });

        btnNewCateroria.setText("Nuevo");
        btnNewCateroria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCateroriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btnSaveCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelCategoria)
                                .addGap(2, 2, 2)
                                .addComponent(btnNewCateroria)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveCategoria)
                    .addComponent(btnCancelCategoria)
                    .addComponent(btnNewCateroria))
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jtCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtCategoria);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/delete_item.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaRegistro.addTab("Categoria", jPanel3);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel8.setText("Descripcion");

        jLabel9.setText("Nombre");
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        areaTipo.setColumns(20);
        areaTipo.setRows(5);
        jScrollPane5.setViewportView(areaTipo);

        btnGuardarTipo.setText("Guardar");
        btnGuardarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoActionPerformed(evt);
            }
        });

        btnLimpiarTipo.setText("Limpiar");
        btnLimpiarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTipoActionPerformed(evt);
            }
        });

        btnNuevoTipo.setText("Nuevo");

        jLabel16.setText("Color");

        txtColorTipo.setEditable(false);
        txtColorTipo.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Color");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Categoria");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnModificarTipo.setText("Modificar");
        btnModificarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(36, 36, 36)
                                .addComponent(txtColorTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnGuardarTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiarTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoTipo))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtColorTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarTipo)
                    .addComponent(btnLimpiarTipo)
                    .addComponent(btnNuevoTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarTipo)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jtTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jtTipo);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/delete_item.png"))); // NOI18N
        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/edit_item.png"))); // NOI18N
        jButton5.setText("Modificar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaRegistro.addTab("Tipo", jPanel4);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel11.setText("Descripcion");

        jLabel12.setText("Nombre");
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        areaPlato.setColumns(20);
        areaPlato.setRows(5);
        jScrollPane7.setViewportView(areaPlato);

        btnSavePlato.setText("Guardar");
        btnSavePlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePlatoActionPerformed(evt);
            }
        });

        btnClearPlato.setText("Limpiar");

        btnNewPlato.setText("Nuevo");
        btnNewPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPlatoActionPerformed(evt);
            }
        });

        jLabel17.setText("Tipo");

        cbPlato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Precio");
        jLabel22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnEditPlato.setText("Modificar");
        btnEditPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPlatoActionPerformed(evt);
            }
        });

        btnNuevoPlato.setText("Nuevo");
        btnNuevoPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPlatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(btnNuevoPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEditPlato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                    .addComponent(btnSavePlato)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnClearPlato)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnNewPlato))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(cbPlato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(txtNombrePlato))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPrecioPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbPlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombrePlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioPlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSavePlato)
                    .addComponent(btnClearPlato)
                    .addComponent(btnNewPlato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditPlato)
                    .addComponent(btnNuevoPlato))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jtPlato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jtPlato);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/delete_item.png"))); // NOI18N
        jButton6.setText("Eliminar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/edit_item.png"))); // NOI18N
        jButton7.setText("Midificar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaRegistro.addTab("Plato & Bebidas", jPanel5);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel13.setText("Cedula");

        jLabel14.setText("Direccion");

        jLabel15.setText("Nombre");
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        txtCedulaTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaTrabajadorActionPerformed(evt);
            }
        });

        areaTrabajador.setColumns(20);
        areaTrabajador.setRows(5);
        jScrollPane9.setViewportView(areaTrabajador);

        btnSaveTrabajador.setText("Guardar");
        btnSaveTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTrabajadorActionPerformed(evt);
            }
        });

        btnClearTrabajador.setText("Limpiar");
        btnClearTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTrabajadorActionPerformed(evt);
            }
        });

        jLabel19.setText("Telefono");

        jLabel20.setText("Correo");

        jLabel21.setText("Apellidos");

        jLabel4.setText("Usuario");

        lbclave.setText("Clave");

        lbClave2.setText("Repetir clave");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        btnModificarTrabajador.setText("Modificar");
        btnModificarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTrabajadorActionPerformed(evt);
            }
        });

        btnNuevoTrabajador.setText("Nuevo");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(lbclave))
                            .addGap(15, 15, 15)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(clave1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(txtUsuario)))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbClave2)
                                .addComponent(jLabel13)
                                .addComponent(jLabel15)
                                .addComponent(jLabel21)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombreTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCedulaTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(clave2))
                                .addComponent(txtApellidosTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel20)))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCorreoTrabajador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(txtTelefonoTrabajador)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNuevoTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnClearTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSaveTrabajador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnModificarTrabajador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(21, 21, 21))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbclave))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbClave2)
                    .addComponent(clave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedulaTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidosTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtTelefonoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCorreoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)))
                .addGap(4, 4, 4)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveTrabajador)
                    .addComponent(btnClearTrabajador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarTrabajador)
                    .addComponent(btnNuevoTrabajador))
                .addContainerGap())
        );

        jtTrabajador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jtTrabajador);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/delete_item.png"))); // NOI18N
        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/edit_item.png"))); // NOI18N
        jButton12.setText("Modificar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/edit_user_item.png"))); // NOI18N
        jButton14.setText("Editar Clave Trabajador");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12)
                            .addComponent(jButton9)
                            .addComponent(jButton14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaRegistro.addTab("Trabajador", jPanel7);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel26.setText("Descripcion");

        jLabel27.setText("Nombre");
        jLabel27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        areaIngrediente.setColumns(20);
        areaIngrediente.setRows(5);
        jScrollPane14.setViewportView(areaIngrediente);

        btnSaveMesa1.setText("Guardar");
        btnSaveMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMesa1ActionPerformed(evt);
            }
        });

        btnClearMesa1.setText("Limpiar");
        btnClearMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMesa1ActionPerformed(evt);
            }
        });

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione el Tipo"));

        buttonGroup2.add(rbngrediente);
        rbngrediente.setSelected(true);
        rbngrediente.setText("Ingrediente");

        buttonGroup2.add(rbBebida);
        rbBebida.setText("Bebida");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbBebida)
                    .addComponent(rbngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(rbngrediente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(rbBebida))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(btnClearMesa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSaveMesa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreIngrediente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveMesa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClearMesa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(164, 164, 164))
        );

        tablaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane15.setViewportView(tablaIngredientes);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/delete_item.png"))); // NOI18N
        jButton10.setText("Eliminar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/edit_factura_menu.png"))); // NOI18N
        jButton11.setText("Ver");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addGap(19, 19, 19))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10)
                            .addComponent(jButton11))
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaRegistro.addTab("Ingredientes y Bebidas", jPanel18);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        lbPlatoComposicion.setText("Plato");
        lbPlatoComposicion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        cbPlatoComposicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPlatoComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPlatoComposicionActionPerformed(evt);
            }
        });

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingredientes"));

        btnClearComposisicon.setText("Limpiar");
        btnClearComposisicon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearComposisiconActionPerformed(evt);
            }
        });

        btnSaveComposicion.setText("Guardar");
        btnSaveComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveComposicionActionPerformed(evt);
            }
        });

        jLabel28.setText("Ingrediente");

        cbIngredienteComposicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel30.setText("Cantidad (gr o ml)");

        txtCantidadComposicion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidadComposicion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCantidadComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadComposicionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnClearComposisicon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSaveComposicion))
                            .addComponent(txtCantidadComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbIngredienteComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbIngredienteComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtCantidadComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveComposicion)
                    .addComponent(btnClearComposisicon))
                .addGap(20, 20, 20))
        );

        tablaComposicionTemporal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Cantidad", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane18.setViewportView(tablaComposicionTemporal);

        btnEliminarComposicion.setText("Eliminar");
        btnEliminarComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarComposicionActionPerformed(evt);
            }
        });

        btnNuevoComposicion.setText("Limpiar");
        btnNuevoComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoComposicionActionPerformed(evt);
            }
        });

        btnGuardarComposicion.setText("Guardar");
        btnGuardarComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComposicionActionPerformed(evt);
            }
        });

        lbTemporalPlato.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnGuardarEdicionComposicion.setText("Guardar Edicion");
        btnGuardarEdicionComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEdicionComposicionActionPerformed(evt);
            }
        });

        btnNuevoComposicionInvisible.setText("Nuevo");
        btnNuevoComposicionInvisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoComposicionInvisibleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(lbPlatoComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(cbPlatoComposicion, 0, 245, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(lbTemporalPlato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoComposicionInvisible)
                                .addGap(7, 7, 7)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(btnNuevoComposicion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardarComposicion))
                            .addComponent(btnGuardarEdicionComposicion))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarComposicion)
                .addGap(37, 37, 37))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPlatoComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbPlatoComposicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevoComposicion)
                        .addComponent(btnGuardarComposicion)))
                .addGap(1, 1, 1)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTemporalPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardarEdicionComposicion)
                        .addComponent(btnNuevoComposicionInvisible)))
                .addGap(11, 11, 11)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarComposicion)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tablaComposicion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane17.setViewportView(tablaComposicion);

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton13.setText("Modificar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        tablaRegistro.addTab("Composicion Plato", jPanel22);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tablaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(tablaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Registro", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Factura", jPanel2);

        ptInventario.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ptInventarioStateChanged(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setText("Nombre del Ingrediente");

        txtBuscarExistencia.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtBuscarExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarExistenciaKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtBuscarExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ptInventario.addTab("Existencias", jPanel26);

        jPanel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel32.setText("Ingrediente");

        listaSurtido.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaSurtido.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaSurtidoValueChanged(evt);
            }
        });
        jScrollPane19.setViewportView(listaSurtido);

        txtBuscarSurtir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarSurtirActionPerformed(evt);
            }
        });
        txtBuscarSurtir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarSurtirKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarSurtirKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarSurtir, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addComponent(jScrollPane19))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtBuscarSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19)
                .addContainerGap())
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel33.setText("Digite cantidad a surtir de");

        txtIngredienteSurtir.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnLimpiarSurtir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/edit-clear.png"))); // NOI18N
        btnLimpiarSurtir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSurtirActionPerformed(evt);
            }
        });

        btnQuitarSurtir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/1449713383_minus.png"))); // NOI18N
        btnQuitarSurtir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarSurtirActionPerformed(evt);
            }
        });

        btnAgregarSurtir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/1449713349_plus.png"))); // NOI18N
        btnAgregarSurtir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSurtirActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel34.setText("Existencia");

        txtTemporalSurtidoResultado.setEditable(false);
        txtTemporalSurtidoResultado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTemporalSurtidoResultado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTemporalSurtidoResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTemporalSurtidoResultadoActionPerformed(evt);
            }
        });

        txtNombreDecimal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbTemporalSurtir.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel34)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(txtTemporalSurtidoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreDecimal)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(btnLimpiarSurtir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIngredienteSurtir, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombreExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTemporalSurtir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNombreExistencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTemporalSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIngredienteSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiarSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitarSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarSurtir, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTemporalSurtidoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreDecimal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        ptInventario.addTab("Surtir", jPanel27);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ptInventario)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ptInventario)
        );

        jTabbedPane1.addTab("Inventario", jPanel25);

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/barra/update.png"))); // NOI18N
        jButton15.setText("Actualizar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel29.setText("Clave");

        jLabel10.setText("Usuario");

        jLabel7.setText("Correo Electronico");

        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });

        jLabel35.setText("Min existencia Ingrediente");

        jLabel36.setText("Min existencia Bebida");

        txtIngredienteAdmin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIngredienteAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngredienteAdminActionPerformed(evt);
            }
        });

        txtUnidadesAdmin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel37.setText("KG");

        jLabel38.setText("Udades");

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder("Notificacion de Correo"));

        buttonGroup3.add(jRadioButton1);
        jRadioButton1.setText("Despues de cada Venta");

        buttonGroup3.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Enviar a Solicitud");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCorreoConfiguracion)
                            .addComponent(txtUsuarioAdmin)
                            .addComponent(txtClave, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUnidadesAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addComponent(txtIngredienteAdmin, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCorreoConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtIngredienteAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(6, 6, 6)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtUnidadesAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Admin", jPanel31);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuracion", jPanel19);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("", jPanel33);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtCedulaTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaTrabajadorActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_txtCedulaTrabajadorActionPerformed
    

    private void btnNewCateroriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCateroriaActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_btnNewCateroriaActionPerformed
    

    private void tablaRegistroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tablaRegistroStateChanged
        // TODO add your handling code here:
        Object mar[][];
        int resp = tablaRegistro.getSelectedIndex();
        if (resp == 0) {
            actualizarModificacionBase(1, 4);
            modificarTablamesa();
        } else if (resp == 1) {
            actualizarModificacionBase(2, 4);
            modificarTablaCategoria();
        } else if (resp == 2) {
            cargarIdDeCategorias(cbTipo);
            actualizarModificacionBase(3, 6);
            deshabilitarBotonesTipo(true);
            modificarTablaTipo();
        } else if (resp == 3) {
            cargarIdTipos();
            actualizarModificacionBase(4, 6);
            magiaButtonPlato(true);
            modificarTablaPlato();
        } else if (resp == 4) {
            actualizarModificacionBase(7, 6);
            modificarTablaTrabajador();
            magiaButtonTrabajador(true);
            deshabilitarControlPass(true);
        } else if (resp == 5) {
            actualizarModificacionBase(10, 4);
            modificarTablaIngrediente();
        } else if (resp == 6) {
            synPanelComposicion();
        }

    }//GEN-LAST:event_tablaRegistroStateChanged
    
    public void synPanelComposicion() {
        setDb(FabricaDaos.producirPersistenia(4));
        cbPlatoComposicion.setModel(new javax.swing.DefaultComboBoxModel(getDb().getNombres()));
        setId_platos(getDb().getIds());
        setDb(FabricaDaos.producirPersistenia(10));
        cbIngredienteComposicion.setModel(new javax.swing.DefaultComboBoxModel(getDb().getNombres()));
        setId_Ingredientes(getDb().getIds());
        cbPlatoComposicion.setSelectedIndex(0);
        habilitarComposicion(false);
        actualizarModificacionBase(11, 3);
        modificarTablaComposicion();
        magiaDelCambio(true);
    }
    
    public void magiaDelCambio(boolean b) {
        lbTemporalPlato.setVisible(!b);
        btnGuardarEdicionComposicion.setVisible(!b);
        btnNuevoComposicionInvisible.setVisible(!b);
        cbPlatoComposicion.setVisible(b);
        btnNuevoComposicion.setVisible(b);
        btnGuardarComposicion.setVisible(b);
        lbPlatoComposicion.setVisible(b);
    }
    
    public void actualizarModificacionBase(int id_obj, int num_col) {
        setDb(FabricaDaos.producirPersistenia(id_obj));
        m = convertirMatriz(getDb().getObjetoMatriz(), num_col);
    }
    
    public void producirMensaje(String a, String b, int c) {
        
        JOptionPane.showMessageDialog(this, a, b, c);
        
    }
    

    private void btnSaveMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMesaActionPerformed

        // TODO add your handling code here:
        if (txtNombeMesa.getText().equals("")) {
            
            producirMensaje("Debe digitar nombre 0 numero para mesa", "Registrar Mesa", JOptionPane.INFORMATION_MESSAGE);
            
            return;
            
        }
        
        db = FabricaDaos.producirPersistenia(1);
        
        ArrayList<Object> o = new ArrayList<>();
        
        o.add(txtNombeMesa.getText());
        
        Object r[] = db.validInsert(o);
        
        if (!(boolean) r[0]) {
            db.insertarDato(new logica.Mesa(txtNombeMesa.getText(), areaMesa.getText()));
            actualizarModificacionBase(1, 4);
            modificarTablamesa();
            limpiarMesa();
            return;
        }
        
        producirMensaje((String) r[1], "Registrar Mesa", JOptionPane.INFORMATION_MESSAGE);
        
        txtNombeMesa.setText("");
        
        txtNombeMesa.requestFocusInWindow();
        

    }//GEN-LAST:event_btnSaveMesaActionPerformed
    

    private void btnClearMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMesaActionPerformed

        // TODO add your handling code here:
        limpiarMesa();

    }//GEN-LAST:event_btnClearMesaActionPerformed
    

    private void btnCancelCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelCategoriaActionPerformed

        // TODO add your handling code here:
        limpiarCategoria();

    }//GEN-LAST:event_btnCancelCategoriaActionPerformed
    

    private void btnSaveCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCategoriaActionPerformed

        // TODO add your handling code here:
        // TODO add your handling code here:
        if (txtNombeCategoria.getText().equals("")) {
            
            producirMensaje("Debe digitar nombre 0 numero para categoria", "Registrar Categoria", JOptionPane.INFORMATION_MESSAGE);
            
            return;
            
        }
        
        db = FabricaDaos.producirPersistenia(2);
        
        ArrayList<Object> o = new ArrayList<>();
        
        o.add(txtNombeCategoria.getText());
        
        Object r[] = db.validInsert(o);
        
        if (!(boolean) r[0]) {
            
            db.insertarDato(new logica.Categoria(txtNombeCategoria.getText(), areaCategoria.getText()));
            actualizarModificacionBase(2, 4);
            modificarTablaCategoria();
            
            limpiarCategoria();
            
            return;
            
        }
        
        producirMensaje((String) r[1], "Registrar Categoria", JOptionPane.INFORMATION_MESSAGE);
        
        txtNombeCategoria.setText("");
        
        txtNombeCategoria.requestFocusInWindow();

    }//GEN-LAST:event_btnSaveCategoriaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setColor_tipo(null);
        choise c = new choise();
        c.setBase_admin(this);
        this.setEnabled(false);
        c.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGuardarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoActionPerformed
        // TODO add your handling code here:
        if (cbTipo.getSelectedIndex() > 0) {
            db = FabricaDaos.producirPersistenia(3);
            ArrayList<Object> l = new ArrayList<>();
            l.add(txtNombeTipo.getText());
            Object r[] = db.validInsert(l, 1);
            if (!((boolean) r[0])) {
                if (getColor_tipo() != null) {
                    db.insertarDato(new logica.Tipo(id_categoria.get(cbTipo.getSelectedIndex()), txtNombeTipo.getText(), areaTipo.getText(), getColor_tipo()));
                    actualizarModificacionBase(3, 6);
                    deshabilitarBotonesTipo(true);
                    modificarTablaTipo();
                } else {
                    producirMensaje("Seleccione un color", "TIPO", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                producirMensaje(((String) r[1]), "TIPO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            this.producirMensaje("Seleccione una categoria.", "TIPO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarTipoActionPerformed

    private void btnLimpiarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTipoActionPerformed
        // TODO add your handling code here:
        limpiarTipo();
    }//GEN-LAST:event_btnLimpiarTipoActionPerformed

    private void btnSavePlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePlatoActionPerformed
        // TODO add your handling code here:
        if (cbPlato.getSelectedIndex() > 0) {
            db = FabricaDaos.producirPersistenia(4);
            ArrayList<Object> l = new ArrayList<>();
            l.add(txtNombrePlato.getText());
            Object r[] = db.validInsert(l);
            if (!((boolean) r[0])) {
                if (logica.Validadora.validarCadena(4, txtPrecioPlato.getText())) {
                    db.insertarDato(new logica.Plato(txtNombrePlato.getText(), areaPlato.getText(), id_tipos.get(cbPlato.getSelectedIndex()), Float.parseFloat(txtPrecioPlato.getText())));
                    limpiarPlato();
                    actualizarModificacionBase(4, 6);
                    magiaButtonPlato(true);
                    modificarTablaPlato();
                    return;
                } else {
                    producirMensaje("Debe dijitar solo numeros.", "PLATO", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                producirMensaje(((String) r[1]), "PLATO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            producirMensaje("Debe seleccionar un tipo para el plato.", null, WIDTH);
            cbTipo.setSelectedIndex(0);
            cbTipo.requestFocusInWindow();
        }
        actualizarModificacionBase(4, 6);
        magiaButtonPlato(true);
        modificarTablaPlato();
    }//GEN-LAST:event_btnSavePlatoActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
        char c = (char) evt.getKeyChar();
        int e = evt.getKeyCode();
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void btnSaveTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTrabajadorActionPerformed
        // TODO add your handling code here:
        db = FabricaDaos.producirPersistenia(6);
        if (!txtUsuario.getText().equals("")) {
            ArrayList<Object> o = new ArrayList<>();
            o.add(txtUsuario.getText());
            Object r[] = db.validInsert(o);
            if (!(boolean) r[0]) {
                if (clave1.getPassword().length != 0 && clave2.getPassword().length != 0) {
                    String c1 = String.valueOf(clave1.getPassword());
                    String c2 = String.valueOf(clave2.getPassword());
                    if (c1.equalsIgnoreCase(c2)) {
                        if (Validadora.validarCadena(0, txtCedulaTrabajador.getText())) {
                            db = FabricaDaos.producirPersistenia(7);
                            o.clear();
                            o.add(txtCedulaTrabajador.getText());
                            r = db.validInsert(o);
                            if (!(boolean) r[0]) {
                                if (!txtNombreTrabajador.getText().equalsIgnoreCase("")) {
                                    if (!txtApellidosTrabajador.getText().equalsIgnoreCase("")) {
                                        if (!areaTrabajador.getText().equalsIgnoreCase("")) {
                                            db.insertarDato(new logica.Trabajador(txtUsuario.getText(),
                                                    String.valueOf(clave1.getPassword()),
                                                    txtNombreTrabajador.getText(),
                                                    txtApellidosTrabajador.getText(),
                                                    txtCedulaTrabajador.getText(),
                                                    areaTrabajador.getText(),
                                                    txtTelefonoTrabajador.getText(),
                                                    txtCorreoTrabajador.getText()));
                                            limpiarTrabajador();
                                            actualizarModificacionBase(7, 6);
                                            modificarTablaTrabajador();
                                            magiaButtonTrabajador(true);
                                        } else {
                                            producirMensaje("La direccion es requerida.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                                            areaTrabajador.requestFocusInWindow();
                                        }
                                    } else {
                                        producirMensaje("Digite los apellidos, son requeridos.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                                        txtApellidosTrabajador.requestFocusInWindow();
                                    }
                                } else {
                                    producirMensaje("Digite el nombre del trabajador es requerido.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                                    txtNombreTrabajador.requestFocusInWindow();
                                }
                            } else {
                                producirMensaje((String) r[1], "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                                txtCedulaTrabajador.setText("");
                                txtCedulaTrabajador.requestFocusInWindow();
                            }
                        } else {
                            producirMensaje("La cedula solo requiere digitos.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                            txtCedulaTrabajador.setText("");
                            txtCedulaTrabajador.requestFocusInWindow();
                        }
                        
                    } else {
                        clave1.setText("");
                        clave2.setText("");
                        producirMensaje("Los campos de clave y confirmacion deben ser iguales.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                        clave1.requestFocusInWindow();
                    }
                } else {
                    producirMensaje("Los campos de clave y confirmacion son requeridos.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                    clave1.requestFocusInWindow();
                }
            } else {
                producirMensaje("El nombre de usuario se encuentra registrado.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                txtUsuario.requestFocusInWindow();
            }
        } else {
            producirMensaje("El usuario es requerido.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
            txtUsuario.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnSaveTrabajadorActionPerformed

    private void btnSaveMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMesa1ActionPerformed
        // TODO add your handling code here:
        if (txtNombreIngrediente.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Digite el nombre para el ingrediente.", "Registrar Ingrediente", JOptionPane.INFORMATION_MESSAGE);
            txtNombreIngrediente.setText("");
            txtNombreIngrediente.requestFocusInWindow();
        }
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(txtNombreIngrediente.getText());
        setDb(FabricaDaos.producirPersistenia(10));
        Object r[] = getDb().validInsert(obj);
        if ((boolean) r[0]) {
            JOptionPane.showMessageDialog(this, "El nombre para el ingrediente se encuentrado.", "Registrar Ingrediente", JOptionPane.INFORMATION_MESSAGE);
            txtNombreIngrediente.setText("");
            txtNombreIngrediente.requestFocusInWindow();
            return;
        }
        getDb().insertarDato(new logica.Ingrediente(txtNombreIngrediente.getText(), areaIngrediente.getText(), rbngrediente.isSelected() ? 0 : 1));
        limpiarIngrediente();
        actualizarModificacionBase(10, 4);
        modificarTablaIngrediente();
    }//GEN-LAST:event_btnSaveMesa1ActionPerformed
    
    public void limpiarIngrediente() {
        txtNombreIngrediente.setText("");
        areaIngrediente.setText("");
    }
    private void btnClearMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMesa1ActionPerformed
        // TODO add your handling code here:
        limpiarIngrediente();
    }//GEN-LAST:event_btnClearMesa1ActionPerformed
    private Object matrizTemporalComposicion[][] = new Object[0][4];
    private void btnSaveComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveComposicionActionPerformed
        // TODO add your handling code here:
        if (!Validadora.validarCadena(4, txtCantidadComposicion.getText())) {
            JOptionPane.showMessageDialog(this, "En la cantidad se requiere un valor real con \".\".\nEjemplo : 45.12", "Composicion de Plato", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (validarTemporalCOmposicion(getId_Ingredientes().get(cbIngredienteComposicion.getSelectedIndex() - 1))) {
            JOptionPane.showMessageDialog(this, "El ingrediente fue seleccionado", "Composicion de Plato", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        addTablaTemporalCOmposicion(new TemporalComposicion(getId_Ingredientes().get(cbIngredienteComposicion.getSelectedIndex() - 1), (String) cbIngredienteComposicion.getSelectedItem(), Float.parseFloat(txtCantidadComposicion.getText())));
        limpiarComposicion(false);
    }//GEN-LAST:event_btnSaveComposicionActionPerformed
    
    public void actualizarTablaComposicion() {
        tablaComposicionTemporal.setModel(new javax.swing.table.DefaultTableModel(
                matrizTemporalComposicion,
                new String[]{
                    "Id", "Nombre", "Cantidad gr", "Eliminar"
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, false, true, true
            
            };
            
            public Class getColumnClass(int columnIndex) {
                
                return types[columnIndex];
                
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                
                return canEdit[columnIndex];
                
            }
            
            @Override
            
            public void setValueAt(Object value, int row, int col) {
                db = FabricaDaos.producirPersistenia(2);
                if (col == 2) {
                    matrizTemporalComposicion[row][col] = (float) value;
                } else if (col == 3) {
                    matrizTemporalComposicion[row][col] = (boolean) value;
                }
                actualizarTablaComposicion();
            }
            
        });
    }
    
    public void addTablaTemporalCOmposicion(TemporalComposicion t) {
        Object tep[][] = new Object[matrizTemporalComposicion.length + 1][4];
        int i = 0;
        for (i = 0; i < matrizTemporalComposicion.length; i++) {
            for (int j = 0; j < matrizTemporalComposicion[i].length; j++) {
                tep[i][j] = matrizTemporalComposicion[i][j];
            }
        }
        tep[i][0] = t.getId();
        tep[i][1] = t.getNombre();
        tep[i][2] = t.getCantidad();
        tep[i][3] = false;
        matrizTemporalComposicion = tep;
        actualizarTablaComposicion();
    }
    
    public void deleteTablaTemporalCOmposicion() {
        int c = 0;
        for (int i = 0; i < matrizTemporalComposicion.length; i++) {
            if (!(boolean) tablaComposicionTemporal.getValueAt(i, 3)) {
                c++;
            }
        }
        Object tep[][] = new Object[c][4];
        ;
        int c2 = 0;
        for (int i = 0; i < matrizTemporalComposicion.length; i++) {
            if (!(boolean) tablaComposicionTemporal.getValueAt(i, 3)) {
                for (int j = 0; j < matrizTemporalComposicion[i].length; j++) {
                    tep[c2][j] = matrizTemporalComposicion[i][j];
                }
                c2++;
            }
        }
        matrizTemporalComposicion = tep;
        actualizarTablaComposicion();
    }
    
    public boolean validarTemporalCOmposicion(int id) {
        for (int i = 0; i < matrizTemporalComposicion.length; i++) {
            if (((int) tablaComposicionTemporal.getValueAt(i, 0)) == id) {
                return true;
            }
        }
        return false;
    }
    
    public void eraseTablaComposicionTemporal() {
        matrizTemporalComposicion = new Object[0][4];
        actualizarTablaComposicion();
        ;
    }
    
    public void habilitarComposicion(boolean b) {
        
        cbIngredienteComposicion.setEnabled(b);
        cbIngredienteComposicion.setSelectedIndex(0);
        txtCantidadComposicion.setText("");
        txtCantidadComposicion.setEnabled(b);
        eraseTablaComposicionTemporal();
        tablaComposicionTemporal.setEnabled(b);
        btnEliminarComposicion.setEnabled(b);
        btnGuardarComposicion.setEnabled(b);
        btnClearComposisicon.setEnabled(b);
        btnSaveComposicion.setEnabled(b);
        btnNuevoComposicion.setEnabled(b);
    }
    
    public void limpiarComposicion(boolean todo) {
        if (todo) {
            cbPlatoComposicion.setSelectedIndex(0);
        }
        cbIngredienteComposicion.setSelectedIndex(0);
        txtCantidadComposicion.setText("");
    }

    private void btnClearComposisiconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearComposisiconActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnClearComposisiconActionPerformed

    private void btnEliminarComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarComposicionActionPerformed
        // TODO add your handling code here:
        deleteTablaTemporalCOmposicion();
    }//GEN-LAST:event_btnEliminarComposicionActionPerformed

    private void txtTemporalSurtidoResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTemporalSurtidoResultadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTemporalSurtidoResultadoActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if (m.length > 0) {
            int con = 0, p = 0;
            for (int i = 0; i < m.length; i++) {
                con += ((boolean) tablaIngredientes.getValueAt(i, 3) ? 1 : 0);
                if ((boolean) tablaIngredientes.getValueAt(i, 3)) {
                    p = i;
                }
            }
            if (con == 1) {
                JOptionPane.showMessageDialog(this, "Nombre:\n"
                        + ((String) tablaIngredientes.getValueAt(p, 1))
                        + "\nDescripcion :\n" + ((String) tablaIngredientes.getValueAt(p, 2)), "Ver Ingrediente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un ingrediente.", "Ver Ingrediente", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        setDb(FabricaDaos.producirPersistenia(10));
        for (int i = 0; i < m.length; i++) {
            if ((boolean) tablaIngredientes.getValueAt(i, 3)) {
                getDb().deleteDato((int) tablaIngredientes.getValueAt(i, 0));
            }
        }
        actualizarModificacionBase(10, 4);
        modificarTablaIngrediente();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtCantidadComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadComposicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadComposicionActionPerformed

    private void cbPlatoComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPlatoComposicionActionPerformed
        // TODO add your handling code here:

        if (cbPlatoComposicion.getSelectedIndex() > 0) {
            habilitarComposicion(true);
            return;
        }
        habilitarComposicion(false);
    }//GEN-LAST:event_cbPlatoComposicionActionPerformed

    private void btnGuardarComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComposicionActionPerformed
        // TODO add your handling code here:
        if (cbPlatoComposicion.getSelectedIndex() > 0) {
            int id_plato = this.getId_platos().get(cbPlatoComposicion.getSelectedIndex() - 1);
            setDb(FabricaDaos.producirPersistenia(11));
            ArrayList<TemporalComposicion> tem = new ArrayList<>();
            for (int i = 0; i < matrizTemporalComposicion.length; i++) {
                tem.add(new TemporalComposicion((int) tablaComposicionTemporal.getValueAt(i, 0), (float) tablaComposicionTemporal.getValueAt(i, 2)));
            }
            Vector<Object> obj = new Vector<>();
            obj.add(id_plato);
            obj.add(tem);
            getDb().insertarDato(obj);
            habilitarComposicion(false);
            synPanelComposicion();
        }
    }//GEN-LAST:event_btnGuardarComposicionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int r = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar la conformacion del plato", "Comformacion Plato", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            setDb(FabricaDaos.producirPersistenia(11));
            for (int i = 0; i < m.length; i++) {
                if ((boolean) tablaComposicion.getValueAt(i, 2)) {
                    getDb().deleteAll((int) tablaComposicion.getValueAt(i, 0));
                }
            }
            synPanelComposicion();
            return;
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public boolean validarTablaGenericaTipos(int index, javax.swing.JTable t) {
        int c = 0;
        uno:
        for (int i = 0; i < m.length; i++) {
            if ((boolean) t.getValueAt(i, index)) {
                c++;
                if (c > 1) {
                    break uno;
                }
            }
        }
        return c > 1 ? false : true;
    }
    
    public int idTablaGenericaTipos(int index, javax.swing.JTable t) {
        for (int i = 0; i < m.length; i++) {
            if ((boolean) t.getValueAt(i, index)) {
                return (int) t.getValueAt(i, 0);
            }
        }
        return -1;
    }
    
    public int indexTablaGenericaTipos(int index, javax.swing.JTable t) {
        for (int i = 0; i < m.length; i++) {
            if ((boolean) t.getValueAt(i, index)) {
                return i;
            }
        }
        return -1;
    }
    
    public Object getObjetoModificar(javax.swing.JTable t, int tipo, int i) {
        int index = indexTablaGenericaTipos(i, t);
        if (index >= 0) {
            int id = idTablaGenericaTipos(i, t);
            setDb(FabricaDaos.producirPersistenia(tipo));
            return getDb().getDato(id);
        }
        return null;
    }
    
    public boolean validarTablaCompision() {
        int c = 0;
        for (int i = 0; i < m.length; i++) {
            if ((boolean) tablaComposicion.getValueAt(i, 2)) {
                c++;
            }
        }
        return c > 1 ? false : true;
    }
    
    public boolean validarTablaCompisionTemporalElementos() {
        return tablaComposicionTemporal.getModel().getRowCount() >= 1 ? true : false;
    }
    
    public ArrayList<TemporalComposicion> getTemporalCOmposicionFromTablaTemporal() {
        ArrayList<TemporalComposicion> t = new ArrayList<>();
        for (int i = 0; i < matrizTemporalComposicion.length; i++) {
            t.add(new TemporalComposicion((int) tablaComposicionTemporal.getValueAt(i, 0), (float) tablaComposicionTemporal.getValueAt(i, 2)));
        }
        return t;
    }
    
    public int idTablaComposicion() {
        for (int i = 0; i < m.length; i++) {
            if ((boolean) tablaComposicion.getValueAt(i, 2)) {
                return (int) tablaComposicion.getValueAt(i, 0);
            }
        }
        return -1;
    }
    
    public int indexTablaComposicion() {
        for (int i = 0; i < m.length; i++) {
            if ((boolean) tablaComposicion.getValueAt(i, 2)) {
                return i;
            }
        }
        return -1;
    }
    

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if (validarTablaCompision()) {
            BANDERA_MODIFICARCOMPOSICION = true;
            habilitarComposicion(true);
            int id_p = idTablaComposicion();
            setDb(FabricaDaos.producirPersistenia(11));
            matrizTemporalComposicion = getDb().getObjetoMatriz(id_p);
            matrizTemporalComposicion = convertirMatriz(matrizTemporalComposicion, 4);
            actualizarTablaComposicion();
            magiaDelCambio(false);
            lbTemporalPlato.setText((String) tablaComposicion.getValueAt(indexTablaComposicion(), 1));
        } else {
            BANDERA_MODIFICARCOMPOSICION = false;
            JOptionPane.showMessageDialog(null, "Debe seleccionar solo un elemento.", "Modificar Composicion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton13ActionPerformed
    

    private void btnGuardarEdicionComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEdicionComposicionActionPerformed
        // TODO add your handling code here:
        if (validarTablaCompisionTemporalElementos()) {
            setDb(FabricaDaos.producirPersistenia(11));
            getDb().updateDato(getTemporalCOmposicionFromTablaTemporal(), idTablaComposicion());
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar por le menos un ingrediente.", "Modificar Composicion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarEdicionComposicionActionPerformed

    private void btnNuevoComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoComposicionActionPerformed
        // TODO add your handling code here:
        synPanelComposicion();
    }//GEN-LAST:event_btnNuevoComposicionActionPerformed

    private void ptInventarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ptInventarioStateChanged
        // TODO add your handling code here:
        int r = ptInventario.getSelectedIndex();
        if (r == 1) {
            txtBuscarSurtir.requestFocusInWindow();
            actualizarBusquedaInventario("");
            txtIngredienteSurtir.setEnabled(false);
        } else if (r == 0) {
            actualizarTablaMostrarIngredientes("");
            txtBuscarExistencia.requestFocusInWindow();
        }
    }//GEN-LAST:event_ptInventarioStateChanged
    public void actualizarBusquedaInventario(String b) {
        logica.db.Ingrediente c = new logica.db.Ingrediente();
        setId_Ingredientes_Inventario(c.getIdsIngredientes(b));
        listaSurtido.setModel(new javax.swing.AbstractListModel() {
            String[] strings = c.getNombresIngredientes(b);
            
            public int getSize() {
                return strings.length;
            }
            
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    private void txtBuscarSurtirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSurtirKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBuscarSurtirKeyTyped

    private void txtBuscarSurtirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSurtirKeyReleased
        // TODO add your handling code here:
        actualizarBusquedaInventario(txtBuscarSurtir.getText());
    }//GEN-LAST:event_txtBuscarSurtirKeyReleased
    private JList lista;
    
    public void limpiarSurtidoCOntenedores() {
        txtIngredienteSurtir.setText("");
        txtTemporalSurtidoResultado.setText("");
        txtNombreDecimal.setText("");
    }
    
    public void desabilitarMandosSurtido(boolean r) {
        btnAgregarSurtir.setEnabled(r);
        btnLimpiarSurtir.setEnabled(r);
        btnAgregarSurtir.setEnabled(r);
        btnQuitarSurtir.setEnabled(r);
        limpiarSurtidoCOntenedores();
        
    }
    private logica.Ingrediente ing = null;

    private void listaSurtidoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaSurtidoValueChanged
        // TODO add your handling code here:
        lista = (JList) evt.getSource();
        desabilitarMandosSurtido(false);
        ing = null;
        if (lista.getSelectedIndices().length == 1) {
            desabilitarMandosSurtido(true);
            logica.db.Ingrediente cing = new logica.db.Ingrediente();
            ing = cing.getDato(getId_Ingredientes_Inventario().get(lista.getSelectedIndex()));
            lbTemporalSurtir.setText(ing.getNombre());
            txtTemporalSurtidoResultado.setText(new String("" + ing.getExistencias()));
            txtNombreDecimal.setText(ing.getCategoria() == 0 ? "Kg" : "Unids.");
            txtIngredienteSurtir.setEnabled(true);
            txtIngredienteSurtir.requestFocusInWindow();
        } else if (lista.getSelectedIndices().length > 0) {
            int index[] = lista.getSelectedIndices();
            for (int j : index) {
                lista.setSelectedIndex(j);
            }
            JOptionPane.showMessageDialog(this, "Debe seleccionar un solo elemento de la lista.", "Surtir Existencias", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_listaSurtidoValueChanged

    private void btnAgregarSurtirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSurtirActionPerformed
        // TODO add your handling code here:
        operacionModificacion(1);
    }//GEN-LAST:event_btnAgregarSurtirActionPerformed

    private void btnQuitarSurtirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSurtirActionPerformed
        // TODO add your handling code here:
        if (Validadora.validarCadena(4, txtIngredienteSurtir.getText())) {
            if (Float.parseFloat(txtIngredienteSurtir.getText()) <= ing.getExistencias()) {
                operacionModificacion(-1);
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad maxima a decrementar son " + ing.getExistencias()
                        + " " + (ing.getCategoria() == 0 ? "Kg" : "Unids"), "Surtir Ingrediente",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Debe Digitar solo valores positivos",
                    "Surtir Ingrediente",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnQuitarSurtirActionPerformed
    
    public void actualizarTablaMostrarIngredientes(String b) {
        logica.db.Ingrediente bing = new logica.db.Ingrediente();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                bing.getObjetoMatrizInventarioIngredientes(b),
                new String[]{
                    "Nombre", "Cantidad"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private void btnLimpiarSurtirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSurtirActionPerformed
        limpiarSurtidoCOntenedores();
    }//GEN-LAST:event_btnLimpiarSurtirActionPerformed

    private void txtBuscarExistenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarExistenciaKeyReleased
        // TODO add your handling code here:
        actualizarTablaMostrarIngredientes(txtBuscarExistencia.getText());
    }//GEN-LAST:event_txtBuscarExistenciaKeyReleased

    private void txtBuscarSurtirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarSurtirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarSurtirActionPerformed

    private void optPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optPlatoActionPerformed
        // TODO add your handling code here:
        actualizarTabla(4);
    }//GEN-LAST:event_optPlatoActionPerformed

    private void optPlatoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optPlatoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_optPlatoStateChanged

    private void optCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCategoriaActionPerformed
        // TODO add your handling code here:
        actualizarTabla(2);
    }//GEN-LAST:event_optCategoriaActionPerformed

    private void optCategoriaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optCategoriaStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_optCategoriaStateChanged

    private void optTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optTipoActionPerformed
        // TODO add your handling code here:
        actualizarTabla(3);
    }//GEN-LAST:event_optTipoActionPerformed

    private void optTipoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optTipoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_optTipoStateChanged

    private void optPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optPagoActionPerformed
        // TODO add your handling code here:
        actualizarTabla(1);
    }//GEN-LAST:event_optPagoActionPerformed

    private void optPagoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optPagoStateChanged

    }//GEN-LAST:event_optPagoStateChanged

    private void btnEliminarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMesaActionPerformed
        // TODO add your handling code here:      
        setDb(FabricaDaos.producirPersistenia(1));
        for (int i = 0; i < m.length; i++) {
            if ((boolean) jtMesa.getValueAt(i, 3)) {
                getDb().deleteDato((int) jtMesa.getValueAt(i, 0));
            }
        }
        actualizarModificacionBase(1, 4);
        modificarTablamesa();
    }//GEN-LAST:event_btnEliminarMesaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setDb(FabricaDaos.producirPersistenia(2));
        for (int i = 0; i < m.length; i++) {
            if ((boolean) jtCategoria.getValueAt(i, 3)) {
                getDb().deleteDato((int) jtCategoria.getValueAt(i, 0));
            }
        }
        actualizarModificacionBase(2, 4);
        modificarTablaCategoria();
    }//GEN-LAST:event_jButton3ActionPerformed
    private boolean BANDERA_GENERICA_TIPO = false;
    private logica.Tipo temporal_tipo = null;
    
    public Tipo getTemporal_tipo() {
        return temporal_tipo;
    }
    
    public void setTemporal_tipo(Tipo temporal_tipo) {
        this.temporal_tipo = temporal_tipo;
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (validarTablaGenericaTipos(5, jtTipo)) {
            
            this.setTemporal_tipo((logica.Tipo) getObjetoModificar(jtTipo, 3, 5));
            if (this.getTemporal_tipo() != null) {
                deshabilitarBotonesTipo(false);
                cbTipo.setSelectedIndex(id_categoria.indexOf(getTemporal_tipo().getCategoria_id()));
                txtNombeTipo.setText(getTemporal_tipo().getNombre());
                setColor_tipo(new Color(getTemporal_tipo().getColo().getRed(), getTemporal_tipo().getColo().getGreen(), getTemporal_tipo().getColo().getBlue()));
                txtColorTipo.setBackground(color_tipo);
                getTxtColorTipo().setBackground(color_tipo);
                areaTipo.setText(getTemporal_tipo().getDescripcion());
                return;
            }
            JOptionPane.showMessageDialog(null, "Inconsistencias con la modificacion del tipo.", "Modificar Tipo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Debe seleccionar solo un elemento.", "Modificar Tipo", JOptionPane.INFORMATION_MESSAGE);
        BANDERA_GENERICA_TIPO = false;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnModificarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTipoActionPerformed
        // TODO add your handling code here:
        if (txtNombeTipo.getText().length() == 0) {
            producirMensaje("Debe digitar un nombre.", "TIPO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (cbTipo.getSelectedIndex() > 0) {
            db = FabricaDaos.producirPersistenia(3);
            ArrayList<Object> l = new ArrayList<>();
            l.add(txtNombeTipo.getText());
            Object r[] = db.validInsert(l, 1);
            if (((boolean) r[0]) && txtNombeTipo.getText().equalsIgnoreCase(getTemporal_tipo().getNombre())) {
                r[0] = false;
            }
            if (!((boolean) r[0])) {
                if (getColor_tipo() != null) {
                    db.updateDato(new logica.Tipo(id_categoria.get(cbTipo.getSelectedIndex()), txtNombeTipo.getText(), areaTipo.getText(), getColor_tipo()), getTemporal_tipo().getId());
                    limpiarTipo();
                    actualizarModificacionBase(3, 6);
                    deshabilitarBotonesTipo(true);
                    modificarTablaTipo();
                } else {
                    producirMensaje("Seleccione un color", "TIPO", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                producirMensaje(((String) r[1]), "TIPO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            this.producirMensaje("Seleccione una categoria.", "TIPO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarTipoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        eraseElementosTabla(jtTipo, 5, 3, "Esta seguro de que quiere eliminar\nlos elementos seleccionados", "Eliminar Tipo");
        actualizarModificacionBase(3, 6);
        modificarTablaTipo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        eraseElementosTabla(jtPlato, 5, 4, "Esta seguro de que quiere eliminar\nlos elementos seleccionados", "Eliminar Plato");
        actualizarModificacionBase(4, 6);
        modificarTablaPlato();
    }//GEN-LAST:event_jButton6ActionPerformed
    
    private boolean BANDERA_GENERICA_PLATO = false;
    private logica.Plato temporal_plato = null;
    
    public Plato getTemporal_plato() {
        return temporal_plato;
    }
    
    public void setTemporal_plato(Plato temporal_plato) {
        this.temporal_plato = temporal_plato;
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (validarTablaGenericaTipos(5, jtPlato)) {
            BANDERA_GENERICA_PLATO = true;
            this.setTemporal_plato((logica.Plato) getObjetoModificar(jtPlato, 4, 5));
            if (this.getTemporal_plato() != null) {
                magiaButtonPlato(false);
                cbPlato.setSelectedIndex(id_tipos.indexOf(getTemporal_plato().getTipo_id()));
                txtNombrePlato.setText(this.getTemporal_plato().getNombre());
                txtPrecioPlato.setText("" + this.getTemporal_plato().getPrecio());
                areaPlato.setText(getTemporal_plato().getDescripcion());
                return;
            }
            JOptionPane.showMessageDialog(null, "Inconsistencias con la modificacion del plato.", "Modificar Plato", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Debe seleccionar solo un elemento.", "Modificar Plato", JOptionPane.INFORMATION_MESSAGE);
        actualizarModificacionBase(4, 6);
        modificarTablaPlato();
        BANDERA_GENERICA_PLATO = false;
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnEditPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPlatoActionPerformed
        // TODO add your handling code here:
        if (txtNombrePlato.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar solo un elemento.", "Modificar Plato", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (cbPlato.getSelectedIndex() > 0) {
            db = FabricaDaos.producirPersistenia(4);
            ArrayList<Object> l = new ArrayList<>();
            l.add(txtNombrePlato.getText());
            Object r[] = db.validInsert(l);
            if (((boolean) r[0]) && txtNombrePlato.getText().equalsIgnoreCase(getTemporal_plato().getNombre())) {
                r[0] = false;
            }
            if (!((boolean) r[0])) {
                if (logica.Validadora.validarCadena(4, txtPrecioPlato.getText())) {
                    db.updateDato(new logica.Plato(txtNombrePlato.getText(), areaPlato.getText(), id_tipos.get(cbPlato.getSelectedIndex()), Float.parseFloat(txtPrecioPlato.getText())), getTemporal_plato().getId());
                    limpiarPlato();
                    actualizarModificacionBase(4, 6);
                    magiaButtonPlato(true);
                    modificarTablaPlato();
                } else {
                    producirMensaje("Debe dijitar solo numeros.", "PLATO", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                producirMensaje(((String) r[1]), "PLATO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            producirMensaje("Debe seleccionar un tipo para el plato.", null, WIDTH);
            cbTipo.setSelectedIndex(0);
            cbTipo.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnEditPlatoActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        eraseElementosTabla(jtTrabajador, 5, 7, "Esta seguro de que quiere eliminar\nlos elementos seleccionados", "Eliminar Trabajador");
        actualizarModificacionBase(7, 6);
        modificarTablaTrabajador();
    }//GEN-LAST:event_jButton9ActionPerformed
    
    public Trabajador getTemporal_trabajador() {
        return temporal_trabajador;
    }
    
    public void setTemporal_trabajador(Trabajador temporal_trabajador) {
        this.temporal_trabajador = temporal_trabajador;
    }
    
    private logica.Trabajador temporal_trabajador = null;
    
    public void limpiarTrabajador() {
        txtUsuario.setText("");
        txtCedulaTrabajador.setText("");
        txtNombreTrabajador.setText("");
        txtApellidosTrabajador.setText("");
        txtTelefonoTrabajador.setText("");
        txtCorreoTrabajador.setText("");
        clave1.setText("");
        clave2.setText("");
    }
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "aqui");
        BANDERA_TRABAJADOR = false;
        if (validarTablaGenericaTipos(5, jtTrabajador)) {
            BANDERA_TRABAJADOR = true;
            this.setTemporal_trabajador((logica.Trabajador) getObjetoModificar(jtTrabajador, 7, 5));
            if (this.getTemporal_trabajador() != null) {
                deshabilitarControlPass(false);
                magiaButtonTrabajador(false);
                txtUsuario.setText(getTemporal_trabajador().getUser());
                txtCedulaTrabajador.setText(getTemporal_trabajador().getIdentificacion());
                txtNombreTrabajador.setText(getTemporal_trabajador().getNombre());
                txtApellidosTrabajador.setText(getTemporal_trabajador().getApellidos());
                txtTelefonoTrabajador.setText(getTemporal_trabajador().getTel());
                txtCorreoTrabajador.setText(getTemporal_trabajador().getCorreo());
                areaTrabajador.setText(getTemporal_trabajador().getDireccion());
                return;
            }
            JOptionPane.showMessageDialog(null, "Inconsistencias con la modificacion del tipo.", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Debe seleccionar solo un elemento.", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnClearTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTrabajadorActionPerformed
        // TODO add your handling code here:
        limpiarTrabajador();
        txtUsuario.requestFocusInWindow();
    }//GEN-LAST:event_btnClearTrabajadorActionPerformed

    private void btnModificarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTrabajadorActionPerformed
        // TODO add your handling code here:
        db = FabricaDaos.producirPersistenia(6);
        if (!txtUsuario.getText().equals("")) {
            ArrayList<Object> o = new ArrayList<>();
            o.add(txtUsuario.getText());
            Object r[] = db.validInsert(o);
            if (txtUsuario.getText().equalsIgnoreCase(getTemporal_trabajador().getUser())) {
                r[0] = false;
            }
            if (!(boolean) r[0]) {
                if (Validadora.validarCadena(0, txtCedulaTrabajador.getText())) {
                    db = FabricaDaos.producirPersistenia(7);
                    o.clear();
                    o.add(txtCedulaTrabajador.getText());
                    r = db.validInsert(o);
                    if (txtCedulaTrabajador.getText().equalsIgnoreCase(getTemporal_trabajador().getIdentificacion())) {
                        r[0] = false;
                    }
                    if (!(boolean) r[0]) {
                        if (!txtNombreTrabajador.getText().equalsIgnoreCase("")) {
                            if (!txtApellidosTrabajador.getText().equalsIgnoreCase("")) {
                                Vector<Object> j = new Vector<>();
                                j.add(new logica.Trabajador(txtUsuario.getText(),
                                        String.valueOf(clave1.getPassword()),
                                        txtNombreTrabajador.getText(),
                                        txtApellidosTrabajador.getText(),
                                        txtCedulaTrabajador.getText(),
                                        areaTrabajador.getText(),
                                        txtTelefonoTrabajador.getText(),
                                        txtCorreoTrabajador.getText()));
                                j.add(this.getTemporal_trabajador().getId_user());
                                db.updateDato(j, getTemporal_trabajador().getId_trabajador()
                                );
                                limpiarTrabajador();
                                deshabilitarControlPass(true);
                                actualizarModificacionBase(7, 6);
                                modificarTablaTrabajador();
                                magiaButtonTrabajador(true);
                            } else {
                                producirMensaje("Digite los apellidos, son requeridos.", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
                                txtApellidosTrabajador.requestFocusInWindow();
                            }
                        } else {
                            producirMensaje("Digite el nombre del trabajador es requerido.", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
                            txtNombreTrabajador.requestFocusInWindow();
                        }
                    } else {
                        producirMensaje((String) r[1], "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
                        txtCedulaTrabajador.setText("");
                        txtCedulaTrabajador.requestFocusInWindow();
                    }
                } else {
                    producirMensaje("La cedula solo requiere digitos.", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
                    txtCedulaTrabajador.setText("");
                    txtCedulaTrabajador.requestFocusInWindow();
                }
            } else {
                producirMensaje("El nombre de usuario se encuentra registrado.", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
                txtUsuario.requestFocusInWindow();
            }
        } else {
            producirMensaje("El usuario es requerido.", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
            txtUsuario.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnModificarTrabajadorActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        BANDERA_TRABAJADOR = false;
        if (validarTablaGenericaTipos(5, jtTrabajador)) {
            BANDERA_TRABAJADOR = true;
            this.setTemporal_trabajador((logica.Trabajador) getObjetoModificar(jtTrabajador, 7, 5));
            if (this.getTemporal_trabajador() != null) {
                deshabilitarControlPass(false);
                magiaButtonTrabajador(false);
                txtUsuario.setText(getTemporal_trabajador().getUser());
                txtCedulaTrabajador.setText(getTemporal_trabajador().getIdentificacion());
                txtNombreTrabajador.setText(getTemporal_trabajador().getNombre());
                txtApellidosTrabajador.setText(getTemporal_trabajador().getApellidos());
                txtTelefonoTrabajador.setText(getTemporal_trabajador().getTel());
                txtCorreoTrabajador.setText(getTemporal_trabajador().getTel());
                String cad;
                final_:
                do {
                    cad = JOptionPane.showInputDialog(this,
                            "Digite la clave para el usuario \n" + getTemporal_trabajador().getNombre() + " " + getTemporal_trabajador().getApellidos(), "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
                    if (cad == null) {
                        return;
                    }
                    if (cad.length() < 6) {
                        JOptionPane.showMessageDialog(this, "La clave debe contener por o menos 6 caracteres", "Modificar Clave", JOptionPane.INFORMATION_MESSAGE);
                    }
                } while (cad.length() < 6);
                setDb(FabricaDaos.producirPersistenia(6));
                getDb().updateDato(new logica.Usuario(getTemporal_trabajador().getUser(), cad), getTemporal_trabajador().getId_user());
                return;
            }
            JOptionPane.showMessageDialog(null, "Inconsistencias con la modificacion del tipo.", "Modificar Tipo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Debe seleccionar solo un elemento.", "Modificar Tipo", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton14ActionPerformed
    
    private logica.Configuracion configuracion = null;
    
    public Configuracion getConfiguracion() {
        return configuracion;
    }
    
    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
    

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        int r = jTabbedPane1.getSelectedIndex();
        panel.add(r);
        if (r == 4) {
            logica.db.Configuracion c = new logica.db.Configuracion();
            setConfiguracion(c.get_sDato());
            actualizarConfiguracion();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        String m = !Validadora.validarCadena(3, txtCorreoConfiguracion.getText()) ? "El correo no es valido."
                : !Validadora.validarCadena(6, txtUsuarioAdmin.getText()) ? "El nombre de de usuario debe tener por  lo menos 3 caracteres"
                        : !Validadora.validarCadena(4, txtIngredienteAdmin.getText()) ? "El ingrediente debe ser un valor real.\nEjemplo: 4.5"
                                : !Validadora.validarCadena(5, txtUnidadesAdmin.getText()) ? "Debe digitar solo enteros positivos.\nEjemplo: 25"
                                        : String.valueOf(txtClave.getPassword()).length() == 0 ? "" : String.valueOf(txtClave.getPassword()).length() >= 6 ? "" : "La clave debe tener por lo menos 6 carateres alfa numericos";
        if (m.length() == 0) {
            logica.db.Configuracion c = new logica.db.Configuracion();
            c.updateDato(new logica.Configuracion(txtUsuarioAdmin.getText(), String.valueOf(txtClave.getPassword()).length() == 0 ? "" : String.valueOf(txtClave.getPassword()), txtCorreoConfiguracion.getText(),
                    Integer.parseInt(txtUnidadesAdmin.getText()), Float.parseFloat(txtIngredienteAdmin.getText())), getConfiguracion().getId());
        } else {
            JOptionPane.showMessageDialog(this, m, "Configuraciones Admin", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton15ActionPerformed
    
    public class ThearCorreo implements Runnable {
        
        private admin admin;
        
        public ThearCorreo(admin admin) {
            this.admin = admin;
        }
        
        @Override
        public void run() {
            logica.correo.EnvioCorreo.send();
            JOptionPane.showMessageDialog(admin, "El correo fue enviado con exito.", "Notificar Inventario", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed
        // TODO add your handling code here:  v
        if (jTabbedPane1.getSelectedIndex() == 5) {
            panel.remove(panel.indexOf(panel.lastElement()));
            if (panel.size() > 0) {
                jTabbedPane1.setSelectedIndex(panel.lastElement());
                panel.remove(panel.indexOf(panel.lastElement()));
            } else {
                jTabbedPane1.setSelectedIndex(0);
            }
            ThearCorreo hc = new ThearCorreo(this);
            Thread h1 = new Thread(hc);
            h1.run();
        }
    }//GEN-LAST:event_jTabbedPane1MousePressed

    private void txtIngredienteAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngredienteAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngredienteAdminActionPerformed

    private void btnNewPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPlatoActionPerformed
        // TODO add your handling code here:
        limpiarPlato();
    }//GEN-LAST:event_btnNewPlatoActionPerformed

    private void btnNuevoPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPlatoActionPerformed
        // TODO add your handling code here:
        actualizarModificacionBase(4, 6);
        modificarTablaPlato();
        limpiarPlato();
    }//GEN-LAST:event_btnNuevoPlatoActionPerformed

    private void btnNuevoComposicionInvisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoComposicionInvisibleActionPerformed
        // TODO add your handling code here:
        synPanelComposicion();
    }//GEN-LAST:event_btnNuevoComposicionInvisibleActionPerformed
    private boolean BANDERA_TRABAJADOR = false;
    
    public void actualizarConfiguracion() {
        if (getConfiguracion() != null) {
            txtCorreoConfiguracion.setText(getConfiguracion().getCorreo());
            txtUsuarioAdmin.setText(getConfiguracion().getNom());
            txtIngredienteAdmin.setText("" + getConfiguracion().getIngrediente());
            txtUnidadesAdmin.setText("" + getConfiguracion().getUnidades());
        }
    }
    
    public void eraseElementosTabla(javax.swing.JTable t, int p, int f, String m, String titu) {
        int r = JOptionPane.showConfirmDialog(this, m, titu, JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            setDb(FabricaDaos.producirPersistenia(f));
            for (int i = 0; i < this.m.length; i++) {
                if ((boolean) t.getValueAt(i, p)) {
                    getDb().deleteDato((int) t.getValueAt(i, 0));
                }
            }
            return;
        }
    }
    
    public void operacionModificacion(int v) {
        if (ing != null) {
            if (ing.getCategoria() == 0 ? Validadora.validarCadena(4, txtIngredienteSurtir.getText()) : Validadora.validarCadena(5, txtIngredienteSurtir.getText())) {
                logica.db.Ingrediente i = new Ingrediente();
                ing.setExistencias(ing.getExistencias() + Float.parseFloat(txtIngredienteSurtir.getText()) * v);
                i.updateDato(ing, ing.getId());
                int pos = lista.getSelectedIndex();
                actualizarBusquedaInventario(txtBuscarSurtir.getText());
                lista.setSelectedIndex(pos);
                txtIngredienteSurtir.setText("");
                txtIngredienteSurtir.requestFocusInWindow();
            } else {
                txtIngredienteSurtir.setText("");
                txtIngredienteSurtir.requestFocusInWindow();
                JOptionPane.showMessageDialog(this,
                        ing.getCategoria() == 0 ? "En la cantidad se requiere un valor real positivo con \".\".\nEjemplo : 45.12" : "Debe digitar solo numeros enteros positivos.",
                        "Surtir Ingrediente",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento de la lista.", "Surtir Existencias", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void limpiarMesa() {
        txtNombeMesa.setText("");
        areaMesa.setText("");
        txtNombeMesa.requestFocusInWindow();
    }
    
    public void limpiarCategoria() {
        
        txtNombeCategoria.setText("");
        
        areaCategoria.setText("");
        
        txtNombeCategoria.requestFocusInWindow();
        
    }
    
    public void limpiarTipo() {
        cbTipo.setSelectedIndex(0);
        txtNombeTipo.setText("");
        areaTipo.setText("");
        txtColorTipo.setBackground(new Color(240, 240, 240));
        txtNombeTipo.requestFocusInWindow();
    }
    
    public void limpiarPlato() {
        cbPlato.setSelectedIndex(0);
        txtNombrePlato.setText("");
        areaPlato.setText("");
        txtPrecioPlato.setText("");
        cbPlato.requestFocusInWindow();
    }
    
    public void modificarTablamesa() {
        db = FabricaDaos.producirPersistenia(1);
        jtMesa.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Nombre", "Descripcion", "Eliminar"
                
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, true, true, true
            
            };
            
            public Class getColumnClass(int columnIndex) {
                
                return types[columnIndex];
                
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                
                return canEdit[columnIndex];
                
            }
            
            @Override
            
            public void setValueAt(Object value, int row, int col) {
                if (col == 1) {
                    
                    db = FabricaDaos.producirPersistenia(1);
                    
                    ArrayList<Object> o = new ArrayList<>();
                    
                    o.add((String) value);
                    
                    Object r[] = db.validInsert(o);
                    
                    if (!(boolean) r[0]) {
                        
                        db.updateDato(new logica.Mesa((String) value, (String) jtMesa.getValueAt(row, 2)), (int) jtMesa.getValueAt(row, 0));
                        m[row][col] = (String) value;
                        modificarTablamesa();
                        
                        limpiarMesa();
                        
                        return;
                        
                    }
                    
                } else if (col == 2) {
                    
                    db = FabricaDaos.producirPersistenia(1);
                    
                    db.updateDato(new logica.Mesa((String) jtMesa.getValueAt(row, 1), (String) value), (int) jtMesa.getValueAt(row, 0));
                    m[row][col] = (String) value;
                    modificarTablamesa();
                    
                    limpiarMesa();
                    
                    return;
                    
                } else if (col == 3) {
                    m[row][col] = (boolean) value;
                    modificarTablamesa();
                }
                
            }
            
        });
        
    }
    
    public void modificarTablaTrabajador() {
        db = FabricaDaos.producirPersistenia(7);
        jtTrabajador.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Identificacion", "Nombre", "Apellidos", "Usuario", "Eliminar"
                
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true
            
            };
            
            public Class getColumnClass(int columnIndex) {
                
                return types[columnIndex];
                
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                
                return canEdit[columnIndex];
                
            }
            
            @Override
            
            public void setValueAt(Object value, int row, int col) {
                if (col == 5) {
                    m[row][col] = (boolean) value;
                    modificarTablaTrabajador();
                }
                
            }
            
        });
        
    }
    
    public void modificarTablaIngrediente() {
        db = FabricaDaos.producirPersistenia(10);
        tablaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Nombre", "Descripcion", "Eliminar"
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, true, true, true
            
            };
            
            public Class getColumnClass(int columnIndex) {
                
                return types[columnIndex];
                
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                
                return canEdit[columnIndex];
                
            }
            
            @Override
            
            public void setValueAt(Object value, int row, int col) {
                db = FabricaDaos.producirPersistenia(10);
                if (col == 1) {
                    ArrayList<Object> o = new ArrayList<>();
                    o.add((String) value);
                    Object r[] = db.validInsert(o);
                    if (!(boolean) r[0]) {
                        db.updateDato(new logica.Ingrediente((String) value, (String) tablaIngredientes.getValueAt(row, 2)), (int) tablaIngredientes.getValueAt(row, 0));
                        m[row][col] = (String) value;
                        limpiarIngrediente();
                    }
                    
                } else if (col == 2) {
                    
                    db.updateDato(new logica.Ingrediente((String) tablaIngredientes.getValueAt(row, 1), (String) value), (int) tablaIngredientes.getValueAt(row, 0));
                    m[row][col] = (String) value;
                } else if (col == 3) {
                    m[row][3] = (boolean) value;
                    //
                }
                modificarTablaIngrediente();
            }
            
        });
        
    }
    
    public void modificarTablaCategoria() {
        db = FabricaDaos.producirPersistenia(2);
        jtCategoria.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Nombre", "Descripcion", "Eliminar"
                
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, true, true, true
            
            };
            
            public Class getColumnClass(int columnIndex) {
                
                return types[columnIndex];
                
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                
                return canEdit[columnIndex];
                
            }
            
            @Override
            
            public void setValueAt(Object value, int row, int col) {
                db = FabricaDaos.producirPersistenia(2);
                
                if (col == 1) {
                    
                    ArrayList<Object> o = new ArrayList<>();
                    
                    o.add((String) value);
                    
                    Object r[] = db.validInsert(o);
                    
                    if (!(boolean) r[0]) {
                        
                        db.updateDato(new logica.Categoria((String) value, (String) jtCategoria.getValueAt(row, 2)), (int) jtCategoria.getValueAt(row, 0));
                        m[row][col] = (String) value;
                        modificarTablaCategoria();
                        limpiarCategoria();
                        
                        return;
                        
                    }
                    
                } else if (col == 2) {
                    
                    db.updateDato(new logica.Categoria((String) jtCategoria.getValueAt(row, 1), (String) value), (int) jtCategoria.getValueAt(row, 0));
                    m[row][col] = (String) value;
                    modificarTablaCategoria();
                    
                    limpiarCategoria();
                    
                    return;
                    
                } else if (col == 3) {
                    m[row][col] = (boolean) value;
                    modificarTablaCategoria();
                }
                
            }
            
        });
    }
    
    public void modificarTablaTipo() {
        db = FabricaDaos.producirPersistenia(3);
        jtTipo.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Categoria", "Nombre", "Descripcion", "Color", "Editar"
                
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, Color.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            @Override
            public void setValueAt(Object value, int row, int col) {
                setBackground(Color.BLUE);
                db = FabricaDaos.producirPersistenia(3);
//                if (col == 1) {
//                    ArrayList<Object> o = new ArrayList<>();
//                    o.add((String) value);
//                    Object r[] = db.validInsert(o);
//                    if (!(boolean) r[0]) {
//                        db.updateDato(new logica.Categoria((String) value, (String) jtMesa.getValueAt(row, 2)), (int) jtMesa.getValueAt(row, 0));
//                        modificarTablaCategoria();
//                        limpiarCategoria();
//                        return;
//                    }
//                } else if (col == 2) {
//                    db.updateDato(new logica.Categoria((String) jtCategoria.getValueAt(row, 1), (String) value), (int) jtCategoria.getValueAt(row, 0));
//                    modificarTablaCategoria();
//                    limpiarCategoria();
//                    return;
//                }
                if (col == 5) {
                    m[row][col] = (boolean) value;
                    modificarTablaTipo();
                }
            }
            
        });
    }
    
    public void magiaButtonPlato(boolean r) {
        btnEditPlato.setVisible(!r);
        btnNuevoPlato.setVisible(!r);
        btnSavePlato.setVisible(r);
        btnClearPlato.setVisible(r);
        btnNewPlato.setVisible(r);
    }
    
    public void modificarTablaPlato() {
        db = FabricaDaos.producirPersistenia(4);
        jtPlato.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Nombre", "Descripcion", "Tipo", "Precio", "Editar"
                
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, true, true, false, true, true
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            @Override
            public void setValueAt(Object value, int row, int col) {
                setBackground(Color.BLUE);
                db = FabricaDaos.producirPersistenia(4);
                if (col == 1) {
                    ArrayList<Object> o = new ArrayList<>();
                    o.add((String) value);
                    Object r[] = db.validInsert(o);
                    if (!(boolean) r[0]) {
                        db.updateDato(new logica.Plato((String) value, (String) jtPlato.getValueAt(row, 2), (float) jtPlato.getValueAt(row, 4)), (int) jtPlato.getValueAt(row, 0));
                        m[row][col] = (String) value;
                        modificarTablaPlato();
                        limpiarPlato();
                        return;
                    }
                } else if (col == 2) {
                    db.updateDato(new logica.Plato((String) jtPlato.getValueAt(row, 1), (String) value, (float) jtPlato.getValueAt(row, 4)), (int) jtPlato.getValueAt(row, 0));
                    m[row][col] = (String) value;
                    modificarTablaPlato();
                    limpiarPlato();
                    return;
                } else if (col == 4) {
                    String valor = new String("" + value);
                    if (logica.Validadora.validarCadena(4, valor)) {
                        db.updateDato(new logica.Plato((String) jtPlato.getValueAt(row, 1), (String) jtPlato.getValueAt(row, 2), (float) value), (int) jtPlato.getValueAt(row, 0));
                        m[row][col] = (float) value;
                        modificarTablaPlato();
                        limpiarPlato();
                        return;
                    } else {
                        producirMensaje("Digite solo numeros ó el formato decimal es \"#.#\".", null, col);
                    }
                } else if (col == 5) {
                    m[row][col] = (boolean) value;
                    modificarTablaPlato();
                }
            }
        });
    }
    
    public void modificarTablaComposicion() {
        db = FabricaDaos.producirPersistenia(4);
        tablaComposicion.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{
                    "Id", "Nombre", "Editar"
                
                }
        ) {
            
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            
            };
            
            boolean[] canEdit = new boolean[]{
                false, false, true
            };
            
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            
            @Override
            public void setValueAt(Object value, int row, int col) {
                setBackground(Color.BLUE);
                if (col == 2) {
                    m[row][col] = (boolean) value;
                    if (BANDERA_MODIFICARCOMPOSICION) {
                        BANDERA_MODIFICARCOMPOSICION = false;
                        synPanelComposicion();
                    }
                }
                modificarTablaComposicion();
            }
        });
    }
    
    public void cargarIdDeCategorias(javax.swing.JComboBox te) {
        db = FabricaDaos.producirPersistenia(2);
        Object m[][] = db.getObjetoMatriz();
        int z = m.length;
        String l[] = new String[z + 1];
        l[0] = "  Opciones";
        id_categoria.add(0);
        for (int i = 0; i < z; i++) {
            id_categoria.add((int) m[i][0]);
            l[i + 1] = (String) m[i][1];
        }
        te.setModel(new javax.swing.DefaultComboBoxModel(l));
    }
    
    public void limpiarTrabajador2() {
        txtUsuario.setText("");
        clave1.setText("");
        clave2.setText("");
        txtCedulaTrabajador.setText("");
        txtNombreTrabajador.setText("");
        txtApellidosTrabajador.setText("");
        areaTrabajador.setText("");
        txtCorreoTrabajador.setText("");
        txtTelefonoTrabajador.setText("");
        txtUsuario.requestFocusInWindow();
    }
    
    public void magiaButtonTrabajador(boolean t) {
        btnClearTrabajador.setVisible(t);
        btnSaveTrabajador.setVisible(t);
        btnNuevoTrabajador.setVisible(!t);
        btnModificarTrabajador.setVisible(!t);
        this.repaint();
    }
    
    public void cargarIdTipos() {
        db = FabricaDaos.producirPersistenia(3);
        Object m[][] = db.getObjetoMatriz();
        int z = m.length;
        String l[] = new String[z + 1];
        l[0] = "  Opciones";
        id_tipos.add(0);
        for (int i = 0; i < z; i++) {
            id_tipos.add((int) m[i][0]);
            l[i + 1] = (String) m[i][2];
        }
        cbPlato.setModel(new javax.swing.DefaultComboBoxModel(l));
    }

    /**
     *
     * @param args the command line arguments
     *
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.

         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 

         */
        try {
            
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        } catch (ClassNotFoundException ex) {
            
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        }

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new admin().setVisible(true);
                
            }
            
        });
        
    }
    
    public JTextField getTxtColorTipo() {
        return txtColorTipo;
    }
    
    public void setTxtColorTipo(JTextField txtColorTipo) {
        this.txtColorTipo = txtColorTipo;
    }
    
    public void deshabilitarBotonesTipo(boolean r) {
        btnGuardarTipo.setVisible(r);
        btnLimpiarTipo.setVisible(r);
        btnNuevoTipo.setVisible(r);
        btnModificarTipo.setVisible(!r);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaCategoria;
    private javax.swing.JTextArea areaIngrediente;
    private javax.swing.JTextArea areaMesa;
    private javax.swing.JTextArea areaPlato;
    private javax.swing.JTextArea areaTipo;
    private javax.swing.JTextArea areaTrabajador;
    private javax.swing.JButton btnAgregarSurtir;
    private javax.swing.JButton btnCancelCategoria;
    private javax.swing.JButton btnClearComposisicon;
    private javax.swing.JButton btnClearMesa;
    private javax.swing.JButton btnClearMesa1;
    private javax.swing.JButton btnClearPlato;
    private javax.swing.JButton btnClearTrabajador;
    private javax.swing.JButton btnEditPlato;
    private javax.swing.JButton btnEliminarComposicion;
    private javax.swing.JButton btnEliminarMesa;
    private javax.swing.JButton btnGuardarComposicion;
    private javax.swing.JButton btnGuardarEdicionComposicion;
    private javax.swing.JButton btnGuardarTipo;
    private javax.swing.JButton btnLimpiarSurtir;
    private javax.swing.JButton btnLimpiarTipo;
    private javax.swing.JButton btnModificarTipo;
    private javax.swing.JButton btnModificarTrabajador;
    private javax.swing.JButton btnNewCateroria;
    private javax.swing.JButton btnNewMesa;
    private javax.swing.JButton btnNewPlato;
    private javax.swing.JButton btnNuevoComposicion;
    private javax.swing.JButton btnNuevoComposicionInvisible;
    private javax.swing.JButton btnNuevoPlato;
    private javax.swing.JButton btnNuevoTipo;
    private javax.swing.JButton btnNuevoTrabajador;
    private javax.swing.JButton btnQuitarSurtir;
    private javax.swing.JButton btnSaveCategoria;
    private javax.swing.JButton btnSaveComposicion;
    private javax.swing.JButton btnSaveMesa;
    private javax.swing.JButton btnSaveMesa1;
    private javax.swing.JButton btnSavePlato;
    private javax.swing.JButton btnSaveTrabajador;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cbIngredienteComposicion;
    private javax.swing.JComboBox cbPlato;
    private javax.swing.JComboBox cbPlatoComposicion;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JPasswordField clave1;
    private javax.swing.JPasswordField clave2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpContedorTabla;
    private javax.swing.JTable jtCategoria;
    private javax.swing.JTabbedPane jtFactura;
    private javax.swing.JTable jtMesa;
    private javax.swing.JTable jtPlato;
    private javax.swing.JTable jtResultados;
    private javax.swing.JTable jtTipo;
    private javax.swing.JTable jtTrabajador;
    private javax.swing.JLabel lbClave2;
    private javax.swing.JLabel lbNombreExistencia;
    private javax.swing.JLabel lbPlatoComposicion;
    private javax.swing.JLabel lbTemporalPlato;
    private javax.swing.JLabel lbTemporalSurtir;
    private javax.swing.JLabel lbclave;
    private javax.swing.JList listaSurtido;
    private javax.swing.JRadioButton optCategoria;
    private javax.swing.JRadioButton optPago;
    private javax.swing.JRadioButton optPlato;
    private javax.swing.JRadioButton optTipo;
    private javax.swing.JTabbedPane ptInventario;
    private javax.swing.JRadioButton rbBebida;
    private javax.swing.JRadioButton rbngrediente;
    private javax.swing.JTable tablaComposicion;
    private javax.swing.JTable tablaComposicionTemporal;
    private javax.swing.JTable tablaIngredientes;
    private javax.swing.JTabbedPane tablaRegistro;
    private javax.swing.JTextField txtApellidosTrabajador;
    private javax.swing.JTextField txtBuscarExistencia;
    private javax.swing.JTextField txtBuscarSurtir;
    private javax.swing.JTextField txtCantidadComposicion;
    private javax.swing.JTextField txtCedulaTrabajador;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtColorTipo;
    private javax.swing.JTextField txtCorreoConfiguracion;
    private javax.swing.JTextField txtCorreoTrabajador;
    private javax.swing.JTextField txtIngredienteAdmin;
    private javax.swing.JTextField txtIngredienteSurtir;
    private javax.swing.JTextField txtNombeCategoria;
    private javax.swing.JTextField txtNombeMesa;
    private javax.swing.JTextField txtNombeTipo;
    private javax.swing.JTextField txtNombreDecimal;
    private javax.swing.JTextField txtNombreIngrediente;
    private javax.swing.JTextField txtNombrePlato;
    private javax.swing.JTextField txtNombreTrabajador;
    private javax.swing.JTextField txtPrecioPlato;
    private javax.swing.JTextField txtTelefonoTrabajador;
    private javax.swing.JTextField txtTemporalSurtidoResultado;
    private javax.swing.JTextField txtUnidadesAdmin;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtUsuarioAdmin;
    // End of variables declaration//GEN-END:variables

    public void deshabilitarControlPass(boolean r) {
        lbclave.setEnabled(r);
        lbClave2.setEnabled(r);
        clave1.setEnabled(r);
        clave2.setEnabled(r);
    }
}
