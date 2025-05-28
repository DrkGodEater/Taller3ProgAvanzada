/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3.control;



/**
 *
 * @author a
 */
public class ControlPrincipal {
    private Fachada fachada;
    private ControlCorredor cCorredor;
    
    
    public void empiezaLaCarrera() {
        Thread thread = new Thread(this.cCorredor.crearCorredor("Usain Bolt", 100));
        Thread thread2 = new Thread(this.cCorredor.crearCorredor("Periquin", 1000));
        thread.start();
        thread2.start();
        try {
        thread.join();
        thread2.join();
        }catch(InterruptedException itex) {
            //Decir Algo bue, igual nunca va a pasar
        }
        
    }
    public void anunciarGanador() {
        this.fachada.getvPrincipal().mostrarMensaje("El ganador es: " + this.cCorredor.quienEsElGanador());
    }
    public ControlPrincipal() {
        this.fachada = new Fachada(this);
        this.cCorredor = new ControlCorredor(this);
        empiezaLaCarrera();
    }
    
}
