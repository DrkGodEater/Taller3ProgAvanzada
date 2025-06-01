package edu.progavud.taller3.modelo;

/**
 * Interface que implementa el patrón Observer para notificar eventos de la carrera.
 * Permite que las clases interesadas reciban notificaciones cuando un corredor
 * llega a la meta, facilitando la comunicación entre los componentes del sistema
 * sin crear dependencias directas.
 * 
 * @author Alex M
 * @author batapop
 * @author carlosmamut
 * @version 1.0
 */
public interface CarreraObserver {
    
    /**
     * Método callback que se ejecuta cuando un corredor cruza la línea de meta.
     * Las clases que implementen esta interface deben definir qué acciones
     * realizar cuando se recibe esta notificación (mostrar ganador, actualizar UI, etc.).
     * 
     * @param nombre El nombre del corredor que ha llegado a la meta
     */
    public void corredorLlego(String nombre);
}