/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.List;
/**
 *
 * @author dante
 */


public class Pedido {
    private int id;
    private int usuarioId;
    private Timestamp data;
    private List<ItemPedido> itens;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public Timestamp getData() { return data; }
    public void setData(Timestamp data) { this.data = data; }
    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public static boolean criarPedido(int usuarioId, int alimentoId, int quantidade) {
    try (Connection conn = Conexao.conectar()) {
        String sql = "INSERT INTO pedido (usuario_id, alimento_id, quantidade) VALUES (?, ?, ?) RETURNING id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, usuarioId);  
        stmt.setInt(2, alimentoId); 
        stmt.setInt(3, quantidade);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return false;
}
}

