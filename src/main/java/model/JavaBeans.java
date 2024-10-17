package model;

public class JavaBeans {
    private String idcon;          // ID da viagem
    private String destino;        // Destino da viagem
    private String data_partida;   // Data de partida
    private String data_retorno;   // Data de retorno
    private String nome;           // Nome do passageiro
    private String fone;           // Telefone do passageiro
    private String email;          // E-mail do passageiro
    private int capacidade;         // Capacidade da viagem

    // Construtores
    public JavaBeans() {
        super();
    }

    public JavaBeans(String idcon, String destino, String data_partida, String data_retorno, String nome, String fone, String email, int capacidade) {
        super();
        this.idcon = idcon;
        this.destino = destino;
        this.data_partida = data_partida;
        this.data_retorno = data_retorno;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        this.capacidade = capacidade;
    }

    // Getters e Setters
    public String getIdcon() {
        return idcon;
    }

    public void setIdcon(String idcon) {
        this.idcon = idcon;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData_partida() {
        return data_partida;
    }

    public void setData_partida(String data_partida) {
        this.data_partida = data_partida;
    }

    public String getData_retorno() {
        return data_retorno;
    }

    public void setData_retorno(String data_retorno) {
        this.data_retorno = data_retorno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
