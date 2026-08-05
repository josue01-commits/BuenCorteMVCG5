/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.empleados;
import buencortemvc.dashboard_empleados;
import dao.empleadosDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class empleadosController {
    private dashboard_empleados vista;
    private empleadosDao dao;
    public empleadosController(dashboard_empleados vista){
        this.vista=vista;
        dao=new empleadosDao();
    }
    public void insertarEmpleados(){
        empleados e=new empleados();
        e.setNombre_Emp(vista.txtNombreEmp.getText());
        e.setApellido_Emp(vista.txtApellidoEmp.getText());
        e.setCargo_Emp(vista.txtCargoEmp.getText());
        if(dao.insertarempleados(e)){
            JOptionPane.showMessageDialog(null, "Empleado registrado con éxito");
            listarempleados();
        }else{
            JOptionPane.showMessageDialog(null, "error en el registro");
        }
    }
    public void listarempleados(){
        List<empleados>listPelu=dao.listarempleados();
        DefaultTableModel modelo=(DefaultTableModel)vista.tblEmpleados.getModel();
        modelo.setRowCount(0);
        for(empleados e:listPelu){
            Object[]fila={e.getId_emp(), e.getNombre_emp(), e.getApellido_emp(), e.getcargo_emp()};
            modelo.addRow(fila);
        }
    }
    public void eliminarempleados() {
    int fila = vista.tblEmpleados.getSelectedRow(); 
    
    if (fila == -1) { 
        JOptionPane.showMessageDialog(null, "Seleccione un empleado de la tabla");
        return;
    }
    

    int id_emp = (int) vista.tblEmpleados.getValueAt(fila, 0); 
    
    if (dao.eliminarempleados(id_emp)) {
        JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito");
        listarempleados(); 
    }
}
    public void editarempleados(){
        int fila=vista.tblempleados.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "debe seleccionar un empleado");
            return;
        }
        empleados e=new empleados();
        e.setId_emp(Integer.parseInt(vista.tblEmpleados.getValueAt(fila, 0).toString()));
        e.setNombre_emp(vista.txtNombreEmp.getText());
        e.setApellido_emp(vista.txtApellidoEmp.getText());
        e.setTelefono_emp(vista.txtCargoEmp.getText());
        if(dao.actualizarempleados(e)){
            JOptionPane.showMessageDialog(null, "empleado actializado  con exito");
            listarempleado();
        }else{
            JOptionPane.showMessageDialog(null, "error al actualizar");
        }
    }

}
