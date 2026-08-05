/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author HP
 */
public class conexion {
    private static final String URL="jdbc:mysql://localhost:3306/peluqueria_trab";
    private static final String USER="root";
    private static final String PASSWORD="";
    private Connection con;
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("error de conexion " + e.getMessage());
        }
        return con;
    }
}
