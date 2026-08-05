/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import modelo.conexion;
import modelo.citas;


public class citasDAO {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
     public Boolean insertar(citas c){
        String sql ="INSERT INTO citas ( fecha, hora, estado, fk_id_cli, fk_id_emple) VALUES(?,?,?,?,?)";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setDate(1, java.sql.Date.valueOf(c.getFecha()));
            ps.setTime(2, java.sql.Time.valueOf(c.getHora()));
            ps.setString(3, c.getEstado());
            ps.setInt(4, c.getId_cli());
            ps.setInt(5, c.getId_emple());
          
            
            ps.executeUpdate();
            return true;
           
        }catch(Exception e){
            System.out.println("error"+ e.toString());
            return false;
        }
    }
     public List<citas> listarCitas(){
        List<citas> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                citas c = new citas();
                c.setId_cita(rs.getInt("id_cita"));
             if (rs.getDate("fecha") != null) {
                c.setFecha(rs.getDate("fecha").toLocalDate());
    }
             if (rs.getTime("hora") != null) {
             c.setHora(rs.getTime("hora").toLocalTime());
        }
                c.setEstado(rs.getString("estado"));
                c.setId_cli(rs.getInt("fk_id_cli"));
                c.setId_emple(rs.getInt("fk_id_emple"));
                
                
                
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("error"+ e.toString());
           
        }
        return lista;
    }
    public boolean eliminarCitas(int id_cita){
        String sql = "delete from citas where id_cita =?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_cita);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error"+ e.toString());
            return false;
        }
    }
    public boolean actualizarCitas(citas c){
        String sql = "UPDATE citas set fecha=?, hora=?, estado=? ,fk_id_cli=?,fk_id_emple=? where id_cita=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setDate(1, java.sql.Date.valueOf(c.getFecha()));
            ps.setTime(2, java.sql.Time.valueOf(c.getHora()));
            ps.setString(3, c.getEstado());
            ps.setInt(4, c.getId_cli());
            ps.setInt(5, c.getId_emple());
            ps.setInt(6, c.getId_cita());
            
            ps.executeUpdate();
            return true;
            
        }catch(Exception e){
            System.out.println("error"+ e.toString());
            return false;
        }
    }
}
