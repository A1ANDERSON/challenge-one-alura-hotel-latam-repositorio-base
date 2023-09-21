package Controladores;

import DAO.HuespedesDAO;
import Factory.ConexionBase;
import modelo.Huespedes;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class HuespedesControlador {

    private HuespedesDAO huespedesDAO;

    public HuespedesControlador(){
        Connection con = new ConexionBase().conectarBase();
        this.huespedesDAO = new HuespedesDAO(con);
    }

    public void guardar(Huespedes huespedes){
        this.huespedesDAO.guardar(huespedes);
    }

    public List<Huespedes> mostarHuesped(){
        return this.huespedesDAO.mostrar();
    }

    public List<Huespedes> buscarHuesped(String id){
        return this.huespedesDAO.buscarId(id);
    }

    public void actualizarH(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva,
                            Integer id){
        this.huespedesDAO.ActualizarH(nombre, apellido, (java.sql.Date) fechaNacimiento, nacionalidad, telefono, idReserva, id);
    }

    public void Eliminar(Integer idReserva){
        this.huespedesDAO.Eliminar(idReserva);
    }
}
