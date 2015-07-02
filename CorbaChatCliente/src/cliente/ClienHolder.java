package cliente;

public final class ClienHolder implements org.omg.CORBA.portable.Streamable {
    public cliente.Clien value = null;

    public ClienHolder() {
    }

    public ClienHolder(cliente.Clien initialValue) {
        value = initialValue;
    }

    public void _read(org.omg.CORBA.portable.InputStream i) {
        value = cliente.ClienHelper.read(i);
    }

    public void _write(org.omg.CORBA.portable.OutputStream o) {
        cliente.ClienHelper.write(o, value);
    }

    public org.omg.CORBA.TypeCode _type() {
        return cliente.ClienHelper.type();
    }
}
