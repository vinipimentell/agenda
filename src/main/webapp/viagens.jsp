<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Viagem" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Viagens</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Link para seu CSS -->
</head>
<body>
    <header>
        <h1>Lista de Viagens</h1>
        <a href="index.html">Voltar para o Início</a>
        <a href="novoViagem.html">Adicionar Nova Viagem</a> <!-- Link para formulário de nova viagem -->
    </header>
    
    <main>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Destino</th>
                    <th>Data de Partida</th>
                    <th>Data de Retorno</th>
                    <th>Capacidade</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recuperar a lista de viagens
                    ArrayList<Viagem> listaViagens = (ArrayList<Viagem>) request.getAttribute("viagens");
                    if (listaViagens != null && !listaViagens.isEmpty()) {
                        for (Viagem v : listaViagens) {
                %>
                    <tr>
                        <td><%= v.getIdviagem() %></td>
                        <td><%= v.getDestino() %></td>
                        <td><%= v.getDataPartida() %></td>
                        <td><%= v.getDataRetorno() %></td>
                        <td><%= v.getCapacidade() %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">Nenhuma viagem cadastrada.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </main>
    
    <footer>
        <p>&copy; 2024 Sua Empresa</p>
    </footer>
</body>
</html>
