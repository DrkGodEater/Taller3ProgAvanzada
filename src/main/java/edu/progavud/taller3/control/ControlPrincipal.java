package edu.progavud.taller3.control;

import edu.progavud.taller3.modelo.Corredor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Controlador principal que coordina la lógica de la aplicación de carreras.
 * Actúa como el núcleo del sistema, gestionando el flujo de la carrera, la
 * interacción entre componentes y el manejo de eventos del usuario.
 *
 * @author Alex M
 * @author batapop
 * @author carlosmamut
 * @version 1.0
 */
public class ControlPrincipal {

    private ControlCarrera cCarrera;
    private Fachada fachada;
    private ControlCorredor cCorredor;
    private int segundosQuePasaron;

    /**
     * Constructor que inicializa los componentes principales del sistema.
     * Establece la fachada para la interfaz de usuario y el controlador de
     * corredores para la gestión de participantes.
     */
    public ControlPrincipal() {
        this.fachada = new Fachada(this);
        this.cCorredor = new ControlCorredor(this);
    }

    /**
     * Determina y muestra el corredor con más victorias acumuladas. En caso de
     * empate, muestra todos los corredores empatados. Se ejecuta típicamente al
     * finalizar la aplicación.
     */
    public void mostrarMaxGanador() {
        String maxGanador = "";
        int maximasVictorias = 0;
        ArrayList<Corredor> corredores = this.cCorredor.getCorredores();
        for (int i = 0; i < corredores.size(); i++) {
            if (maximasVictorias < corredores.get(i).getVictorias() || maxGanador.equals("")) {
                maxGanador = corredores.get(i).getNombre();
                maximasVictorias = corredores.get(i).getVictorias();
            } else if (maximasVictorias == corredores.get(i).getVictorias()) {
                maxGanador = maxGanador + " y " + corredores.get(i).getNombre();
            }
        }
        this.fachada.getvPrincipal().mostrarMensaje("Maximo Ganador(s): " + maxGanador
                + "\nVictorias: " + maximasVictorias);
    }

    /**
     * Inicia una nueva carrera con todos los corredores registrados. Resetea
     * las posiciones, inicia el cronómetro y ejecuta la carrera en hilos
     * separados para mantener la responsividad de la UI.
     */
    public void empiezaLaCarrera() {
        segundosQuePasaron = 0;
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                segundosQuePasaron += 1;
            }

        });

        ArrayList<Corredor> corredores = this.cCorredor.getCorredores();

        if (!corredores.isEmpty()) {
            // REINICIAR TODO ANTES DE EMPEZAR LA CARRERA
            Corredor.setGanadorDeLaCarrera(null); // Resetear ganador

            // Resetear posición de todos los corredores
            for (Corredor corredor : corredores) {
                corredor.setPosicionX(0);
            }

            // Ejecutar la carrera en un hilo separado para no bloquear la UI
            timer.start();
            Thread carreraThread = new Thread(() -> {
                ArrayList<Thread> threads = new ArrayList<>();

                // Crear ControlCarrera para cada corredor y empezar los hilos
                for (int i = 0; i < corredores.size(); i++) {
                    this.cCarrera = new ControlCarrera(i, this.cCorredor, this);
                    Thread thread = new Thread(this.cCarrera);
                    threads.add(thread);
                    thread.start();
                }

                try {
                    // Esperar a que terminen todos los corredores
                    for (Thread thread : threads) {
                        thread.join();
                    }
                } catch (InterruptedException itex) {
                    // Actualizar UI desde el EDT
                    SwingUtilities.invokeLater(() -> {
                        this.fachada.getvPrincipal().mostrarMensaje("Se ha parado la ejecución");
                    });
                    return;
                }

                // Actualizar UI desde el EDT cuando termine la carrera
                SwingUtilities.invokeLater(() -> {

                    this.fachada.getvPrincipal().mostrarMensaje("La carrera se ha terminado");
                    timer.stop();
                });
            });

            carreraThread.start();

        } else {
            this.fachada.getvPrincipal().mostrarMensaje("No puedes iniciar una carrera sin ningún corredor");
        }
    }

    /**
     * Selecciona aleatoriamente un corredor y le otorga un impulso adicional.
     * El impulso permite al corredor avanzar más rápido temporalmente.
     */
    public void impulsarYMostrarAQueCorredor() {
        Random random = new Random();
        int numDeJugadorRandom = random.nextInt(this.cCorredor.getCantidadCorredores());
        this.fachada.getvPrincipal().mostrarMensaje("El jugador número: " + (numDeJugadorRandom + 1) + " Ha recibido un impulso");
        this.cCarrera.impulsarJugadorRandom(numDeJugadorRandom);
    }

    /**
     * Selecciona aleatoriamente un corredor y lo accidenta temporalmente. El
     * corredor accidentado se detiene por un período de tiempo determinado.
     */
    public void accidentarYMostrarAQueCorredor() {
        Random random = new Random();
        int numDeJugadorRandom = random.nextInt(this.cCorredor.getCantidadCorredores());
        this.fachada.getvPrincipal().mostrarMensaje("El jugador número: " + (numDeJugadorRandom + 1) + " Ha tenido un accidente y se detendrá por 5 segundos");
        this.cCarrera.accidentarJugadorRandom(numDeJugadorRandom);
    }

    /**
     * Permite agregar un nuevo corredor a la carrera. Solicita al usuario el
     * nombre del corredor y lo posiciona en la pista.
     *
     * @return El número identificador del corredor agregado
     */
    public int agregarCorredorALaPista() {
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
        return this.cCorredor.getCorredor().getNumCorredor();
    }

    /**
     * Actualiza la posición visual de un corredor en la interfaz gráfica.
     *
     * @param numero Número identificador del corredor
     * @param locationX Nueva posición horizontal
     * @param locationY Nueva posición vertical
     */
    public void repintarElLabel(int numero, int locationX, int locationY) {
        this.fachada.getvPrincipal().repintarLabel(numero, locationX, locationY);
    }

    /**
     * Calcula la posición vertical (Y) correspondiente a cada carril de
     * corredor.
     *
     * @param numCorredor El número del corredor (1-5)
     * @return La coordenada Y correspondiente al carril
     */
    public int getPosicionY(int numCorredor) {
        // Calcular posición Y basada en el número de corredor
        switch (numCorredor) {
            case 1:
                return 430; // vPrincipalLblCorredor1
            case 2:
                return 350; // vPrincipalLblCorredor2
            case 3:
                return 270; // vPrincipalLblCorredor3
            case 4:
                return 190; // vPrincipalLblCorredor4
            case 5:
                return 100; // vPrincipalLblCorredor5
            default:
                return 430;
        }
    }

    /**
     * Anuncia el ganador de la carrera actual. Utiliza SwingUtilities para
     * asegurar que la actualización de UI se ejecute en el Event Dispatch
     * Thread.
     */
    public void anunciarGanador() {
        String ganador = this.cCorredor.quienEsElGanador();
        if (ganador != null) {
            // Asegurar que la actualización de UI se haga en el EDT
            SwingUtilities.invokeLater(() -> {
                this.fachada.getvPrincipal().mostrarGanador("El ganador es: " + ganador + " (" + this.segundosQuePasaron + " segs)");
            });
        }
    }

    /**
     * Reinicia el estado de la carrera para una nueva competencia. Resetea el
     * ganador y las posiciones de todos los corredores.
     */
    public void reiniciarCarrera() {
        // Resetear el ganador
        Corredor.setGanadorDeLaCarrera(null);

        // Resetear posiciones de todos los corredores
        ArrayList<Corredor> corredores = this.cCorredor.getCorredores();
        for (Corredor corredor : corredores) {
            corredor.setPosicionX(0);
        }
    }
}
