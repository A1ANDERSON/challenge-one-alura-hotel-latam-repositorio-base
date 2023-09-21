package DAO;

import modelo.Huespedes;


import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDAO {

    private Connection con;

    public HuespedesDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huespedes huespedes){
        try {
            String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try (pstm){
                pstm.setString(1, huespedes.getNombre());
                pstm.setString(2, huespedes.getApellido());
                pstm.setObject(3, huespedes.getFechaNacimiento());
                pstm.setString(4, huespedes.getNacionalidad());
                pstm.setString(5, huespedes.getTelefono());
                pstm.setInt(6, huespedes.getIdReserva());
                pstm.execute();

                try(ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()){
                        huespedes.setId(rst.getInt(1));
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("animal" + e.getMessage(),e);
        }
    }

    public List<Huespedes> mostrar(){
        List<Huespedes> huespedes = new ArrayList<Huespedes>();
        try {
            String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
            PreparedStatement pstm = con.prepareStatement(sql);
            try (pstm){
                pstm.execute();

                trasformarResultado(huespedes,pstm);
            }
            return huespedes;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public List<Huespedes> buscarId(String id){
        List<Huespedes> huespedes = new ArrayList<Huespedes>();
        try {
            String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            try (pstm){
                pstm.setString(1,id);
                pstm.execute();

                trasformarResultado(huespedes,pstm);
            }
            return huespedes;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void  ActualizarH(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id){
        try(PreparedStatement stm = con.prepareStatement("UPDATE huespedes SET nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?," +
                "telefono=?, id_reserva=? WHERE id=?")){
        stm.setString(1,nombre);
        stm.setString(2, apellido);
        stm.setDate(3, fechaNacimiento);
        stm.setString(4, nacionalidad);
        stm.setString(5, telefono);
        stm.setInt(6, idReserva);
        stm.setInt(7, id);
        stm.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void Eliminar(Integer id){
        try(PreparedStatement stm = con.prepareStatement("DELETE FROM huespedes WHERE id=?")) {
            stm.setInt(1,id);
            stm.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void trasformarResultado( List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException{
        ResultSet rst = pstm.executeQuery();
        try(rst) {
            while (rst.next()){
                int id = rst.getInt("id");
                String nombre = rst.getString("nombre");
                String apellido = rst.getString("apellido");
                LocalDate fechaNacimiento = rst.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
                String nacionalidad = rst.getString("nacionalidad");
                String telefono = rst.getString("telefono");
                int idReserva = rst.getInt("id_reserva");

                Huespedes huesped = new Huespedes(id,nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva);
                huespedes.add(huesped);
            }
        }
    }
}
