/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3.control;

import edu.progavud.taller3.modelo.Corredor;
import java.util.ArrayList;
import javax.swing.SwingUtilities;



/**
 *
 * @author a
 */
public class ControlPrincipal {
    private Fachada fachada;
    private ControlCorredor cCorredor;
    
    
    public void empiezaLaCarrera() {
        ArrayList<Corredor> corredores = this.cCorredor.getCorredores();
        if(!corredores.isEmpty()) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < corredores.size(); i++) {
            threads.add(new Thread(corredores.get(i)));
            threads.get(i).start();
        }
        try {
        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }
        }catch(InterruptedException itex) {
            this.fachada.getvPrincipal().mostrarMensaje("Se ha parado la ejecucion");
        }
        this.fachada.getvPrincipal().mostrarMensaje("La carrera se ha terminado");
        }
        else {
            this.fachada.getvPrincipal().mostrarMensaje("No puedes iniciar una carrera sin ningun corredor");
        } 
    }
    
    public void agregarCorredorALaPista(String nombre, int posicionY) {
        this.cCorredor.crearCorredor(nombre, posicionY);
    }
    public void anunciarGanador() {
        String ganador = this.cCorredor.quienEsElGanador();
        if(ganador != null) {
            this.fachada.getvPrincipal().mostrarMensaje("¡El ganador es: " + ganador + "!");
        }
    }
    
    public ControlPrincipal() {
        this.fachada = new Fachada(this);
        this.cCorredor = new ControlCorredor(this);
    }
    
}
