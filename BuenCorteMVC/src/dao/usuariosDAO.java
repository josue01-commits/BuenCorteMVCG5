/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.usuarios;
import modelo.conexion;

/**
 *
 * @author HP
 */
public class usuariosDAO {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public usuarios validarLogin(String nombreUsuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usu=? AND password_usu=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuarios u = new usuarios();
                u.setId_usu(rs.getInt("id_usu"));
                u.setNombre_usu(rs.getString("nombre_usu"));
                u.setPassword_usu(rs.getString("password_usu"));
                u.setRol_usu(rs.getString("rol_usu"));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("error " + e.toString());
            return null;
        }
    }
}
