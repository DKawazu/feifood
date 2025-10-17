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




public class ControllerCadastro implements ActionListener {
    private JTextField txtNome, txtEmail;
    private JPasswordField txtSenha, txtConfirmarSenha;

    public ControllerCadastro(JTextField txtNome, JTextField txtEmail, JPasswordField txtSenha, JPasswordField txtConfirmarSenha) {
        this.txtNome = txtNome;
        this.txtEmail = txtEmail;
        this.txtSenha = txtSenha;
        this.txtConfirmarSenha = txtConfirmarSenha;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        String confirmarSenha = new String(txtConfirmarSenha.getPassword());

        if (senha.equals(confirmarSenha)) {
            if (Usuario.cadastrar(nome, email, senha)) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "As senhas não coincidem.");
        }
    }
}
