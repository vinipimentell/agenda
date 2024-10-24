package model;

import java.time.LocalDate;

public class Viagem {
    private int idviagem;
    private String destino;
    private LocalDate dataPartida;
    private LocalDate dataRetorno;
    private int capacidade;

    // Construtor padrão
    public Viagem() {
    }

    // Construtor
    public Viagem(int idviagem, String destino, LocalDate dataPartida, LocalDate dataRetorno, int capacidade) {
        this.idviagem = idviagem;
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.dataRetorno = dataRetorno;
        this.capacidade = capacidade;
    }

    // Getters
    public int getIdviagem() {
        return idviagem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public int getCapacidade() {
        return capacidade;
    }

    // Setters
    public void setIdviagem(int idviagem) {
        this.idviagem = idviagem;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public void setDataRetorno(LocalDate dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}