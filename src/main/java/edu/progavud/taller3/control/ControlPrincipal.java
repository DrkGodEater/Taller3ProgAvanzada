package edu.progavud.taller3.control;

import edu.progavud.taller3.modelo.Corredor;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.SwingUtilities;

/**
 * Main control class
 * @author a
 */
public class ControlPrincipal {
    private ControlCarrera cCarrera;
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
                
                // Crear ControlCarrera para cada corredor y empezar los hilos
                for(int i = 0; i < corredores.size(); i++) {
                    this.cCarrera = new ControlCarrera(i, this.cCorredor,this);
                    Thread thread = new Thread(this.cCarrera);
                    threads.add(thread);
                    thread.start();
                }
                
                try {
                    // Esperar a que terminen todos los corredores
                    for(Thread thread : threads) {
                        thread.join();
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
    public void impulsarYMostrarAQueCorredor() {
       Random random = new Random();
       int numDeJugadorRandom = random.nextInt(this.cCorredor.getCantidadCorredores());
       this.fachada.getvPrincipal().mostrarMensaje("El jugador número: " + (numDeJugadorRandom + 1) + " Ha recibido un impulso");
       this.cCarrera.impulsarJugadorRandom(numDeJugadorRandom);
    }
    public void agregarCorredorALaPista() {
        if (this.cCorredor.puedeAgregarCorredor()) {
            String nombre = this.fachada.getvPrincipal().registrarCorredor(this.cCorredor.getCantidadCorredores() + 1);
            
            if (nombre != null && !nombre.trim().isEmpty()) {
                // Calcular posición Y basada en el número de corredor
                int numCorredor = this.cCorredor.getCantidadCorredores() + 1;
                int posicionY = getPosicionY(numCorredor);
                
                this.cCorredor.crearCorredor(nombre.trim(), posicionY);
                this.fachada.getvPrincipal().mostrarMensaje("Corredor '" + nombre.trim() + "' agregado como Corredor #" + numCorredor);
            } else {
                this.fachada.getvPrincipal().mostrarMensaje("Debe ingresar un nombre válido para el corredor");
            }
        } else {
            this.fachada.getvPrincipal().mostrarMensaje("No se pueden agregar más corredores. Máximo 5 corredores.");
        }
    }
    public void repintarElLabel(int numero, int locationX, int locationY) {
        this.fachada.getvPrincipal().repintarLabel(numero, locationX, locationY);
    }
    private int getPosicionY(int numCorredor) {
        // Calcular posición Y basada en el número de corredor
        switch(numCorredor) {
            case 1: return 430; // vPrincipalLblCorredor1
            case 2: return 350; // vPrincipalLblCorredor2
            case 3: return 270; // vPrincipalLblCorredor3
            case 4: return 190; // vPrincipalLblCorredor4
            case 5: return 100; // vPrincipalLblCorredor5
            default: return 430;
        }
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