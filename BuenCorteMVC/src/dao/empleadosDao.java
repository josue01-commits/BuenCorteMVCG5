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

import modelo.conexion;
import modelo.empleados;

public class empleadosDao {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // INSERTAR
    public Boolean insertarEmpleado(empleados e) {

        String sql = "INSERT INTO empleados(nombre, apellido, cargo) VALUES(?, ?, ?)";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getCargo());

            ps.executeUpdate();

            return true;

        } catch (Exception ex) {

            System.out.println("Error " + ex.toString());
            return false;

        }

    }

    // LISTAR
    public List<empleados> listarEmpleado() {

        List<empleados> listaEmp = new ArrayList<>();

        String sql = "SELECT * FROM empleados";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                empleados e = new empleados();

                e.setId(rs.getInt("id_emple"));
                e.setNombre(rs.getString("nombre_emple"));
                e.setApellido(rs.getString("apellido_emple"));
                e.setCargo(rs.getString("cargo_emple"));

                listaEmp.add(e);

            }

        } catch (Exception ex) {

            System.out.println("Error " + ex.toString());

        }

        return listaEmp;

    }

    // ELIMINAR
    public Boolean eliminarEmpleado(int id) {

        String sql = "DELETE FROM empleados WHERE id_empleado=?";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (Exception ex) {

            System.out.println("Error " + ex.toString());
            return false;

        }

    }

    // ACTUALIZAR
    public Boolean actualizarEmpleado(empleados e) {

        String sql = "UPDATE empleados SET nombre=?, apellido=?, cargo=? WHERE id_empleado=?";

        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getCargo());
            ps.setInt(4, e.getId());

            ps.executeUpdate();

            return true;

        } catch (Exception ex) {

            System.out.println("Error " + ex.toString());
            return false;

        }

    }

}