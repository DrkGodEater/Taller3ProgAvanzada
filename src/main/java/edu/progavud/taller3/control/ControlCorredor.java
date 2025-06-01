package edu.progavud.taller3.control;
import edu.progavud.taller3.modelo.CarreraObserver;
import edu.progavud.taller3.modelo.Corredor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Controlador encargado de gestionar todos los aspectos relacionados con los corredores
 * en la aplicación de carreras. Implementa el patrón Observer para recibir notificaciones
 * cuando un corredor llega a la meta y maneja la creación, búsqueda y administración
 * de corredores dentro de la carrera.
 * 
 * @author Alex M
 * @author batapop
 * @author carlosmamut
 * @version 1.0
 */
public class ControlCorredor implements CarreraObserver {
    private ControlPrincipal cPrincipal;
    private ArrayList<Corredor> corredores;
    private Corredor corredor;
    
    /**
     * Crea un nuevo corredor con el nombre y posición especificados.
     * Asigna automáticamente un número de corredor basado en el tamaño actual
     * de la lista de corredores y lo añade a la colección.
     * 
     * @param nombre el nombre del corredor a crear
     * @param posicionY la posición vertical en la que se ubicará el corredor en la pista
     * @return el corredor recién creado
     */
    public Corredor crearCorredor(String nombre, int posicionY) {
        corredor = new Corredor(nombre, posicionY, corredores.size());
        this.corredores.add(corredor);
        return corredor;
    }
    
    /**
     * Busca un corredor específico por su número identificador.
     * Utiliza un iterador para recorrer la lista de corredores y encuentra
     * el que coincida con el número proporcionado.
     * 
     * @param numero el número identificador del corredor a buscar
     * @return el corredor encontrado o null si no existe ninguno con ese número
     */
    public Corredor buscarCorredor(int numero) {
        Iterator iterator = this.corredores.iterator();
        while(iterator.hasNext()) {
            Corredor corredorPivot = (Corredor) iterator.next();
            if(corredorPivot.getNumCorredor() == numero) {
                return corredorPivot;
            }
        }
        return null;
    }
    
    /**
     * Obtiene el nombre del corredor que ha ganado la carrera.
     * Delega la consulta al método estático de la clase Corredor.
     * 
     * @return el nombre del ganador de la carrera
     */
    public String quienEsElGanador() {
        return Corredor.getGanadorDeLaCarrera();
    }
    
    /**
     * Verifica si es posible agregar un nuevo corredor a la carrera.
     * La carrera tiene un límite máximo de 5 corredores.
     * 
     * @return true si se puede agregar un corredor más, false en caso contrario
     */
    public boolean puedeAgregarCorredor() {
        return this.corredores.size() < 5; // Máximo 5 corredores
    }
    
    /**
     * Obtiene la cantidad actual de corredores en la carrera.
     * 
     * @return el número de corredores registrados
     */
    public int getCantidadCorredores() {
        return this.corredores.size();
    }
    
    /**
     * Constructor que inicializa el controlador de corredores.
     * Crea la lista de corredores y establece la referencia al controlador principal.
     * Automáticamente crea el primer corredor con el nombre "Usain Bolt".
     * 
     * @param cPrincipal el controlador principal de la aplicación
     */
    public ControlCorredor(ControlPrincipal cPrincipal) {
        this.corredores = new ArrayList<>();
        this.cPrincipal = cPrincipal;
        
        // Crear el primer corredor al inicializar
        this.crearCorredor("Usain Bolt", getYPosition(1));
    }
    
    /**
     * Calcula la posición vertical en la pantalla para un corredor específico.
     * Cada corredor tiene una posición Y predefinida basada en su número de carril.
     * 
     * @param numCorredor el número del corredor (1-5)
     * @return la coordenada Y donde debe posicionarse el corredor
     */
    private int getYPosition(int numCorredor) {
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
    
    public Corredor getCorredor() {
        return corredor;
    }
    
    public ArrayList<Corredor> getCorredores() {
        return corredores;
    }
    
    public void setCorredores(ArrayList<Corredor> corredores) {
        this.corredores = corredores;
    }
    
    /**
     * Método del patrón Observer que se ejecuta cuando un corredor llega a la meta.
     * Notifica al controlador principal para que anuncie el ganador de la carrera.
     * 
     * @param nombre el nombre del corredor que llegó a la meta
     */
    @Override
    public void corredorLlego(String nombre) {
        this.cPrincipal.anunciarGanador();
    }
}