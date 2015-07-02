package servidor;

import Cliente.Clien;
import Cliente.ClienHelper;
import Servidor.ServPOA;
import gui.Contato;
import gui.Servidor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class ServServant extends ServPOA {
    private ORB orb;
    private Servidor gui;
    private SimpleDateFormat df;
    
    public ServServant(Servidor gui) {
        super();
        this.gui = gui;
        df = new SimpleDateFormat("HH:mm:ss");
    }
    
    public Contato getContato(String apelido) {
        for (Contato c: gui.getContatos()) {
            if (c.getApelido().equals(apelido)) {
                return c;
            }
        }
        return null;
    }
    
    @Override
    public int ReceberMensagemCliente(String apelidoOrigem, String apelidoDestino, String mensagem) {
        if (!apelidoDestino.equals("TODOS")) {
            Contato destino = getContato(apelidoDestino);
            destino.getCliente().ReceberMensagemServidor(apelidoOrigem, mensagem);
        } else {
            for (Contato c: gui.getContatos()) {
                c.getCliente().ReceberMensagemServidor(apelidoOrigem, mensagem);
            }
        }
        gui.getFila().add("<"+df.format(new Date()) + "> DE " + apelidoOrigem + " PARA " + apelidoDestino + ": " + mensagem);
        return 0;
    }

    @Override
    public int Conectar(String apelido, String nome) {
        Clien cliente = null;
        if (apelido.equals("TODOS")) {
            return 2;
        }
        for (Contato c: gui.getContatos()) {
            if (c.getApelido().equals(apelido)) {
                return 3;
            }
        }
        
        try {
            String[] arg = new String[4];
            arg[0] = "-ORBInitialHost";
            arg[1] = "localhost";
            arg[2] = "-ORBInitialPort";
            arg[3] = "900";
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            cliente = ClienHelper.narrow(ncRef.resolve_str(apelido));
        } catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(ServServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        Contato c = new Contato(nome, apelido, cliente);
        for (Contato con : gui.getContatos()) {
            c.getCliente().ReceberNovaConexao(con.getApelido(), con.getNome());
        }
        gui.getContatos().add(c);
        gui.getFila().add("<" + df.format(new Date()) + "> CLIENTE " + apelido + "(" + nome + ") CONECTADO");
        for (Contato con : gui.getContatos()) {
            con.getCliente().ReceberNovaConexao(apelido, nome);
        }
        gui.setContatosAtualizados(false);
        return 0;
    }

    @Override
    public void Desconectar(String apelido) {
        Iterator<Contato> iterator = gui.getContatos().iterator();
        while (iterator.hasNext()) {
            Contato c = iterator.next();
            if (c.getApelido().equals(apelido)) {
                for (Contato con: gui.getContatos()) {
                    con.getCliente().DesconexaoCliente(apelido, c.getNome());
                }
                iterator.remove();
                gui.getFila().add("<"+df.format(new Date()) + "> CLIENTE " + c.getApelido() + "(" + c.getNome() + ") DESCONECTADO");
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
    
}
