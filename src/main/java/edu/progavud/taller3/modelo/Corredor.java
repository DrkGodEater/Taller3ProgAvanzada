package edu.progavud.taller3.modelo;

import java.util.Random;

/**
 * Representa un corredor en la simulación de carrera amazónica.
 * Esta clase maneja toda la información relacionada con un participante
 * individual de la carrera, incluyendo su posición, estado y estadísticas.
 * 
 * @author Alex M
 * @author batapop
 * @author carlosmamut
 * @version 1.0
 */
public class Corredor {
    
    private String nombre;
    private int posicionX = 0;
    private int posicionY;
    private int numCorredor;
    private static volatile String ganadorDeLaCarrera = null;
    private int victorias = 0;
    private boolean accidentado = false;

    /**
     * Constructor principal de la clase Corredor.
     * Inicializa un nuevo corredor con los parámetros especificados.
     * 
     * @param nombre El nombre del corredor
     * @param posicionY La posición vertical (Y) del corredor en la pista
     * @param numCorredor El número identificador único del corredor
     */
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

    /**
     * Obtiene el nombre del ganador de la carrera actual.
     * Este método es estático y thread-safe.
     * 
     * @return El nombre del corredor ganador, o null si la carrera aún no ha terminado
     */
    public static String getGanadorDeLaCarrera() {
        return ganadorDeLaCarrera;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    /**
     * Establece el ganador de la carrera actual.
     * Este método es estático y thread-safe, utilizado cuando un corredor
     * cruza la línea de meta.
     * 
     * @param ganadorDeLaCarrera El nombre del corredor que ha ganado la carrera
     */
    public static void setGanadorDeLaCarrera(String ganadorDeLaCarrera) {
        Corredor.ganadorDeLaCarrera = ganadorDeLaCarrera;
    }

    public boolean isAccidentado() {
        return accidentado;
    }

    public void setAccidentado(boolean accidentado) {
        this.accidentado = accidentado;
    }
}