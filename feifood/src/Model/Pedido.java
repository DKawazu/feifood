package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private int usuarioId;
    private Timestamp data;
    private int avaliacao;
    private List<ItemPedido> itens;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public Timestamp getData() { return data; }
    public void setData(Timestamp data) { this.data = data; }
    public int getAvaliacao() { return avaliacao; }
    public void setAvaliacao(int avaliacao) { this.avaliacao = avaliacao; }
    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    // 🔹 Cria um novo pedido com os itens no banco
    public static boolean criarPedido(int usuarioId, List<ItemPedido> itens) {
    try (Connection conn = Conexao.conectar()) {
        // Cria o pedido e obtém o ID
        String sqlPedido = "INSERT INTO pedido (usuario_id, data) VALUES (?, CURRENT_TIMESTAMP) RETURNING id";
        PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido);
        stmtPedido.setInt(1, usuarioId);
        ResultSet rs = stmtPedido.executeQuery();

        if (rs.next()) {
            int pedidoId = rs.getInt("id");

            // Adiciona os itens do pedido
            String sqlItem = "INSERT INTO item_pedido (pedido_id, alimento_id, quantidade) VALUES (?, ?, ?)";
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);

            for (ItemPedido item : itens) {
                stmtItem.setInt(1, pedidoId);
                stmtItem.setInt(2, item.getAlimentoId());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.executeUpdate();
            }

            return true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // Retorna todos os pedidos do banco com seus itens e avaliação
public static List<Pedido> listarTodos() {
    List<Pedido> pedidos = new ArrayList<>();

    String sql = "SELECT id, usuario_id, data, COALESCE(avaliacao, 0) AS avaliacao " +
                 "FROM pedido ORDER BY data DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Pedido p = new Pedido();
            p.setId(rs.getInt("id"));
            p.setUsuarioId(rs.getInt("usuario_id"));
            p.setData(rs.getTimestamp("data"));
            p.setAvaliacao(rs.getInt("avaliacao"));

            // AGORA pode chamar porque o helper também é estático
            p.setItens(buscarItensDoPedido(conn, p.getId()));

            pedidos.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return pedidos;
}

// Busca os itens de um pedido (JOIN com alimento)
private static List<ItemPedido> buscarItensDoPedido(Connection conn, int pedidoId) throws SQLException {
    List<ItemPedido> itens = new ArrayList<>();

    String sql = """
        SELECT i.pedido_id,
               a.id   AS alimento_id,
               a.nome AS nome,
               a.preco AS preco,
               i.quantidade
          FROM item_pedido i
          JOIN alimento a ON i.alimento_id = a.id
         WHERE i.pedido_id = ?
    """;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, pedidoId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("alimento_id"));
                alimento.setNome(rs.getString("nome"));
                alimento.setPreco(rs.getDouble("preco"));

                ItemPedido item = new ItemPedido();
                item.setPedidoId(rs.getInt("pedido_id"));
                item.setAlimento(alimento);           // evita NullPointer
                item.setQuantidade(rs.getInt("quantidade"));

                itens.add(item);
            }
        }
    }
    return itens;


    }

    // 🔹 Retorna apenas os nomes dos alimentos (pra exibir na tabela)
    public List<String> getItensNomes() {
        List<String> nomes = new ArrayList<>();
        if (itens != null) {
            for (ItemPedido i : itens) {
                nomes.add(i.getAlimento().getNome());
            }
        }
        return nomes;
    }
}
