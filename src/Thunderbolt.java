public class Thunderbolt {
    public int codigo;
    public String nombre;
    public String habilidadPrincipal;
    public int nivelRedencion;
    public String misionAsignada;

    public Thunderbolt(int codigo, String nombre, String habilidadPrincipal, int nivelRedencion, String misionAsignada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.habilidadPrincipal = habilidadPrincipal;
        this.nivelRedencion = nivelRedencion;
        this.misionAsignada = misionAsignada;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabilidadPrincipal() {
        return habilidadPrincipal;
    }

    public int getNivelRedencion() {
        return nivelRedencion;
    }

    public String getMisionAsignada() {
        return misionAsignada;
    }



}
