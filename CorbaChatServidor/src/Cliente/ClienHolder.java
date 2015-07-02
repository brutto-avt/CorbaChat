package Cliente;

public final class ClienHolder implements org.omg.CORBA.portable.Streamable {
    public Cliente.Clien value = null;

    public ClienHolder() {
    }

    public ClienHolder(Cliente.Clien initialValue) {
        value = initialValue;
    }

    public void _read(org.omg.CORBA.portable.InputStream i) {
        value = Cliente.ClienHelper.read(i);
    }

    public void _write(org.omg.CORBA.portable.OutputStream o) {
        Cliente.ClienHelper.write(o, value);
    }

    public org.omg.CORBA.TypeCode _type() {
        return Cliente.ClienHelper.type();
    }
}
