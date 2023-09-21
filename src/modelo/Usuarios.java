package modelo;

import Factory.ConexionBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuarios {

    private String nombre;
    private String contrasenia;

    public Usuarios(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public static boolean validarUsuario(String nombre, String contrasenia){
        ConexionBase con = new ConexionBase();
        Connection connec = null;
        PreparedStatement state = null;
        ResultSet result = null;

        try{
            connec = con.conectarBase();
            state = connec.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?");
            state.setString(1, nombre);
            state.setString(2, contrasenia);
            result = state.executeQuery();
            return result.next();

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (result != null){
                    result.close();
                }else if (state != null){
                    state.close();
                }else if (connec != null){
                    connec.close();
                }
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
    }
}
