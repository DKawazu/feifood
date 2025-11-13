/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;







import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dante
 */
public class Conexao {

    public static Connection conectar() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/feifood"; 
        String usuario = "postgres"; 
        String senha = "fei";

        return DriverManager.getConnection(url, usuario, senha);
    }
}

