/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * 
 */
public class pagos {
    private int id_pago;
    private double monto_total;
    private String metodo_pago;
    private int fk_id_cita;
    public pagos(){
    }

    public int getId_pago() {
        return id_pago;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public int getFk_id_cita() {
        return fk_id_cita;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public void setFk_id_cita(int fk_id_cita) {
        this.fk_id_cita = fk_id_cita;
    }
}
