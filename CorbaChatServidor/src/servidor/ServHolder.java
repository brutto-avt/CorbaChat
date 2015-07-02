package Servidor;

public final class ServHolder implements org.omg.CORBA.portable.Streamable {
    public Servidor.Serv value = null;

    public ServHolder() {
    }

    public ServHolder(Servidor.Serv initialValue) {
        value = initialValue;
    }

    public void _read(org.omg.CORBA.portable.InputStream i) {
        value = Servidor.ServHelper.read(i);
    }

    public void _write(org.omg.CORBA.portable.OutputStream o) {
        Servidor.ServHelper.write(o, value);
    }

    public org.omg.CORBA.TypeCode _type() {
        return Servidor.ServHelper.type();
    }
}
