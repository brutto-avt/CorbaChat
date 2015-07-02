package cliente;

public class _ClienStub extends org.omg.CORBA.portable.ObjectImpl implements cliente.Clien {

    public void ReceberMensagemServidor(String apelidoOrigem, String mensagem) {
        org.omg.CORBA.portable.InputStream $in = null;
        try {
            org.omg.CORBA.portable.OutputStream $out = _request("ReceberMensagemServidor", true);
            $out.write_string(apelidoOrigem);
            $out.write_string(mensagem);
            $in = _invoke($out);
            return;
        } catch (org.omg.CORBA.portable.ApplicationException $ex) {
            $in = $ex.getInputStream();
            String _id = $ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        } catch (org.omg.CORBA.portable.RemarshalException $rm) {
            ReceberMensagemServidor(apelidoOrigem, mensagem);
        } finally {
            _releaseReply($in);
        }
    }

    public void ReceberNovaConexao(String apelido, String nome) {
        org.omg.CORBA.portable.InputStream $in = null;
        try {
            org.omg.CORBA.portable.OutputStream $out = _request("ReceberNovaConexao", true);
            $out.write_string(apelido);
            $out.write_string(nome);
            $in = _invoke($out);
            return;
        } catch (org.omg.CORBA.portable.ApplicationException $ex) {
            $in = $ex.getInputStream();
            String _id = $ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        } catch (org.omg.CORBA.portable.RemarshalException $rm) {
            ReceberNovaConexao(apelido, nome);
        } finally {
            _releaseReply($in);
        }
    }

    public void DesconexaoCliente(String apelido, String nome) {
        org.omg.CORBA.portable.InputStream $in = null;
        try {
            org.omg.CORBA.portable.OutputStream $out = _request("DesconexaoCliente", true);
            $out.write_string(apelido);
            $out.write_string(nome);
            $in = _invoke($out);
            return;
        } catch (org.omg.CORBA.portable.ApplicationException $ex) {
            $in = $ex.getInputStream();
            String _id = $ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        } catch (org.omg.CORBA.portable.RemarshalException $rm) {
            DesconexaoCliente(apelido, nome);
        } finally {
            _releaseReply($in);
        }
    }

    private static String[] __ids = {
        "IDL:Cliente/Clien:1.0"};

    public String[] _ids() {
        return (String[]) __ids.clone();
    }

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException {
        String str = s.readUTF();
        String[] args = null;
        java.util.Properties props = null;
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);
        try {
            org.omg.CORBA.Object obj = orb.string_to_object(str);
            org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate();
            _set_delegate(delegate);
        } finally {
            orb.destroy();
        }
    }

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        String[] args = null;
        java.util.Properties props = null;
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);
        try {
            String str = orb.object_to_string(this);
            s.writeUTF(str);
        } finally {
            orb.destroy();
        }
    }
}
