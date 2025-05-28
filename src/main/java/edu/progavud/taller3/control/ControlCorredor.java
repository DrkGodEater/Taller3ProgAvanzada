/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3.control;

import edu.progavud.taller3.modelo.CarreraObserver;
import edu.progavud.taller3.modelo.Corredor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author carlosmamut1
 */
public class ControlCorredor implements CarreraObserver {
    private ControlPrincipal cPrincipal;
    private ArrayList<Corredor> corredores;
    private Corredor corredor;
    
    public Corredor crearCorredor(String nombre, int posicionY) {
        corredor = new Corredor(nombre,posicionY,this);
        this.corredores.add(corredor);
        return corredor;
    }
    
    public Corredor buscarCorredor(String nombre) {
        Iterator iterator = this.corredores.iterator();
        while(iterator.hasNext()) {
            Corredor corredorPivot = (Corredor) iterator.next();
            if(corredorPivot.getNombre().equals(nombre)) {
                return corredorPivot;
            }
        }
        return null;
    }
    
    public String quienEsElGanador() {
        return Corredor.getGanadorDeLaCarrera();
    }

    public ControlCorredor(ControlPrincipal cPrincipal) {
        this.corredores = new ArrayList<>();
        this.cPrincipal = cPrincipal;
        
    }

    public Corredor getCorredor() {
        return corredor;
    }

    @Override
    public void corredorLlego(String nombre) {
        this.cPrincipal.anunciarGanador();
    }
    
    
    
   


    
    
}
