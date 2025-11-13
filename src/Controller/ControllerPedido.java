/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Alimento;
import Model.Conexao;
import Model.ItemPedido;
import Model.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ControllerPedido {

    private static List<ItemPedido> carrinho = new ArrayList<>();

    public static void adicionarAoCarrinho(Alimento alimento) {
        // Verifica se o item já existe no carrinho
        for (ItemPedido item : carrinho) {
            if (item.getAlimento().getId() == alimento.getId()) {
                item.setQuantidade(item.getQuantidade() + 1);
                return;
            }
        }
        // Se não existe, adiciona novo item
        carrinho.add(new ItemPedido(alimento, 1));
    }

    public static void removerDoCarrinho(Alimento alimento) {
        carrinho.removeIf(item -> item.getAlimento().getId() == alimento.getId());
    }

    public static List<ItemPedido> getCarrinho() {
        return carrinho;
    }

    public static void limparCarrinho() {
        carrinho.clear();
    }

    public static boolean finalizarPedido(int usuarioId) {
        if (carrinho.isEmpty()) return false;
        boolean sucesso = Pedido.criarPedido(usuarioId, carrinho);
        if (sucesso) limparCarrinho();
        return sucesso;
    }
    
    public static void avaliarPedido(int pedidoId, int nota) {
    try (Connection conn = Conexao.conectar()) {
        String sql = "UPDATE pedido SET avaliacao = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, nota);
        stmt.setInt(2, pedidoId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static List<Pedido> listarPedidos() {
    return Pedido.listarTodos();
}

}

