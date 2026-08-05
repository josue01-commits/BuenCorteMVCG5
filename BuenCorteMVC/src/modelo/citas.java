/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class citas {
    private int id_cita;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private int id_cli;
    private int id_emple;
    
    
 public citas(){
 
 }
    public int getId_cita(){
            return id_cita;
        }
        public void setId_cita(int id_cita){
            this.id_cita = id_cita;
        }
    public LocalDate getFecha(){
            return fecha;
        }
        public void setFecha(LocalDate fecha){
            this.fecha = fecha;
        }
    public LocalTime getHora(){
            return hora;
        }
        public void setHora(LocalTime hora){
            this.hora = hora;
        }
    public String getEstado(){
            return estado;
        }
        public void setEstado(String estado){
            this.estado = estado;
        }
    public int getId_cli(){
            return id_cli;
        }
        public void setId_cli(int id_cli){
            this.id_cli = id_cli;
        }
    public int getId_emple(){
            return id_emple;
        }
        public void setId_emple(int id_emple){
            this.id_emple = id_emple;
        }
    
}
