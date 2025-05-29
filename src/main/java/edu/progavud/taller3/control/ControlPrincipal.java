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
            // REINICIAR TODO ANTES DE EMPEZAR LA CARRERA
            Corredor.setGanadorDeLaCarrera(null); // Resetear ganador
            
            // Resetear posición de todos los corredores
            for(Corredor corredor : corredores) {
                corredor.setPosicionX(0);
            }
            
            // Ejecutar la carrera en un hilo separado para no bloquear la UI
            Thread carreraThread = new Thread(() -> {
                ArrayList<Thread> threads = new ArrayList<>();
                
                // Crear y empezar los hilos de los corredores
                for(int i = 0; i < corredores.size(); i++) {
                    threads.add(new Thread(corredores.get(i)));
                    threads.get(i).start();
                }
                
                try {
                    // Esperar a que terminen todos los corredores
                    for(int i = 0; i < threads.size(); i++) {
                        threads.get(i).join();
                    }
                } catch(InterruptedException itex) {
                    // Actualizar UI desde el EDT
                    SwingUtilities.invokeLater(() -> {
                        this.fachada.getvPrincipal().mostrarMensaje("Se ha parado la ejecución");
                    });
                    return;
                }
                
                // Actualizar UI desde el EDT cuando termine la carrera
                SwingUtilities.invokeLater(() -> {
                    this.fachada.getvPrincipal().mostrarMensaje("La carrera se ha terminado");
                });
            });
            
            carreraThread.start();
            
        } else {
            this.fachada.getvPrincipal().mostrarMensaje("No puedes iniciar una carrera sin ningún corredor");
        } 
    }
    
    public void agregarCorredorALaPista(String nombre, int posicionY) {
        this.cCorredor.crearCorredor(nombre, posicionY);
    }
    
    public void anunciarGanador() {
        String ganador = this.cCorredor.quienEsElGanador();
        if(ganador != null) {
            // Asegurar que la actualización de UI se haga en el EDT
            SwingUtilities.invokeLater(() -> {
                this.fachada.getvPrincipal().mostrarGanador("El ganador es: " + ganador);
            });
        }
    }
    
    public void reiniciarCarrera() {
        // Resetear el ganador
        Corredor.setGanadorDeLaCarrera(null);
        
        // Resetear posiciones de todos los corredores
        ArrayList<Corredor> corredores = this.cCorredor.getCorredores();
        for(Corredor corredor : corredores) {
            corredor.setPosicionX(0);
        }
    }
    
    public ControlPrincipal() {
        this.fachada = new Fachada(this);
        this.cCorredor = new ControlCorredor(this);
    }
}