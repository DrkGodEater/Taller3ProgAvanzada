/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3.control;

import edu.progavud.taller3.modelo.Corredor;
import java.util.Random;

/**
 *
 * @author carlosmamut1
 */
public class ControlCarrera implements Runnable {
    private ControlPrincipal cPrincipal;
    private int  numCorredor;
    private ControlCorredor cCorredor;
    private static final int META = 900;
    private static final int AVANCE_POR_PASO = 20;
    private static final int TIEMPO_MIN_ESPERA = 500;
    private static final int TIEMPO_MAX_ESPERA = 1500;
    
    public ControlCarrera(int numCorredor, ControlCorredor cCorredor, ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.numCorredor = numCorredor;
        this.cCorredor = cCorredor;
    }
    public void impulsarJugadorRandom(int numeroRandom) {
        Random random = new Random();
        int impulsoRandom = random.nextInt(50) + 30;
        Corredor corredorSeleccionado = this.cCorredor.getCorredores().get(numeroRandom);
        corredorSeleccionado.setPosicionX(corredorSeleccionado.getPosicionX() + impulsoRandom);
        this.cPrincipal.repintarElLabel(corredorSeleccionado.getNumCorredor(), corredorSeleccionado.getPosicionX(), corredorSeleccionado.getPosicionY());
    }

    


    @Override
    public void run() {
        Random random = new Random();
        Corredor corredorPivot = this.cCorredor.getCorredores().get(numCorredor);
        while(corredorPivot.getPosicionX() < META && 
              Corredor.getGanadorDeLaCarrera() == null) {
            
            // Avanzar el corredor
            corredorPivot.setPosicionX(corredorPivot.getPosicionX() + AVANCE_POR_PASO);
            this.cPrincipal.repintarElLabel(corredorPivot.getNumCorredor(), corredorPivot.getPosicionX(), corredorPivot.getPosicionY());
            // Log del progreso
            
            try {
                // Tiempo de espera aleatorio
                Thread.sleep(random.nextInt(TIEMPO_MAX_ESPERA - TIEMPO_MIN_ESPERA) + TIEMPO_MIN_ESPERA);
            } catch(InterruptedException ex) {
 
                return;
            }
        }
        
        // Verificar si este corredor es el ganador
        if(Corredor.getGanadorDeLaCarrera() == null) {
            Corredor.setGanadorDeLaCarrera(corredorPivot.getNombre());
            this.cCorredor.corredorLlego(corredorPivot.getNombre());
            corredorPivot.setVictorias(corredorPivot.getVictorias() + 1);
        }
    }
    

}

