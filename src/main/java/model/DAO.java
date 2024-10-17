package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
    /** Módulo de conexão **/
    // Parâmetros de conexão
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "admin121";

    // Método de conexão
    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Método para inserir uma nova viagem
    public void inserirContato(JavaBeans viagem) {
        String create = "INSERT INTO contatos (nome, fone, email, destino, data_partida, data_retorno, capacidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            // Abrir conexão
            Connection con = conectar();
            // Preparar a query para execução no banco de dados
            PreparedStatement pst = con.prepareStatement(create);
            // Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
            pst.setString(1, viagem.getNome());
            pst.setString(2, viagem.getFone());
            pst.setString(3, viagem.getEmail());
            pst.setString(4, viagem.getDestino());
            pst.setString(5, viagem.getData_partida());
            pst.setString(6, viagem.getData_retorno());
            pst.setInt(7, viagem.getCapacidade());
            // Executar a query
            pst.executeUpdate();
            // Encerrar a conexão com o banco
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** CRUD READ **/
    public ArrayList<JavaBeans> listarContatos() {
        // Criando um objeto para acessar a classe JavaBeans
        ArrayList<JavaBeans> contatos = new ArrayList<>();
        String read = "SELECT * FROM contatos ORDER BY nome"; // Certifique-se de que a consulta está correta
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            // o laço abaixo será executado enquanto houver contatos
            while (rs.next()) {
                // variáveis de apoio que recebem os dados do banco
                String idcon = rs.getString("idcon"); // Supondo que o nome da coluna no banco é idcon
                String destino = rs.getString("destino"); // Coluna para destino
                String data_partida = rs.getString("data_partida"); // Coluna para data de partida
                String data_retorno = rs.getString("data_retorno"); // Coluna para data de retorno
                String nome = rs.getString("nome"); // Coluna para nome
                String fone = rs.getString("fone"); // Coluna para telefone
                String email = rs.getString("email"); // Coluna para e-mail
                int capacidade = rs.getInt("capacidade"); // Coluna para capacidade

                // populando o ArrayList
                contatos.add(new JavaBeans(idcon, destino, data_partida, data_retorno, nome, fone, email, capacidade));
            }
            con.close();
            return contatos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* CRUD DELETE */
    public void deletarContato(JavaBeans contato) {
        String delete = "DELETE FROM contatos WHERE idcon=?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setString(1, contato.getIdcon());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

