package Servidor;

public interface ServOperations {
    int ReceberMensagemCliente(String apelidoOrigem, String apelidoDestino, String mensagem);
    int Conectar(String apelido, String nome);
    void Desconectar(String apelido);
}
