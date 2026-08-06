/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.empleadosDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.empleados;
import buencortemvc.dashboard_empleados;

public class empleadosController {

    private dashboard_empleados vista;
    private empleadosDao dao;

    public empleadosController(dashboard_empleados vista) {

        this.vista = vista;
        dao = new empleadosDao();

    }

    // INSERTAR
    public void insertarempleados() {

        empleados e = new empleados();

        e.setNombre(vista.txtNombreEmple.getText());
        e.setApellido(vista.txtApellidoEmple.getText());
        e.setCargo(vista.txtCargoEmple.getText());

        if (dao.insertarEmpleado(e)) {

            JOptionPane.showMessageDialog(null, "Empleado registrado.");

            listarEmpleados();

            limpiar();

        } else {

            JOptionPane.showMessageDialog(null, "Error al registrar.");

        }

    }

    // LISTAR
//    
    public void listarEmpleados(){
        List<empleados>listEmple=dao.listarEmpleado();
        DefaultTableModel modelo=(DefaultTableModel)vista.tblEmpleados.getModel();
        modelo.setRowCount(0);
        for(empleados e:listEmple){
            Object[]fila={e.getId(), e.getNombre(), e.getApellido(), e.getCargo()};
            modelo.addRow(fila);
        }
    }

    // ELIMINAR EMPLEADO
    public void eliminarEmpleados() {

        int fila = vista.tblEmpleados.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(null, "Seleccione un empleado.");
            return;

        }

        int id = Integer.parseInt(vista.tblEmpleados.getValueAt(fila, 0).toString());

        if (dao.eliminarEmpleado(id)) {

            JOptionPane.showMessageDialog(null, "Empleado eliminado.");

            listarEmpleados();

            limpiar();

        } else {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar.");

        }

    }

    // EDITAR
    public void editarEmpleados() {

       int fila = vista.tblEmpleados.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(null, "Seleccione un empleado.");
            return;

        }

        empleados e = new empleados();

        e.setId(Integer.parseInt(vista.tblEmpleados.getValueAt(fila, 0).toString()));
        e.setNombre(vista.txtNombreEmple.getText());
        e.setApellido(vista.txtApellidoEmple.getText());
        e.setCargo(vista.txtCargoEmple.getText());

        if (dao.actualizarEmpleado(e)) {

            JOptionPane.showMessageDialog(null, "Empleado actualizado.");

            listarEmpleados();

            limpiar();

        } else {

            JOptionPane.showMessageDialog(null, "No se pudo actualizar.");

        }

    }

    // LIMPIAR
    public void limpiar() {

        vista.txtNombreEmp.setText("");
        vista.txtApellidoEmp.setText("");
        vista.txtCargoEmp.setText("");

    }

}