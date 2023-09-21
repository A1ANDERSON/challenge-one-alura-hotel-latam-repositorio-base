package Controladores;

import DAO.ReservaDAO;
import Factory.ConexionBase;
import modelo.Reserva;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class ReservaControlador {

    private ReservaDAO reservaD;

    public ReservaControlador() {
        Connection con = new ConexionBase().conectarBase();
        this.reservaD = new ReservaDAO(con);
    }

    public void guardar(Reserva reserva){
        this.reservaD.guardar(reserva);
    }

    public List<Reserva> mostar(){
        return this.reservaD.mostrar();
    }

    public List<Reserva> buscarId(String id){
        return this.reservaD.buscarId(id);
    }

    public void actualizarReserva(Integer id, LocalDate dataE, LocalDate dataS, String valor, String formaPago){
        this.reservaD.Actualizar(dataE, dataS, valor,formaPago, id);
    }

    public void Eliminar(Integer id){
        this.reservaD.Eliminar(id);
    }
}
