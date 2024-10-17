package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans viagem = new JavaBeans(); // Alterado para refletir o uso da classe com os novos atributos

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			listarViagens(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response, viagem);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar viagens
	protected void listarViagens(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarContatos();
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Novo contato (viagem)
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		viagem.setNome(request.getParameter("nome"));
		viagem.setFone(request.getParameter("fone"));
		viagem.setEmail(request.getParameter("email"));
		viagem.setDestino(request.getParameter("destino"));
		viagem.setData_partida(request.getParameter("data_partida"));
		viagem.setData_retorno(request.getParameter("data_retorno"));
		viagem.setCapacidade(Integer.parseInt(request.getParameter("capacidade"))); // A capacidade deve ser convertida
																					// para int

		dao.inserirContato(viagem); // Certifique-se de que o método no DAO aceita o novo objeto
		response.sendRedirect("main");
	}

	// Remover um contato

	protected void removerContato(HttpServletRequest request, HttpServletResponse response, JavaBeans contato)
			throws ServletException, IOException {
		// Recebimento do id do contato a ser excluido
		String idcon = request.getParameter("idcon");
		// setar a variável idcon JavaBeans
		contato.setIdcon(idcon);
		// executar o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
}
