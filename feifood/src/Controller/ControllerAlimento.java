package Controller;

import Model.Alimento;
import Model.Bebida;
import Model.Comida;
import Model.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerAlimento {

    // Lista com filtro (usa termo de busca)
    public static List<Alimento> listarTodos(String termo) {
        List<Alimento> lista = new ArrayList<>();
        String sql = """
            SELECT a.id, a.nome, a.preco,
                   CASE WHEN b.id IS NOT NULL THEN 'Bebida' ELSE 'Comida' END AS tipo,
                   b.teor_alcoolico, b.alcoolica
            FROM alimento a
            LEFT JOIN bebida b ON a.id = b.alimento_id
            WHERE a.nome ILIKE ?
            ORDER BY a.id;
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + termo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                if ("Bebida".equals(tipo)) {
                    Bebida b = new Bebida(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getDouble("teor_alcoolico"),
                        rs.getBoolean("alcoolica")
                    );
                    lista.add(b);
                } else {
                    Comida c = new Comida(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                    );
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Lista tudo sem filtro
    public static List<Alimento> listarTodos() {
        return listarTodos("");
    }
    
    public static Alimento buscarPorNome(String nome) {
    for (Alimento a : listarTodos()) {
        if (a.getNome().equalsIgnoreCase(nome)) return a;
    }
    return null;
}

}

