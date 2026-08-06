/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.pagos;
import modelo.citas;
import buencortemvc.dashboard_pagos;
import dao.pagosDAO;
import dao.citasDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */
public class pagosController {
    private dashboard_pagos vista;
    private pagosDAO dao;
    private citasDAO daoCitas;
    private List<citas> listaCitas;
    public pagosController(dashboard_pagos vista){
        this.vista=vista;
        dao=new pagosDAO();
        daoCitas=new citasDAO();
    }
    public void cargarCitas(){
        vista.cbxCita.removeAllItems();
        listaCitas=daoCitas.listarCitas();
        for(citas ci:listaCitas){
            vista.cbxCita.addItem(ci);
        }
    }
    public void insertarPago(){
        pagos p=new pagos();
        try{
            p.setMonto_total(Double.parseDouble(vista.txtMontoTotal.getText()));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "el monto debe ser numerico");
            return;
        }
        citas ci=(citas)vista.cbxCita.getSelectedItem();
        if(ci==null){
            JOptionPane.showMessageDialog(null, "selecciona una cita");
            return;
        }
        p.setMetodo_pago(vista.cbxMetodoPago.getSelectedItem().toString());
        p.setFk_id_cita(ci.getId_cita());
        if(dao.insertarPago(p)){
            JOptionPane.showMessageDialog(null, "pago registrado con exito");
            listarPago();
        }else{
            JOptionPane.showMessageDialog(null, "error en el registro");
        }
    }
    public void listarPago(){
        DefaultTableModel modelo=(DefaultTableModel)vista.tblPagos.getModel();
        modelo.setRowCount(0);
        List<pagos>lista=dao.listarPago();
        for(pagos p:lista){
            modelo.addRow(new Object[]{
                p.getId_pago(),
                p.getMonto_total(),
                p.getMetodo_pago(),
                p.getFk_id_cita()
            });
        }
    }
    public void eliminarPago(){
        int fila=vista.tblPagos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "selecciona un pago");
            return;
        }
        int id_pago=(int)vista.tblPagos.getValueAt(fila, 0);
        if(dao.eliminarPago(id_pago)){
            JOptionPane.showMessageDialog(null, "pago eliminado con exito");
            listarPago();
        }else{
            JOptionPane.showMessageDialog(null, "error al eliminar");
        }
    }
    public void editarPago(){
        int fila=vista.tblPagos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "debe seleccionar un pago");
            return;
        }
        pagos p=new pagos();
        p.setId_pago(Integer.parseInt(vista.tblPagos.getValueAt(fila, 0).toString()));
        try{
            p.setMonto_total(Double.parseDouble(vista.txtMontoTotal.getText()));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "el monto debe ser numerico");
            return;
        }
        citas ci=(citas)vista.cbxCita.getSelectedItem();
        if(ci==null){
            JOptionPane.showMessageDialog(null, "selecciona una cita");
            return;
        }
        p.setMetodo_pago(vista.cbxMetodoPago.getSelectedItem().toString());
        p.setFk_id_cita(ci.getId_cita());
        if(dao.actualizarPago(p)){
            JOptionPane.showMessageDialog(null, "pago actualizado con exito");
            listarPago();
        }else{
            JOptionPane.showMessageDialog(null, "error al actualizar");
        }
    }
}