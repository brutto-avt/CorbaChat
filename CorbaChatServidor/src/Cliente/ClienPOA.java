package Cliente;

public abstract class ClienPOA extends org.omg.PortableServer.Servant implements Cliente.ClienOperations, org.omg.CORBA.portable.InvokeHandler {
    private static java.util.Hashtable _methods = new java.util.Hashtable();

    static {
        _methods.put("ReceberMensagemServidor", new java.lang.Integer(0));
        _methods.put("ReceberNovaConexao", new java.lang.Integer(1));
        _methods.put("DesconexaoCliente", new java.lang.Integer(2));
    }

    public org.omg.CORBA.portable.OutputStream _invoke(String $method,
            org.omg.CORBA.portable.InputStream in,
            org.omg.CORBA.portable.ResponseHandler $rh) {
        org.omg.CORBA.portable.OutputStream out = null;
        java.lang.Integer __method = (java.lang.Integer) _methods.get($method);
        if (__method == null) {
            throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        switch (__method.intValue()) {
            case 0:
            {
                String apelidoOrigem = in.read_string();
                String mensagem = in.read_string();
                this.ReceberMensagemServidor(apelidoOrigem, mensagem);
                out = $rh.createReply();
                break;
            }

            case 1:
            {
                String apelido = in.read_string();
                String nome = in.read_string();
                this.ReceberNovaConexao(apelido, nome);
                out = $rh.createReply();
                break;
            }

            case 2:
            {
                String apelido = in.read_string();
                String nome = in.read_string();
                this.DesconexaoCliente(apelido, nome);
                out = $rh.createReply();
                break;
            }

            default:
                throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        return out;
    }

    private static String[] __ids = {
        "IDL:Cliente/Clien:1.0"};

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] objectId) {
        return (String[]) __ids.clone();
    }

    public Clien _this() {
        return ClienHelper.narrow(
                super._this_object());
    }

    public Clien _this(org.omg.CORBA.ORB orb) {
        return ClienHelper.narrow(
                super._this_object(orb));
    }
}
