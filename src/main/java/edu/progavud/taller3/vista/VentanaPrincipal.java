package edu.progavud.taller3.vista;

import edu.progavud.taller3.control.Fachada;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author a
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private Fachada fachada;
    private JLabel labelsCorredores[]= new JLabel[5];
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public String registrarCorredor(int numeroCorredor) {
    return javax.swing.JOptionPane.showInputDialog(null, "Ingrese el nombre del corredor #" + numeroCorredor + ":");
}

    public VentanaPrincipal(Fachada fachada) {
        initComponents();
        this.fachada = fachada;
        this.vPrincipalLblGanador.setVisible(false);
        labelsCorredores[0]=vPrincipalLblCorredor1;
        labelsCorredores[1]=vPrincipalLblCorredor2;
        labelsCorredores[2]=vPrincipalLblCorredor3;
        labelsCorredores[3]=vPrincipalLblCorredor4;
        labelsCorredores[4]=vPrincipalLblCorredor5;
        labelsCorredores[1].setVisible(false);
        labelsCorredores[2].setVisible(false);
        labelsCorredores[3].setVisible(false);
        labelsCorredores[4].setVisible(false);

       
    }
    
    
    public void repintarLabel(int numero, int posicionX, int posicionY) {
        labelsCorredores[numero].setLocation(posicionX,posicionY);
        labelsCorredores[numero].repaint();
    }
    public void mostrarJugador(int numero) {
        labelsCorredores[numero].setVisible(true);
    }
    public void ocultarPanelGanador(){
        this.vPrincipalLblGanador.setVisible(false);
        
        
    }
    public void mostrarGanador(String msj){
        this.vPrincipalLblGanador.setVisible(true);
        this.vPrincipalLblGanador.setText(msj);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        vPrincipalLblGanador = new javax.swing.JLabel();
        vPrincipalLblCorredor5 = new javax.swing.JLabel();
        vPrincipalLblCorredor3 = new javax.swing.JLabel();
        vPrincipalLblCorredor4 = new javax.swing.JLabel();
        vPrincipalLblCorredor2 = new javax.swing.JLabel();
        vPrincipalLblCorredor1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        vPrincipalBtnIniciar = new javax.swing.JButton();
        vPrincipalBtnSalir = new javax.swing.JButton();
        vPrincipalBtnAccidente = new javax.swing.JButton();
        vPrincipalBtnImpulsar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        vPrincipalBtnAgregarCorredor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(106, 138, 86));

        jPanel2.setLayout(null);

        vPrincipalLblGanador.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        vPrincipalLblGanador.setForeground(new java.awt.Color(255, 255, 255));
        vPrincipalLblGanador.setText("Gano Tal");
        jPanel2.add(vPrincipalLblGanador);
        vPrincipalLblGanador.setBounds(30, 30, 920, 40);

        vPrincipalLblCorredor5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/progavud/taller3/img/processed_gif_1.gif"))); // NOI18N
        vPrincipalLblCorredor5.setText("jLabel2");
        jPanel2.add(vPrincipalLblCorredor5);
        vPrincipalLblCorredor5.setBounds(0, 100, 80, 70);

        vPrincipalLblCorredor3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/progavud/taller3/img/processed_gif_3.gif"))); // NOI18N
        vPrincipalLblCorredor3.setText("jLabel2");
        jPanel2.add(vPrincipalLblCorredor3);
        vPrincipalLblCorredor3.setBounds(0, 270, 80, 70);

        vPrincipalLblCorredor4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/progavud/taller3/img/processed_gif_2.gif"))); // NOI18N
        vPrincipalLblCorredor4.setText("jLabel2");
        jPanel2.add(vPrincipalLblCorredor4);
        vPrincipalLblCorredor4.setBounds(0, 190, 80, 70);

        vPrincipalLblCorredor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/progavud/taller3/img/perrorosa-ezgif.com-effects.gif"))); // NOI18N
        vPrincipalLblCorredor2.setText("jLabel2");
        jPanel2.add(vPrincipalLblCorredor2);
        vPrincipalLblCorredor2.setBounds(0, 350, 80, 70);

        vPrincipalLblCorredor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/progavud/taller3/img/negro.gif"))); // NOI18N
        vPrincipalLblCorredor1.setText("jLabel2");
        jPanel2.add(vPrincipalLblCorredor1);
        vPrincipalLblCorredor1.setBounds(0, 430, 80, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/progavud/taller3/img/flipped_resized_image.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 990, 610);

        vPrincipalBtnIniciar.setBackground(new java.awt.Color(102, 204, 0));
        vPrincipalBtnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vPrincipalBtnIniciar.setText("Iniciar Carrera");
        vPrincipalBtnIniciar.setActionCommand("inicia");

        vPrincipalBtnSalir.setBackground(new java.awt.Color(102, 204, 0));
        vPrincipalBtnSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vPrincipalBtnSalir.setText("Salir");
        vPrincipalBtnSalir.setActionCommand("salir");

        vPrincipalBtnAccidente.setBackground(new java.awt.Color(102, 204, 0));
        vPrincipalBtnAccidente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vPrincipalBtnAccidente.setText("Accidente 1");
        vPrincipalBtnAccidente.setActionCommand("accidente");

        vPrincipalBtnImpulsar.setBackground(new java.awt.Color(102, 204, 0));
        vPrincipalBtnImpulsar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vPrincipalBtnImpulsar.setText("Impulsar 2");
        vPrincipalBtnImpulsar.setActionCommand("impulsar");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setText("Carrera Amazonica");

        vPrincipalBtnAgregarCorredor.setBackground(new java.awt.Color(102, 204, 0));
        vPrincipalBtnAgregarCorredor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vPrincipalBtnAgregarCorredor.setText("Agregar Corredor");
        vPrincipalBtnAgregarCorredor.setActionCommand("agregaCorredor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(vPrincipalBtnIniciar)
                .addGap(58, 58, 58)
                .addComponent(vPrincipalBtnAgregarCorredor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vPrincipalBtnAccidente, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vPrincipalBtnImpulsar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(vPrincipalBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(331, 331, 331))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vPrincipalBtnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vPrincipalBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vPrincipalBtnAccidente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vPrincipalBtnImpulsar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vPrincipalBtnAgregarCorredor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JButton vPrincipalBtnAccidente;
    public javax.swing.JButton vPrincipalBtnAgregarCorredor;
    public javax.swing.JButton vPrincipalBtnImpulsar;
    public javax.swing.JButton vPrincipalBtnIniciar;
    public javax.swing.JButton vPrincipalBtnSalir;
    public javax.swing.JLabel vPrincipalLblCorredor1;
    public javax.swing.JLabel vPrincipalLblCorredor2;
    public javax.swing.JLabel vPrincipalLblCorredor3;
    public javax.swing.JLabel vPrincipalLblCorredor4;
    public javax.swing.JLabel vPrincipalLblCorredor5;
    public javax.swing.JLabel vPrincipalLblGanador;
    // End of variables declaration//GEN-END:variables
}
