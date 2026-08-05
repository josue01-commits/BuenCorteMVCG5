/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;
import modelo.servicio;
import buencortemvc.dashboard_servicio;
import dao.servicioDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class servicioController {
    private dashboard_servicio vista;
    private servicioDAO dao;

    public servicioController(dashboard_servicio vista){
        this.vista = vista;
        dao = new servicioDAO();
    }

    public void insertarServicio(){
        servicio s = new servicio();
        s.setNombre_servi(vista.txtNombreServi.getText());
        if(dao.insertarServicio(s)){
            JOptionPane.showMessageDialog(null, "servicio registrado con exito");
            listarServicio();
        }else{
            JOptionPane.showMessageDialog(null, "error en el registro");
        }
    }

    public void listarServicio(){
        List<servicio> listServ = dao.listarServicio();
        DefaultTableModel modelo = (DefaultTableModel) vista.tblServicio.getModel();
        modelo.setRowCount(0);
        for(servicio s : listServ){
            Object[] fila = {s.getId_servi(), s.getNombre_servi()};
            modelo.addRow(fila);
        }
    }

    public void eliminarServicio(){
        int fila = vista.tblServicio.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "seleccione un servicio");
            return;
        }
        int id_servi = (int) vista.tblServicio.getValueAt(fila, 0);
        if(dao.eliminarServicio(id_servi)){
            JOptionPane.showMessageDialog(null, "servicio eliminado con exito");
            listarServicio();
        }else{
            JOptionPane.showMessageDialog(null, "error al eliminar");
        }
    }

    public void editarServicio(){
        int fila = vista.tblServicio.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "debe seleccionar un servicio");
            return;
        }
        servicio s = new servicio();
        s.setId_servi(Integer.parseInt(vista.tblServicio.getValueAt(fila, 0).toString()));
        s.setNombre_servi(vista.txtNombreServi.getText());
        if(dao.actualizarServicio(s)){
            JOptionPane.showMessageDialog(null, "servicio actualizado con exito");
            listarServicio();
        }else{
            JOptionPane.showMessageDialog(null, "error al actualizar");
        }
    }
}