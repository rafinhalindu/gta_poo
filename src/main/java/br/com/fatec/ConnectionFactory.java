package br.com.fatec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL =
        "jdbc:mysql://localhost:3306/db_gta";

    private static final String USUARIO = "root";
    private static final String SENHA = "admin";

    public static Connection obterConexao() {

        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        Connection conexao = obterConexao();

        System.out.println(conexao);
    }
}