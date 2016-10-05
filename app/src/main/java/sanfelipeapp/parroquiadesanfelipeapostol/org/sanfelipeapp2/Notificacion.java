package sanfelipeapp.parroquiadesanfelipeapostol.org.sanfelipeapp2;

/**
 * Created by MBrandle on 09/03/2016.
 */
public class Notificacion {
    int id;
    String fecha;
    String descripcion;

    public Notificacion(){

    }

    public Notificacion(String fecha, String descripcion){
        super();
        this.fecha=fecha;
        this.descripcion=descripcion;
    }

    @Override
    public String toString() {
        return "Notificacion [id=" + id + ", fecha=" + fecha + ", desc=" + descripcion
                + "]";
    }
}
