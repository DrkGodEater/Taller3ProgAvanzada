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
    private Corredor corredor;
    private ControlCorredor controlCorredor;
    private static final int META = 50;
    private static final int AVANCE_POR_PASO = 5;
    private static final int TIEMPO_MIN_ESPERA = 1000;
    private static final int TIEMPO_MAX_ESPERA = 3000;
    
    public ControlCarrera(Corredor corredor, ControlCorredor controlCorredor) {
        this.corredor = corredor;
        this.controlCorredor = controlCorredor;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        
        while(this.corredor.getPosicionX() < META && 
              Corredor.getGanadorDeLaCarrera() == null) {
            
            // Avanzar el corredor
            this.corredor.setPosicionX(this.corredor.getPosicionX() + AVANCE_POR_PASO);
            
            // Log del progreso
            System.out.println(this.corredor.getNombre() + 
                             " (Corredor #" + this.corredor.getNumCorredor() + 
                             ") ha avanzado: " + this.corredor.getPosicionX());
            
            try {
                // Tiempo de espera aleatorio
                Thread.sleep(random.nextInt(TIEMPO_MAX_ESPERA - TIEMPO_MIN_ESPERA) + TIEMPO_MIN_ESPERA);
            } catch(InterruptedException ex) {
                System.out.println("Carrera interrumpida para " + this.corredor.getNombre());
                return;
            }
        }
        
        // Verificar si este corredor es el ganador
        if(Corredor.getGanadorDeLaCarrera() == null) {
            Corredor.setGanadorDeLaCarrera(this.corredor.getNombre());
            this.controlCorredor.corredorLlego(this.corredor.getNombre());
        }
    }
    
    public Corredor getCorredor() {
        return corredor;
    }
}

