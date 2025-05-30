package edu.progavud.taller3.modelo;

import java.util.Random;

/**
 *
 * @author a
 */
public class Corredor {
    private String nombre;
    private int posicionX = 0;
    private int posicionY;
    private int numCorredor;
    private static volatile String ganadorDeLaCarrera = null;
    private int victorias = 0;

    public Corredor(String nombre, int posicionY, int numCorredor) {
        this.nombre = nombre;
        this.posicionY = posicionY;
        this.numCorredor = numCorredor;
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

    public int getNumCorredor() {
        return numCorredor;
    }

    public void setNumCorredor(int numCorredor) {
        this.numCorredor = numCorredor;
    }

    public static String getGanadorDeLaCarrera() {
        return ganadorDeLaCarrera;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public static void setGanadorDeLaCarrera(String ganadorDeLaCarrera) {
        Corredor.ganadorDeLaCarrera = ganadorDeLaCarrera;
    }

}