package gui;

import Servidor.Serv;
import Servidor.ServHelper;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
import servidor.ServServant;

public class Servidor extends javax.swing.JFrame {
    static ORB orb;
    static POA rootPOA;
    static ServServant servant;
    static Serv href;
    boolean online = false;
    private List<String> fila;
    private List<Contato> contatos;
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
            while (online) {
                Iterator<String> iterator = fila.iterator();
                while (iterator.hasNext()) {
                    String s = iterator.next();
                    edLog.append(s+"\n");
                    iterator.remove();
                }
                //
                if (!contatosAtualizados) {
                    DefaultListModel lm = new DefaultListModel();
                    for (Contato c : contatos) {
                        lm.addElement(c.getApelido() + " (" + c.getNome() + ")");
                    }
                    listaClientes.setModel(lm);
                    contatosAtualizados = true;
                }
            }
            return null;
        }
    };
    
    public Servidor() {
        initComponents();
        this.contatos = new ArrayList<>();
        fila = new ArrayList<>();
        edNome.setText(getIpLocal());
    }
    
    private String getIpLocal() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            return local.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        edLog = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Corba");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnParar.setText("Parar");
        btnParar.setEnabled(false);
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));

        listaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaClientes.setEnabled(false);
        jScrollPane2.setViewportView(listaClientes);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));

        edLog.setEditable(false);
        edLog.setColumns(20);
        edLog.setRows(5);
        edLog.setFocusable(false);
        jScrollPane1.setViewportView(edLog);

        jLabel1.setText("Nome:");

        edNome.setText("localhost");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addComponent(btnIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnParar)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnIniciar)
                        .addComponent(btnParar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(364, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addComponent(jScrollPane2))
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // Disponibilizar serviços servidor
        try {
            String[] arg = new String[4];
            arg[0] = "-ORBInitialHost";
            arg[1] = "localhost";
            arg[2] = "-ORBInitialPort";
            arg[3] = "900";
            orb = ORB.init(arg, System.getProperties());
            rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); 
            //
            servant = new ServServant(this);
            servant.setOrb(orb);
            //
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(servant);
            href = ServHelper.narrow(ref);
            //
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            //
            NameComponent path[] = ncRef.to_name(edNome.getText());
            ncRef.rebind(path, href);
            //
            rootPOA.the_POAManager().activate();        
            edLog.append("Servidor pronto e aguardando chamadas remotas...\n");
            online = true;
            orbWorker.execute();
            worker.execute();
            btnParar.setEnabled(true);
            btnIniciar.setEnabled(false);
        } catch (InvalidName ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServantNotActive ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AdapterInactive ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        orb.shutdown(true);
        online = false;
        btnIniciar.setEnabled(true);
        btnParar.setEnabled(false);
    }//GEN-LAST:event_btnPararActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (btnParar.isEnabled()) {
            btnPararActionPerformed(null);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec("tnameserv  -ORBInitialPort 900");
                } catch (IOException ex) {}
                new Servidor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnParar;
    private javax.swing.JTextArea edLog;
    private javax.swing.JTextField edNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaClientes;
    // End of variables declaration//GEN-END:variables

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public boolean isContatosAtualizados() {
        return contatosAtualizados;
    }

    public void setContatosAtualizados(boolean contatosAtualizados) {
        this.contatosAtualizados = contatosAtualizados;
    }

    public List<String> getFila() {
        return fila;
    }

    public void setFila(List<String> fila) {
        this.fila = fila;
    }
}
