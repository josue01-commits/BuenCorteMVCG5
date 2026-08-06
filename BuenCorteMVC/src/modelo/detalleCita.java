/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class detalleCita {
    private int idDetalle;
    private int idCita;
    private int idServicio;
    private double precioCobrado;
    
    public detalleCita(){
        
    }
    
    public detalleCita(int idDetalle,
                        int idCita,
                        int idServicio,
                        double precioCobrado){

    }
    public int getIdDetalle() {
        return idDetalle;
    }
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }
    public int getIdCita() {
        return idCita;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
    public int getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
    public double getPrecioCobrado() {
        return precioCobrado;
    }
    public void setPrecioCobrado(double precioCobrado) {
        this.precioCobrado = precioCobrado;
    }
    
}
