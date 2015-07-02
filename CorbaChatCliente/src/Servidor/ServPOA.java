package Servidor;

public abstract class ServPOA extends org.omg.PortableServer.Servant implements Servidor.ServOperations, org.omg.CORBA.portable.InvokeHandler {
    private static java.util.Hashtable _methods = new java.util.Hashtable();

    static {
        _methods.put("ReceberMensagemCliente", new java.lang.Integer(0));
        _methods.put("Conectar", new java.lang.Integer(1));
        _methods.put("Desconectar", new java.lang.Integer(2));
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
                String apelidoDestino = in.read_string();
                String mensagem = in.read_string();
                int $result = (int) 0;
                $result = this.ReceberMensagemCliente(apelidoOrigem, apelidoDestino, mensagem);
                out = $rh.createReply();
                out.write_long($result);
                break;
            }

            case 1:
            {
                String apelido = in.read_string();
                String nome = in.read_string();
                int $result = (int) 0;
                $result = this.Conectar(apelido, nome);
                out = $rh.createReply();
                out.write_long($result);
                break;
            }

            case 2:
            {
                String apelido = in.read_string();
                this.Desconectar(apelido);
                out = $rh.createReply();
                break;
            }

            default:
                throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        return out;
    }

    private static String[] __ids = {
        "IDL:Servidor/Serv:1.0"};

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] objectId) {
        return (String[]) __ids.clone();
    }

    public Serv _this() {
        return ServHelper.narrow(
                super._this_object());
    }

    public Serv _this(org.omg.CORBA.ORB orb) {
        return ServHelper.narrow(
                super._this_object(orb));
    }
}
