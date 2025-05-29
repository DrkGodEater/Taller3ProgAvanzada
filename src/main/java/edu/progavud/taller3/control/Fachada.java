
package edu.progavud.taller3.control;

import edu.progavud.taller3.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author a
 */
public class Fachada implements ActionListener{
    private ControlPrincipal cPrincipal;
    private VentanaPrincipal vPrincipal;

    public Fachada(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.vPrincipal = new VentanaPrincipal(this);
        
        this.vPrincipal.setVisible(true);
        this.vPrincipal.vPrincipalBtnSalir.addActionListener(this);
        this.vPrincipal.vPrincipalBtnImpulsar.addActionListener(this);
        this.vPrincipal.vPrincipalBtnIniciar.addActionListener(this);
        this.vPrincipal.vPrincipalBtnAgregarCorredor.addActionListener(this);
        this.vPrincipal.vPrincipalBtnAccidente.addActionListener(this);
        
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("salir".equals(e.getActionCommand())) {
            vPrincipal.setVisible(false);
            vPrincipal.dispose();
        }
        if ("inicia".equals(e.getActionCommand())) {
            this.vPrincipal.ocultarPanelGanador();
            this.cPrincipal.reiniciarCarrera();
            this.cPrincipal.empiezaLaCarrera();
        }
        if ("agregaCorredor".equals(e.getActionCommand())) {
            this.cPrincipal.agregarCorredorALaPista("Usain Bolt", 10);
            this.cPrincipal.agregarCorredorALaPista("Periquito", 1000);
            this.cPrincipal.agregarCorredorALaPista("Pericote", 1000);
            this.cPrincipal.agregarCorredorALaPista("Alex", 1000);
        }
        if ("accidente".equals(e.getActionCommand())) {
            
        }
        if ("impulsar".equals(e.getActionCommand())) {
            
        }
    }
    
    public VentanaPrincipal getvPrincipal() {
        return vPrincipal;
    }

    public void setvPrincipal(VentanaPrincipal vPrincipal) {
        this.vPrincipal = vPrincipal;
    }
    
    
    
}
