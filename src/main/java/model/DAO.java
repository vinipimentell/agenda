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
	
	public boolean inserirViagem(Viagem viagem) {
	    String create = "INSERT INTO viagens(destino, data_partida, data_retorno, capacidade) VALUES (?, ?, ?, ?)";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(create);
	        // Substituir os parâmetros (?) pelo conteúdo do objeto Viagem
	        pst.setString(1, viagem.getDestino());
	        pst.setDate(2, java.sql.Date.valueOf(viagem.getDataPartida()));
	        pst.setDate(3, java.sql.Date.valueOf(viagem.getDataRetorno()));
	        pst.setInt(4, viagem.getCapacidade());

	        int rowsAffected = pst.executeUpdate(); // Executa a inserção e retorna o número de linhas afetadas
	        con.close();
	        
	        // Verifica se a inserção foi bem-sucedida
	        if (rowsAffected > 0) {
	            System.out.println("Viagem inserida com sucesso: " + viagem);
	            return true; // Inserção bem-sucedida
	        } else {
	            System.out.println("Falha ao inserir a viagem: Nenhuma linha afetada.");
	            return false; // Inserção falhou
	        }
	    } catch (Exception e) {
	        System.out.println("Erro ao inserir viagem: " + e.getMessage());
	        return false; // Retorna falso em caso de exceção
	    }
	}

	    public ArrayList<Viagem> listarViagens() {
	        ArrayList<Viagem> viagens = new ArrayList<>();
	        String read = "SELECT * FROM viagens ORDER BY destino";
	        try {
	            Connection con = conectar();
	            PreparedStatement pst = con.prepareStatement(read);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                Viagem viagem = new Viagem();
	                viagem.setIdviagem(rs.getInt("idviagem"));
	                viagem.setDestino(rs.getString("destino"));
	                viagem.setDataPartida(rs.getDate("data_partida").toLocalDate());
	                viagem.setDataRetorno(rs.getDate("data_retorno").toLocalDate());
	                viagem.setCapacidade(rs.getInt("capacidade"));
	                viagens.add(viagem);
	            }
	            con.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return viagens;
	    }

	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome,fone,email) values (?,?,?)";
		try {
			// Abrir conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das váriaveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
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
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Teste de conexão public void testeConexao() { try { Connection con =
	 * conectar(); System.out.println(con); con.close(); } catch(Exception e) {
	 * System.out.println(e); } }
	 **/
}
