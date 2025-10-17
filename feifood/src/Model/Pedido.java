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

    public static boolean criarPedido(int usuarioId, List<ItemPedido> itens) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "INSERT INTO pedido (usuario_id) VALUES (?) RETURNING id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int pedidoId = rs.getInt("id");

                for (ItemPedido item : itens) {
                    sql = "INSERT INTO item_pedido (pedido_id, alimento_id, quantidade) VALUES (?, ?, ?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, pedidoId);
                    stmt.setInt(2, item.getAlimentoId());
                    stmt.setInt(3, item.getQuantidade());
                    stmt.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

