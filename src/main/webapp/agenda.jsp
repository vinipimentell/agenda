<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<% 
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agenda de Viagens</title>
    <link rel="icon" href="imagens/favicon.png">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Agenda de Viagens</h1>
    <a href="novo.html" class="Botao">Nova Viagem</a>
    <a href="index.html" class="Botao">Voltar</a>
    <table id="tabela">
        <thead>
            <tr>
                <th>ID</th>
                <th>Destino</th>
                <th>Data de Partida</th>
                <th>Data de Retorno</th>
                <th>Nome do Passageiro</th>
                <th>Fone</th>
                <th>E-mail</th>
                <th>Capacidade</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < lista.size(); i++) { %>
            <tr>
                <td><%= lista.get(i).getIdcon() %></td>
                <td><%= lista.get(i).getDestino() %></td>
                <td><%= lista.get(i).getData_partida() %></td>
                <td><%= lista.get(i).getData_retorno() %></td>
                <td><%= lista.get(i).getNome() %></td>
                <td><%= lista.get(i).getFone() %></td>
                <td><%= lista.get(i).getEmail() %></td>
                <td><%= lista.get(i).getCapacidade() %></td>
                <td>
                    <a href="javascript: confirmar(<%= lista.get(i).getIdcon() %>)" class="Botao2">Excluir</a>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <script src="scripts/confirmador.js"></script>
</body>
</html>
