package edu.progavud.taller3.control;

import edu.progavud.taller3.modelo.Corredor;
import java.util.Random;

/**
 * Controlador que maneja la lógica de ejecución de la carrera para un corredor
 * específico. Implementa Runnable para permitir que cada corredor corra en su
 * propio hilo de ejecución, gestionando el avance, accidentes e impulsos
 * durante la carrera hasta que alguien llegue a la meta.
 *
 * @author Alex M
 * @author batapop
 * @author carlosmamut
 * @version 1.0
 */
public class ControlCarrera implements Runnable {

    private ControlPrincipal cPrincipal;
    private int numCorredor;
    private ControlCorredor cCorredor;
    private static final int META = 900;
    private static final int AVANCE_POR_PASO = 20;
    private static final int TIEMPO_MIN_ESPERA = 500;
    private static final int TIEMPO_MAX_ESPERA = 1000;

    /**
     * Constructor que inicializa el controlador de carrera para un corredor
     * específico.
     *
     * @param numCorredor el número identificador del corredor que será
     * controlado por este hilo
     * @param cCorredor el controlador de corredores que maneja la colección de
     * participantes
     * @param cPrincipal el controlador principal que coordina la aplicación
     */
    public ControlCarrera(int numCorredor, ControlCorredor cCorredor, ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.numCorredor = numCorredor;
        this.cCorredor = cCorredor;
    }

    /**
     * Aplica un impulso aleatorio a un corredor seleccionado por índice. El
     * impulso es un avance adicional de entre 30 y 79 píxeles que se suma a la
     * posición actual del corredor y actualiza su representación visual.
     *
     * @param numeroRandom el índice del corredor en la lista que recibirá el
     * impulso
     */
    public void impulsarJugadorRandom(int numeroRandom) {
        Random random = new Random();
        int impulsoRandom = random.nextInt(50) + 30;
        Corredor corredorSeleccionado = this.cCorredor.getCorredores().get(numeroRandom);
        corredorSeleccionado.setPosicionX(corredorSeleccionado.getPosicionX() + impulsoRandom);
        this.cPrincipal.repintarElLabel(corredorSeleccionado.getNumCorredor(), corredorSeleccionado.getPosicionX(), corredorSeleccionado.getPosicionY());
    }

    /**
     * Causa un accidente temporal a un corredor seleccionado por índice. El
     * corredor afectado se marca como accidentado y queda inmóvil por un tiempo
     * aleatorio entre 1000 y 2499 milisegundos, después del cual puede
     * continuar corriendo.
     *
     * @param numeroRandom el índice del corredor en la lista que sufrirá el
     * accidente
     */
    public void accidentarJugadorRandom(int numeroRandom) {
        Random random = new Random();
        Corredor corredorSeleccionado = this.cCorredor.getCorredores().get(numeroRandom);
        corredorSeleccionado.setAccidentado(true);
        Thread accidenteThread = new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1500) + 1000);
            } catch (InterruptedException ex) {
            } finally {
                // Desmarcar al corredor como accidentado
                corredorSeleccionado.setAccidentado(false);
            }
        });

        accidenteThread.start();
    }

    /**
     * Ejecuta la lógica principal de la carrera para el corredor asignado. El
     * corredor avanza hacia la meta en incrementos regulares, con pausas
     * aleatorias entre movimientos. Si el corredor está accidentado, no avanza
     * durante ese período. La carrera continúa hasta que este corredor u otro
     * llegue a la meta. Al finalizar, si este corredor es el primero en llegar,
     * se declara ganador.
     */
    @Override
    public void run() {
        Random random = new Random();
        Corredor corredorPivot = this.cCorredor.getCorredores().get(numCorredor);
        while (corredorPivot.getPosicionX() < META
                && Corredor.getGanadorDeLaCarrera() == null) {

            // Avanzar el corredor
            if (!corredorPivot.isAccidentado()) {
                corredorPivot.setPosicionX(corredorPivot.getPosicionX() + AVANCE_POR_PASO);
                this.cPrincipal.repintarElLabel(corredorPivot.getNumCorredor(), corredorPivot.getPosicionX(), corredorPivot.getPosicionY());
                // Log del progreso
            }
            try {
                // Tiempo de espera aleatorio
                Thread.sleep(random.nextInt(TIEMPO_MAX_ESPERA - TIEMPO_MIN_ESPERA) + TIEMPO_MIN_ESPERA);
            } catch (InterruptedException ex) {

                return;
            }
        }

        // Verificar si este corredor es el ganador
        if (Corredor.getGanadorDeLaCarrera() == null) {
            Corredor.setGanadorDeLaCarrera(corredorPivot.getNombre());
            this.cCorredor.corredorLlego(corredorPivot.getNombre());
            corredorPivot.setVictorias(corredorPivot.getVictorias() + 1);
        }
    }

}
