/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import dao.citasDAO;
import modelo.citas;
import modelo.clientes;
import modelo.empleados;
import buencortemvc.dashboard_citas;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class citaController {
    private dashboard_citas vista;
    private citasDAO dao;
    private List<clientes> listaCliente;
    private List<empleados> listaEmpleado;
    private List<citas> listaCitas; 
    private int idCitaSeleccionada = -1;

    public citaController(dashboard_citas vista) {
        this.vista = vista;
        dao = new citasDAO();
    }

    public void cargarClientes(JComboBox cbxClientes) {
        cbxClientes.removeAllItems();
        listaCliente = dao.listarClientesActivos();
        for (clientes c : listaCliente) {
            cbxClientes.addItem(c);
        }
    }

    public void cargarEmpleados(JComboBox cbxEmpleados) {
        cbxEmpleados.removeAllItems();
        listaEmpleado = dao.listarEmpleados();
        for (empleados e : listaEmpleado) {
            cbxEmpleados.addItem(e);
        }
    }

    public void insertarCita() {
        try {
            citas ci = new citas();
            ci.setFecha(LocalDate.parse(vista.txtFechaCi.getText()));
            ci.setHora(LocalTime.parse(vista.txtHoraCi.getText()));
            ci.setEstado(vista.cbxEstadoCi.getSelectedItem().toString());

            clientes cli = (clientes) vista.cbxClientesCi.getSelectedItem();
            empleados emp = (empleados) vista.cbxEmpleadosCi.getSelectedItem();
            if (cli == null || emp == null) {
                JOptionPane.showMessageDialog(null, "Selecciona cliente y empleado");
                return;
            }
            ci.setId_cli(cli.getId_cli());
            ci.setId_emple(emp.getId());

            if (dao.insertar(ci)) {
                JOptionPane.showMessageDialog(null, "Cita registrada");
                listarCitas();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la cita");
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido. Fecha: YYYY-MM-DD, Hora: HH:mm");
        }
    }

    public void actualizarCita() {
        if (idCitaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una cita de la tabla primero");
            return;
        }
        try {
            citas ci = new citas();
            ci.setId_cita(idCitaSeleccionada);
            ci.setFecha(LocalDate.parse(vista.txtFechaCi.getText()));
            ci.setHora(LocalTime.parse(vista.txtHoraCi.getText()));
            ci.setEstado(vista.cbxEstadoCi.getSelectedItem().toString());

            clientes cli = (clientes) vista.cbxClientesCi.getSelectedItem();
            empleados emp = (empleados) vista.cbxEmpleadosCi.getSelectedItem();
            if (cli == null || emp == null) {
                JOptionPane.showMessageDialog(null, "Selecciona cliente y empleado");
                return;
            }
            ci.setId_cli(cli.getId_cli());
            ci.setId_emple(emp.getId());

            if (dao.actualizar(ci)) {
                JOptionPane.showMessageDialog(null, "Cita actualizada");
                listarCitas();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la cita");
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido. Fecha: YYYY-MM-DD, Hora: HH:mm");
        }
    }

    public void eliminarCita() {
        if (idCitaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una cita de la tabla primero");
            return;
        }
        if (dao.eliminar(idCitaSeleccionada)) {
            JOptionPane.showMessageDialog(null, "Cita eliminada");
            listarCitas();
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar la cita");
        }
    }

    public void listarCitas() {
        listaCitas = dao.listarCitas(); // <-- guarda la lista completa
        DefaultTableModel modelo = (DefaultTableModel) vista.tblCitas.getModel();
        modelo.setRowCount(0);
        for (citas ci : listaCitas) {
            String nombreCliente = buscarNombreCliente(ci.getId_cli());
            String nombreEmpleado = buscarNombreEmpleado(ci.getId_emple());
            Object[] fila = {ci.getId_cita(), ci.getFecha(), ci.getHora(), ci.getEstado(), nombreCliente, nombreEmpleado};
            modelo.addRow(fila);
        }
    }

    // Se llama cuando el usuario hace clic en una fila de la tabla
    public void cargarCitaEnFormulario(int filaSeleccionada) {
        if (listaCitas == null || filaSeleccionada < 0 || filaSeleccionada >= listaCitas.size()) {
            return;
        }
        citas ci = listaCitas.get(filaSeleccionada);
        idCitaSeleccionada = ci.getId_cita();

        vista.txtFechaCi.setText(ci.getFecha().toString());
        vista.txtHoraCi.setText(ci.getHora().toString());
        vista.cbxEstadoCi.setSelectedItem(ci.getEstado());

        // Selecciona el cliente correspondiente en el combo
        for (clientes c : listaCliente) {
            if (c.getId_cli() == ci.getId_cli()) {
                vista.cbxClientesCi.setSelectedItem(c);
                break;
            }
        }
        // Selecciona el empleado correspondiente en el combo
        for (empleados e : listaEmpleado) {
            if (e.getId() == ci.getId_emple()) {
                vista.cbxEmpleadosCi.setSelectedItem(e);
                break;
            }
        }
    }

    private void limpiarFormulario() {
        idCitaSeleccionada = -1;
        vista.txtFechaCi.setText("");
        vista.txtHoraCi.setText("");
    }

    private String buscarNombreCliente(int idCli) {
        if (listaCliente == null) return "";
        for (clientes c : listaCliente) {
            if (c.getId_cli() == idCli) return c.getNombre_cli();
        }
        return "";
    }

    private String buscarNombreEmpleado(int idEmple) {
        if (listaEmpleado == null) return "";
        for (empleados e : listaEmpleado) {
            if (e.getId() == idEmple) return e.getNombre();
        }
        return "";
    }
}
