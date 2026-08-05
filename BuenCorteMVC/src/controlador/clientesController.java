/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.clientes;
import buencortemvc.dashboard_clientes;
import dao.clientesDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */
public class clientesController {
    private dashboard_clientes vista;
    private clientesDAO dao;
    public clientesController(dashboard_clientes vista){
        this.vista=vista;
        dao=new clientesDAO();
    }
    public void insertarCliente(){
        clientes c=new clientes();
        c.setNombre_cli(vista.txtNombreCli.getText());
        c.setApellido_cli(vista.txtApellidoCli.getText());
        c.setTelefono_cli(vista.txtTelefonoCli.getText());
        if(dao.insertarCliente(c)){
            JOptionPane.showMessageDialog(null, "cliente registrado con exito");
            listarCliente();
        }else{
            JOptionPane.showMessageDialog(null, "error en el registro");
        }
    }
    public void listarCliente(){
        List<clientes>listPelu=dao.listarCliente();
        DefaultTableModel modelo=(DefaultTableModel)vista.tblClientes.getModel();
        modelo.setRowCount(0);
        for(clientes c:listPelu){
            Object[]fila={c.getId_cli(), c.getNombre_cli(), c.getApellido_cli(), c.getTelefono_cli()};
            modelo.addRow(fila);
        }
    }
    public void eliminarCliente(){
        int fila=vista.tblClientes.getSelectedRow();
        if(fila==1){
            JOptionPane.showMessageDialog(null, "seleccionaste un cliente");
            return;
        }
        int id_cli=(int)vista.tblClientes.getValueAt(fila, 0);
        if(dao.eliminarCliente(id_cli)){
            JOptionPane.showMessageDialog(null, "cliente eliminado con exito");
            listarCliente();
        }else{
            JOptionPane.showMessageDialog(null, "error al eliminar");
        }
    }
    public void editarCliente(){
        int fila=vista.tblClientes.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "debe seleccionar un cleinte");
            return;
        }
        clientes c=new clientes();
        c.setId_cli(Integer.parseInt(vista.tblClientes.getValueAt(fila, 0).toString()));
        c.setNombre_cli(vista.txtNombreCli.getText());
        c.setApellido_cli(vista.txtApellidoCli.getText());
        c.setTelefono_cli(vista.txtTelefonoCli.getText());
        if(dao.actualizarCliente(c)){
            JOptionPane.showMessageDialog(null, "cliente actualizado con exito");
            listarCliente();
        }else{
            JOptionPane.showMessageDialog(null, "error al actualizar");
        }
    }

}
