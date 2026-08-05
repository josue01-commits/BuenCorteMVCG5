/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import dao.usuariosDAO;
import modelo.usuarios;
import buencortemvc.login;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class loginController {
    private login vista;
    private usuariosDAO dao;

    public loginController(login vista) {
        this.vista = vista;
        dao = new usuariosDAO();
    }

    public void validarLogin() {
        String user = vista.txtUsuario.getText();
        String pass = new String(vista.txtPassword.getPassword());
        usuarios u = dao.validarLogin(user, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + u.getNombre_usu());
            vista.dispose();
            new buencortemvc.menuOpciones().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }
}

