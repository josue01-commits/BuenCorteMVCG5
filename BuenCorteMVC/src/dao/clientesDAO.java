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

import modelo.clientes;
import modelo.conexion;
/**
 *
 * @author HP
 */
public class clientesDAO {
    conexion cn=new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public Boolean insertarCliente(clientes c){
        String sql="INSERT INTO clientes(nombre_cli, apellido_cli, telefono_cli)VALUES(?, ?, ?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, c.getNombre_cli());
            ps.setString(2, c.getApellido_cli());
            ps.setString(3, c.getTelefono_cli());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public List<clientes>listarCliente(){
        List<clientes>listCli=new ArrayList<>();
        String sql="SELECT * FROM clientes";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                clientes c=new clientes();
                c.setId_cli(rs.getInt("id_cli"));
                c.setNombre_cli(rs.getString("nombre_cli"));
                c.setApellido_cli(rs.getString("apellido_cli"));
                c.setTelefono_cli(rs.getString("telefono_cli"));
                listCli.add(c);
            }
        }catch(Exception e){
            System.out.println("error "+e.toString());
        }
        return listCli;
    }
    public Boolean eliminarCliente(int id_cli){
        String sql="DELETE FROM clientes WHERE id_cli=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id_cli);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
    public Boolean actualizarCliente(clientes c){
        String sql="UPDATE clientes SET nombre_cli=?, apellido_cli=?, telefono_cli=? WHERE id_cli=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, c.getNombre_cli());
            ps.setString(2, c.getApellido_cli());
            ps.setString(3, c.getTelefono_cli());
            ps.setInt(4, c.getId_cli());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("error "+e.toString());
            return false;
        }
    }
}
