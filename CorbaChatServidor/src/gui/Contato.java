package gui;

import Cliente.Clien;

public class Contato {
    private String nome;
    private String apelido;
    private Clien cliente;
    
    public Contato(String nome, String apelido, Clien cliente) {
        this.nome = nome;
        this.apelido = apelido;
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Clien getCliente() {
        return cliente;
    }

    public void setCliente(Clien cliente) {
        this.cliente = cliente;
    }
}
