package gui;

import Servidor.Serv;
import Servidor.ServHelper;
import cliente.Clien;
import cliente.ClienHelper;
import cliente.ClienServant;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Cliente extends javax.swing.JFrame {
    private Serv servidor;
    static ORB orb;
    static POA rootPOA;
    static ClienServant servant;
    static Clien href;
    private String contatoSelecionado = null;
    boolean online = false;
    private List<Contato> contatos;
    private List<String> fila;
    private boolean contatosAtualizados = false;
    private SwingWorker orbWorker = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            orb.run();
            return null;
        }
    };
    private SwingWorker worker = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            while(online) {
                try {
                    Iterator<String> iterator = fila.iterator();
                    while (iterator.hasNext()) {
                        String s = iterator.next();
                        edChat.append(s + "\n");
                        iterator.remove();
                    }
                    //
                    if (!contatosAtualizados) {
                        DefaultListModel lm = new DefaultListModel();
                        for (Contato c : contatos) {
                            lm.addElement(c.getApelido() + " (" + c.getNome() + ")");
                        }
                        lm.addElement("TODOS (Enviar a todos)");
                        jlContatos.setModel(lm);
                        contatosAtualizados = true;
                    }
                } catch (Exception e) {}
            }
            return null;
        }
    };
    
    public Cliente() {
        initComponents();
        this.fila = new ArrayList<>();
        this.contatos = new ArrayList<>();
        edServidorIp.setText(getIpLocal());
    }
    
    private String getIpLocal() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            return local.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edMensagem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlContatos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        edChat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edApelido = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        edServidorIp = new javax.swing.JTextField();
        btnConectar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Corba");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        edMensagem.setEnabled(false);
        edMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edMensagemActionPerformed(evt);
            }
        });

        jlContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlContatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlContatos);

        edChat.setEditable(false);
        edChat.setColumns(20);
        edChat.setRows(5);
        jScrollPane2.setViewportView(edChat);

        jLabel1.setText("Nome:");

        edNome.setText("Usuário 1");

        jLabel2.setText("Apelido:");

        edApelido.setText("user1");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conectar"));

        jLabel4.setText("Servidor:");

        edServidorIp.setText("localhost");

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.setEnabled(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edServidorIp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnConectar)
                .addComponent(btnSair))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(edServidorIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edMensagem)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edNome, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        // Disponibilizar serviços cliente
        try {
            String[] arg = new String[4];
            arg[0] = "-ORBInitialHost";
            arg[1] = "localhost";
            arg[2] = "-ORBInitialPort";
            arg[3] = "900";
            orb = ORB.init(arg, System.getProperties());
            rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); 
            //
            servant = new ClienServant(servidor, this);
            servant.setOrb(orb);
            //
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(servant);
            href = ClienHelper.narrow(ref);
            //
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            //
            NameComponent path[] = ncRef.to_name(edApelido.getText());
            ncRef.rebind(path, href);
            //
            rootPOA.the_POAManager().activate();        
            online = true;
        } catch (InvalidName ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServantNotActive ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AdapterInactive ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Conectar ao servidor
        try {
            String[] arg = new String[4];
            arg[0] = "-ORBInitialHost";
            arg[1] = "localhost";
            arg[2] = "-ORBInitialPort";
            arg[3] = "900";
            ORB orbServ = ORB.init(arg, System.getProperties());
            org.omg.CORBA.Object objRef = orbServ.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            servidor = ServHelper.narrow(ncRef.resolve_str(edServidorIp.getText()));
            servant.setServidor(servidor);
            servidor.Conectar(edApelido.getText(), edNome.getText());            
            //
            btnConectar.setEnabled(false);
            btnSair.setEnabled(true);
            edApelido.setEnabled(false);
            edMensagem.setEnabled(true);
            edNome.setEnabled(false);
        } catch (InvalidName ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        orbWorker.execute();
        worker.execute();
    }//GEN-LAST:event_btnConectarActionPerformed

    private void jlContatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlContatosMouseClicked
        if (evt.getClickCount() == 2 && btnSair.isEnabled()) {
            contatoSelecionado = jlContatos.getSelectedValue().toString();
            contatoSelecionado = contatoSelecionado.substring(0, contatoSelecionado.indexOf(" ("));
            edMensagem.setEnabled(true);
            edMensagem.requestFocus();
        }
    }//GEN-LAST:event_jlContatosMouseClicked

    private void edMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edMensagemActionPerformed
        String apelido = edApelido.getText();
        String mensagem = edMensagem.getText();
        try {
            servant.enviar(apelido, contatoSelecionado, mensagem);
            edMensagem.setText("");
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar a mensagem");
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_edMensagemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (btnSair.isEnabled()) {
            try {
                btnSairActionPerformed(null);
            } catch(Exception e) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        servidor.Desconectar(edApelido.getText());
        orb.shutdown(true);
        edMensagem.setEnabled(false);
        online = false;
        btnConectar.setEnabled(true);
        btnSair.setEnabled(false);
    }//GEN-LAST:event_btnSairActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec("tnameserv  -ORBInitialPort 900");
                } catch (IOException ex) {}
                new Cliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnSair;
    private javax.swing.JTextField edApelido;
    private javax.swing.JTextArea edChat;
    private javax.swing.JTextField edMensagem;
    private javax.swing.JTextField edNome;
    private javax.swing.JTextField edServidorIp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList jlContatos;
    // End of variables declaration//GEN-END:variables

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<String> getFila() {
        return fila;
    }

    public void setFila(List<String> fila) {
        this.fila = fila;
    }

    public boolean isContatosAtualizados() {
        return contatosAtualizados;
    }

    public void setContatosAtualizados(boolean contatosAtualizados) {
        this.contatosAtualizados = contatosAtualizados;
    }
}
