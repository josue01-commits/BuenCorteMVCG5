/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author HP
 */
public class clientes {
    private int id_cli;
    private String nombre_cli;
    private String apellido_cli;
    private String telefono_cli;
    public clientes(){
    }

    public int getId_cli() {
        return id_cli;
    }

    public String getNombre_cli() {
        return nombre_cli;
    }

    public String getApellido_cli() {
        return apellido_cli;
    }

    public String getTelefono_cli() {
        return telefono_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        this.nombre_cli = nombre_cli;
    }

    public void setApellido_cli(String apellido_cli) {
        this.apellido_cli = apellido_cli;
    }

    public void setTelefono_cli(String telefono_cli) {
        this.telefono_cli = telefono_cli;
    }
}
