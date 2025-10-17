/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Usuario;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author dante
 */



public class ControllerLogin implements ActionListener {
    private JTextField txtEmail;
    private JPasswordField txtSenha;

    public ControllerLogin(JTextField txtEmail, JPasswordField txtSenha) {
        this.txtEmail = txtEmail;
        this.txtSenha = txtSenha;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        if (Usuario.autenticar(email, senha)) {
            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
        } else {
            JOptionPane.showMessageDialog(null, "Credenciais inválidas.");
        }
    }
}
