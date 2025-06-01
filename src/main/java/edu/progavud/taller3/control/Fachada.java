package edu.progavud.taller3.control;

import edu.progavud.taller3.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el patrón Facade para gestionar la interacción entre
 * la vista principal y el controlador de la aplicación de carreras.
 * Actúa como intermediario manejando los eventos de la interfaz gráfica
 * y delegando las operaciones correspondientes al controlador principal.
 * 
 * @author Alex M
 * @author batapop
 * @author carlosmamut
 * @version 1.0
 */
public class Fachada implements ActionListener{
    private ControlPrincipal cPrincipal;
    private VentanaPrincipal vPrincipal;

    /**
     * Constructor que inicializa la fachada con el controlador principal.
     * Crea la ventana principal, la hace visible y registra los listeners
     * para todos los botones de la interfaz.
     * 
     * @param cPrincipal el controlador principal que manejará la lógica de negocio
     */
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

    /**
     * Maneja todos los eventos de acción generados por los botones de la interfaz.
     * Identifica el comando de acción y delega la operación correspondiente
     * al controlador principal o actualiza la vista según sea necesario.
     * 
     * @param e el evento de acción que contiene información sobre qué botón fue presionado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("salir".equals(e.getActionCommand())) {
            vPrincipal.setVisible(false);
            this.cPrincipal.mostrarMaxGanador();
            vPrincipal.dispose();
        }
        if ("inicia".equals(e.getActionCommand())) {
            this.vPrincipal.ocultarPanelGanador();
            this.cPrincipal.reiniciarCarrera();
            this.cPrincipal.empiezaLaCarrera();
        }
        if ("agregaCorredor".equals(e.getActionCommand())) {
            this.vPrincipal.mostrarJugador(this.cPrincipal.agregarCorredorALaPista());
        }
        if ("accidente".equals(e.getActionCommand())) {
            this.cPrincipal.accidentarYMostrarAQueCorredor();
        }
        if ("impulsar".equals(e.getActionCommand())) {
            this.cPrincipal.impulsarYMostrarAQueCorredor();
        }
    }
    
    public VentanaPrincipal getvPrincipal() {
        return vPrincipal;
    }

    public void setvPrincipal(VentanaPrincipal vPrincipal) {
        this.vPrincipal = vPrincipal;
    }
}