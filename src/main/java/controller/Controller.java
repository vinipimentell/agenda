package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;
import model.Viagem;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/insertViagem", "/listarViagens" })
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();
    Viagem viagem = new Viagem();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/main")) {
            contatos(request, response);
        } else if (action.equals("/insert")) {
            novoContato(request, response);
        } else if (action.equals("/listarViagens")) {
            listarViagens(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/insertViagem")) {
            novoViagem(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    // Listar contatos
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<JavaBeans> lista = dao.listarContatos();
        request.setAttribute("contatos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    // Novo contato
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.inserirContato(contato);
        response.sendRedirect("main");
    }

    // Listar Viagens
    protected void listarViagens(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Viagem> listaViagens = dao.listarViagens();
        request.setAttribute("viagens", listaViagens);
        RequestDispatcher rd = request.getRequestDispatcher("viagens.jsp");
        rd.forward(request, response);
    }

 // Novo Viagem
    protected void novoViagem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Viagem viagem = new Viagem(); // Certifique-se de criar uma nova instância de Viagem
        // Criação do objeto Viagem com os dados do formulário
        viagem.setDestino(request.getParameter("destino"));
        viagem.setDataPartida(LocalDate.parse(request.getParameter("data_partida")));
        viagem.setDataRetorno(LocalDate.parse(request.getParameter("data_retorno")));
        viagem.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));

        // Invocar o método inserirViagem passando o objeto viagem e capturar o retorno
        boolean sucesso = dao.inserirViagem(viagem);

        // Verifica se a inserção foi bem-sucedida
        if (sucesso) {
            System.out.println("Viagem inserida com sucesso: " + viagem);
        } else {
            System.out.println("Falha ao inserir a viagem: " + viagem);
            // Opcional: redirecionar para uma página de erro ou mostrar mensagem ao usuário
            request.setAttribute("mensagemErro", "Falha ao inserir a viagem. Por favor, tente novamente.");
            request.getRequestDispatcher("viagens.jsp").forward(request, response); // Redireciona para o formulário
            return; // Para garantir que o redirecionamento não aconteça duas vezes
        }

        // Redirecionar para a lista de viagens
        response.sendRedirect("listarViagens");
    }

}
