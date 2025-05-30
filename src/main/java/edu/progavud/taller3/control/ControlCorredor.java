package edu.progavud.taller3.control;


import edu.progavud.taller3.control.ControlPrincipal;
import edu.progavud.taller3.modelo.CarreraObserver;
import edu.progavud.taller3.modelo.Corredor;
import java.util.ArrayList;
import java.util.Iterator;



public class ControlCorredor implements CarreraObserver {
    private ControlPrincipal cPrincipal;
    private ArrayList<Corredor> corredores;
    private Corredor corredor;
    
    public Corredor crearCorredor(String nombre, int posicionY) {
        corredor = new Corredor(nombre, posicionY, corredores.size());
        this.corredores.add(corredor);
        return corredor;
    }
    
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
    
    public String quienEsElGanador() {
        return Corredor.getGanadorDeLaCarrera();
    }
    
    public boolean puedeAgregarCorredor() {
        return this.corredores.size() < 5; // Máximo 5 corredores
    }
    
    public int getCantidadCorredores() {
        return this.corredores.size();
    }
    
    public ControlCorredor(ControlPrincipal cPrincipal) {
        this.corredores = new ArrayList<>();
        this.cPrincipal = cPrincipal;
        
        // Crear el primer corredor al inicializar
        this.crearCorredor("Usain Bolt", getYPosition(1));
    }
    
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
    
    @Override
    public void corredorLlego(String nombre) {
        this.cPrincipal.anunciarGanador();
    }
}
