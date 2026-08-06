/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import dao.detalleCitaDAO;
import java.util.List;
import modelo.detalleCita;

/**
 *
 * @author Usuario
 */
public class detalleCitaController {
        private final detalleCitaDAO dao = new detalleCitaDAO();

    public boolean guardar(detalleCita detalle) {
        if (detalle.getIdDetalle() == 0) {
            return dao.insertar(detalle);
        } else {
            return dao.actualizar(detalle);
        }
    }

    public boolean eliminar(int idDetalle) {
        return dao.eliminar(idDetalle);
    }

    public detalleCita buscarPorId(int idDetalle) {
        return dao.buscarPorId(idDetalle);
    }

    public List<detalleCita> listarTodos() {
        return dao.listarTodos();
    }

    public List<detalleCita> listarPorCita(int idCita) {
        return dao.listarPorCita(idCita);
    }
}
