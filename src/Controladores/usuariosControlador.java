package Controladores;

import modelo.Usuarios;
import views.Login;
import views.MenuUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class usuariosControlador implements ActionListener {
    private Login vista;

    public usuariosControlador(Login vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String nombre = vista.getNombre();
        String contrasenia = vista.getContrasenia();

        if(Usuarios.validarUsuario(nombre,contrasenia)){
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            vista.dispose();
        } else{
            JOptionPane.showMessageDialog(vista,"Usuario o Contraseña no validos");
        }
    }

}
