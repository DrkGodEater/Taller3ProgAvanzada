/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3.modelo;

import java.util.Random;

/**
 *
 * @author a
 */
public class Corredor implements Runnable {
    private String nombre;
    private int posicionX = 0;
    private int posicionY;
    private static volatile String ganadorDeLaCarrera = null;
    private CarreraObserver observer;

    public Corredor(String nombre, int posicionY, CarreraObserver observer) {
        this.nombre = nombre;
        this.posicionY = posicionY;
        this.observer = observer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public static String getGanadorDeLaCarrera() {
        return ganadorDeLaCarrera;
    }

    public static void setGanadorDeLaCarrera(String ganadorDeLaCarrera) {
        Corredor.ganadorDeLaCarrera = ganadorDeLaCarrera;
    }

    @Override
    public void run() {
        while(posicionX < 200) {
        
        Random random = new Random();
        this.setPosicionX(posicionX + 5);
        try {
            Thread.sleep(random.nextInt(3000) + 1000);
        }catch(InterruptedException ex) {
            System.exit(0);
        }
        }
        if(ganadorDeLaCarrera == null) {
            ganadorDeLaCarrera = this.nombre;
            this.observer.corredorLlego(ganadorDeLaCarrera);
        }

    }
  
    
    
}
