package br.com.fatec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PartidaDAO {
    public boolean cadastrar(Partida p) {

        String sql = "INSERT INTO tb_partida (jogador, pontuacao) VALUES (?, ?)";

        try (Connection conexao = ConnectionFactory.obterConexao()) {

            if (conexao == null) {
                return false;
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setString(1, p.getJogador());
                stmt.setInt(2, p.getPontuacao());

                stmt.executeUpdate();

                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Partida> listar() {

        List<Partida> partidas = new ArrayList<>();

        String sql = "SELECT * FROM tb_partida ORDER BY codigo";

        try (Connection conexao = ConnectionFactory.obterConexao()) {

            if (conexao == null) {
                return partidas;
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Partida p = Partida.builder()
                        .codigo(rs.getInt("codigo"))
                        .jogador(rs.getString("jogador"))
                        .pontuacao(rs.getInt("pontuacao"))
                        .build();

                    partidas.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            partidas.clear();
        }

        return partidas;
    }
}