/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author HP
 */
public class usuarios {
    private int id_usu;
    private String nombre_usu;
    private String password_usu;
    private String rol_usu;
    public usuarios(){   
    }

    public int getId_usu() {
        return id_usu;
    }

    public String getNombre_usu() {
        return nombre_usu;
    }

    public String getPassword_usu() {
        return password_usu;
    }

    public String getRol_usu() {
        return rol_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public void setNombre_usu(String nombre_usu) {
        this.nombre_usu = nombre_usu;
    }

    public void setPassword_usu(String password_usu) {
        this.password_usu = password_usu;
    }

    public void setRol_usu(String rol_usu) {
        this.rol_usu = rol_usu;
    }
    
}
