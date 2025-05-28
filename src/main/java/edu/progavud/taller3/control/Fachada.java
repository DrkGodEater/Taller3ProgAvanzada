/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.taller3.control;

import edu.progavud.taller3.vista.VentanaPrincipal;

/**
 *
 * @author a
 */
public class Fachada {
    private ControlPrincipal cPrincipal;
    private VentanaPrincipal vPrincipal;

    public Fachada(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.vPrincipal = new VentanaPrincipal(this);
    }

    public VentanaPrincipal getvPrincipal() {
        return vPrincipal;
    }

    public void setvPrincipal(VentanaPrincipal vPrincipal) {
        this.vPrincipal = vPrincipal;
    }
    
    
    
    
    
}
