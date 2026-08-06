/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.conexion;
import modelo.detalleCita;

/**
 *
 * @author Usuario
 */
public class detalleCitaDAO {
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public Boolean insertar(detalleCita detalle){
        String sql = "INSERT INTO detalle_citas(fk_id_cita, fk_id_servicio, precio_cobrado) VALUES(?, ?, ?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, detalle.getIdCita());
            ps.setInt(2, detalle.getIdServicio());
            ps.setDouble(3, detalle.getPrecioCobrado());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public Boolean actualizar(detalleCita detalle){
        String sql = "UPDATE detalle_citas SET fk_id_cita=?, fk_id_servicio=?, precio_cobrado=? WHERE id_detalle=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, detalle.getIdCita());
            ps.setInt(2, detalle.getIdServicio());
            ps.setDouble(3, detalle.getPrecioCobrado());
            ps.setInt(4, detalle.getIdDetalle());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public Boolean eliminar(int idDetalle){
        String sql = "DELETE FROM detalle_citas WHERE id_detalle=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public detalleCita buscarPorId(int idDetalle){
        detalleCita d = null;
        String sql = "SELECT * FROM detalle_citas WHERE id_detalle=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            rs = ps.executeQuery();
            if(rs.next()){
                d = new detalleCita();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdCita(rs.getInt("fk_id_cita"));
                d.setIdServicio(rs.getInt("fk_id_servicio"));
                d.setPrecioCobrado(rs.getDouble("precio_cobrado"));
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return d;
    }
    public List<detalleCita> listarTodos(){
        List<detalleCita> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_citas";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                detalleCita d = new detalleCita();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdCita(rs.getInt("fk_id_cita"));
                d.setIdServicio(rs.getInt("fk_id_servicio"));
                d.setPrecioCobrado(rs.getDouble("precio_cobrado"));
                lista.add(d);
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return lista;
    }
    public List<detalleCita> listarPorCita(int idCita){
        List<detalleCita> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_citas WHERE fk_id_cita=?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCita);
            rs = ps.executeQuery();
            while(rs.next()){
                detalleCita d = new detalleCita();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdCita(rs.getInt("fk_id_cita"));
                d.setIdServicio(rs.getInt("fk_id_servicio"));
                d.setPrecioCobrado(rs.getDouble("precio_cobrado"));
                lista.add(d);
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return lista;
    }
}
