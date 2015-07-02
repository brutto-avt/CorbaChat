package cliente;

import Servidor.Serv;
import gui.Cliente;
import gui.Contato;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.omg.CORBA.ORB;

public class ClienServant extends ClienPOA {
    private ORB orb;
    private Serv servidor;
    private Cliente gui;
    
    public ClienServant(Serv servidor, Cliente gui) {
        super();
        this.gui = gui;
        this.servidor = servidor;
    }
    
    public void enviar(String apelidoOrigem, String apelidoDestino, String mensagem) throws RemoteException {
        getServidor().ReceberMensagemCliente(apelidoOrigem, apelidoDestino, mensagem);
    }
    
    @Override
    public void ReceberMensagemServidor(String apelidoOrigem, String mensagem) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        gui.getFila().add("<"+apelidoOrigem+": "+df.format(new Date()) + "> "+ mensagem);
    }

    @Override
    public void ReceberNovaConexao(String apelido, String nome) {
        Contato c = new Contato(nome, apelido);
        gui.getContatos().add(c);
        gui.setContatosAtualizados(false);
    }

    @Override
    public void DesconexaoCliente(String apelido, String nome) {
        Iterator<Contato> iterator = gui.getContatos().iterator();
        while (iterator.hasNext()) {
            Contato c = iterator.next();
            if (c.getApelido().equals(apelido)) {
                iterator.remove();
            }
        }
        gui.setContatosAtualizados(false);
    }

    public ORB getOrb() {
        return orb;
    }

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    public Serv getServidor() {
        return servidor;
    }

    public void setServidor(Serv servidor) {
        this.servidor = servidor;
    }

}
