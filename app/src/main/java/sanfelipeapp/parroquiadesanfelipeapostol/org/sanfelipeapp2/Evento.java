package sanfelipeapp.parroquiadesanfelipeapostol.org.sanfelipeapp2;

/**
 * Created by MBrandle on 10/03/2016.
 */
public class Evento {
    int id;
    String fecha;
    String descripcion;

    public Evento(){

    }

    public Evento(String fecha, String descripcion){
        super();
        this.fecha=fecha;
        this.descripcion=descripcion;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", fecha=" + fecha + ", desc=" + descripcion
                + "]";
    }
}
