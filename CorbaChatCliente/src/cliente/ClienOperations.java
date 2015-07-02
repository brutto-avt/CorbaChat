package cliente;

public interface ClienOperations {
    void ReceberMensagemServidor(String apelidoOrigem, String mensagem);
    void ReceberNovaConexao(String apelido, String nome);
    void DesconexaoCliente(String apelido, String nome);
}
