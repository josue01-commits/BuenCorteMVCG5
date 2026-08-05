/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.citasDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.citas;
import buencortemvc.dashboard_citas;
import java.time.LocalDate;
import java.time.LocalTime;
import modelo.clientes;
import dao.clientesDAO;

public class citasController {
    private dashboard_citas vista;
    private citasDAO dao;
    public citasController(dashboard_citas vista){
        this.vista = vista;
        dao = new citasDAO();
    }
    public void insertar(){
        citas c = new citas();
       
        c.setFecha(LocalDate.parse(vista.txtFecha.getText()));
        c.setHora(LocalTime.parse(vista.txtHora.getText()));
        c.setEstado(vista.cbxEstado.getSelectedItem().toString());
        if(dao.insertar(c)!= null){
            JOptionPane.showMessageDialog(null, "cita registrada");
            listar();
        }
        else{
            JOptionPane.showMessageDialog(null, "error");
        }
        
    }
    
    public void listar(){

    DefaultTableModel modelo = (DefaultTableModel) vista.tblCitas.getModel();

    modelo.setRowCount(0);

    List<citas> lista = dao.listarCitas();

    for(citas c : lista){

        modelo.addRow(new Object[]{
           c.getId_cita(),
           c.getFecha(),
           c.getHora(),
           c.getEstado(),
           c.getId_cli(),
           c.getId_emple()
           
        });
    }
}
    public void eliminar(){
        int fila = vista.tblCitas.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "debe seleccionar un registro de cita");
            return;
        }
        else{
            int opc = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR ESTE REGISTRO?", 
                    "CONFIRMAR ACCION", JOptionPane.YES_NO_OPTION);
            if(opc != JOptionPane.YES_OPTION){
                return;
            }
            else{
                int id_cita = Integer.parseInt(vista.tblCitas.getValueAt(fila, 0).toString());
                if(dao.eliminarCitas(id_cita)){
                    JOptionPane.showMessageDialog(null, "eliminado con exito");
                    listar();
                    
                }
            }
        }
    }
    public void editar(){
            int fila = vista.tblCitas.getSelectedRow();
            if(fila == -1){
            JOptionPane.showMessageDialog(null, "debe seleccionar un registro");
            return;
        }
            citas c = new citas();
            c.setId_cita(Integer.parseInt(vista.tblCitas.getValueAt(fila, 0).toString()));
            c.setFecha(LocalDate.parse(vista.txtFecha.getText()));
            c.setHora(LocalTime.parse(vista.txtHora.getText()));
            c.setEstado(vista.cbxEstado.getSelectedItem().toString());
            if (dao.actualizarCitas(c)){
                JOptionPane.showMessageDialog(null, "Cita actualizada");
                listar();
            }else{
                JOptionPane.showMessageDialog(null, "error al actualizar");
            }
    }
    public void cargarClientes(){
        clientesDAO c=new clientesDAO();
        vista.cbxClientes.removeAllItems();
        List<clientes>lista=c.listarCliente();
        for(clientes cli:lista){
            vista.cbxClientes.addItem(cli);
        }
    }
}

