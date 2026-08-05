/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.servicio;
import modelo.conexion;

public class servicioDAO {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Boolean insertarServicio(servicio s){
        String sql = "INSERT INTO servicios(nombre_servi) VALUES(?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getNombre_servi());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }

    public List<servicio> listarServicio(){
        List<servicio> listServ = new ArrayList<>();
        String sql = "SELECT * FROM servicios";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                servicio s = new servicio();
                s.setId_servi(rs.getInt("id_servi"));
                s.setNombre_servi(rs.getString("nombre_servi"));
                listServ.add(s);
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return listServ;
    }

    public Boolean eliminarServicio(int id_servi){
        String sql = "DELETE FROM servicios WHERE id_servi=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_servi);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }

    public Boolean actualizarServicio(servicio s){
        String sql = "UPDATE servicios SET nombre_servi=? WHERE id_servi=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getNombre_servi());
            ps.setInt(2, s.getId_servi());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
}
