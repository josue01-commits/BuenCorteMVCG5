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

import modelo.pagos;
import modelo.conexion;
/**
 *
 * @author HP
 */
public class pagosDAO {
    conexion cn=new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public Boolean insertarPago(pagos p){
        String sql="INSERT INTO pagos(monto_total, metodo_pago, fk_id_cita)VALUES(?, ?, ?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setDouble(1, p.getMonto_total());
            ps.setString(2, p.getMetodo_pago());
            ps.setInt(3, p.getFk_id_cita());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public List<pagos>listarPago(){
        List<pagos>lista=new ArrayList<>();
        String sql="SELECT * FROM pagos";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pagos p=new pagos();
                p.setId_pago(rs.getInt("id_pago"));
                p.setMonto_total(rs.getDouble("monto_total"));
                p.setMetodo_pago(rs.getString("metodo_pago"));
                p.setFk_id_cita(rs.getInt("fk_id_cita"));
                lista.add(p);
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return lista;
    }
    public Boolean eliminarPago(int id_pago){
        String sql="DELETE FROM pagos WHERE id_pago=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id_pago);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public Boolean actualizarPago(pagos p){
        String sql="UPDATE pagos SET monto_total=?, metodo_pago=?, fk_id_cita=? WHERE id_pago=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setDouble(1, p.getMonto_total());
            ps.setString(2, p.getMetodo_pago());
            ps.setInt(3, p.getFk_id_cita());
            ps.setInt(4, p.getId_pago());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public List<Integer>listarIdCitas(){
        List<Integer>listCitas=new ArrayList<>();
        String sql="SELECT id_cita FROM citas";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                listCitas.add(rs.getInt("id_cita"));
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return listCitas;
    }
}