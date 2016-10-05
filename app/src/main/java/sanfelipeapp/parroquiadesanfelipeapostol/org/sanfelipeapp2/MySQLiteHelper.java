package sanfelipeapp.parroquiadesanfelipeapostol.org.sanfelipeapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MBrandle on 09/03/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 7;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_NOTIFICACIONES_TABLE = "CREATE TABLE notificaciones ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fecha TEXT, "+
                "descripcion TEXT )";
        // create books table
        db.execSQL(CREATE_NOTIFICACIONES_TABLE);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        String currentDateandTime = sdf.format(new Date());
        ContentValues values = new ContentValues();
        values.put(KEY_FECHA, currentDateandTime);
        values.put(KEY_DESC, "Gracias por descargar nuestra aplicación. ");
        db.insert(TABLE_NOTIFICACIONES,
                null,
                values);

        String CREATE_EVENTOS_TABLE="CREATE TABLE eventos ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fecha TEXT, "+
                "descripcion TEXT )";
        db.execSQL(CREATE_EVENTOS_TABLE);

        values = new ContentValues();
        values.put(KEY_FECHA, "" );
        values.put(KEY_DESC, " ");
        db.insert(TABLE_EVENTOS,
                null,
                values);

        values = new ContentValues();
        values.put(KEY_FECHA, "03/Abril/2016 19:30" );
        values.put(KEY_DESC, "Misa Renovación de Compromisos de cada Ministerios ");
        db.insert(TABLE_EVENTOS,
                null,
                values);

        values = new ContentValues();
        values.put(KEY_FECHA, "04/Abril/2016 17:00 ");
        values.put(KEY_DESC, "Cuenta cuentos por: Maru Meza de Loera ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "03/Abril/2016 20:30 ");
        values.put(KEY_DESC, "Conferencia: El apostolado de los laicos por: Pbro. Jorge Rubio ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "05/Abril/2016 18:30");
        values.put(KEY_DESC, "Hora Santa Vocacional  ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "05/Abril/2016 20:30");
        values.put(KEY_DESC, "Café Vocacional ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "06/Abril/2016 19:30 ");
        values.put(KEY_DESC, "Bendición para la familia ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "06/Abril/2016 20:30 ");
        values.put(KEY_DESC, "Fogata Familiar ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "07/Abril/2016 ");
        values.put(KEY_DESC, "Torneo Zanahoria Inf: 614 313 2407 con Joe  ");
        db.insert(TABLE_EVENTOS,
                null,
                values);
        values = new ContentValues();
        values.put(KEY_FECHA, "07/Abril/2016 08:00");
        values.put(KEY_DESC, "Gran Kermesse  ");
        db.insert(TABLE_EVENTOS,
                null,
                values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS notificaciones");
        db.execSQL("DROP TABLE IF EXISTS eventos");
        // create fresh books table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_NOTIFICACIONES = "notificaciones";
    private static final String TABLE_EVENTOS="eventos";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FECHA = "fecha";
    private static final String KEY_DESC = "descripcion";

    private static final String[] COLUMNS = {KEY_ID,KEY_FECHA,KEY_DESC};

    public void addNotificacion(Notificacion notificacion){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FECHA, notificacion.fecha); // get fecha
        values.put(KEY_DESC, notificacion.descripcion); // get desc
        // 3. insert
        db.insert(TABLE_NOTIFICACIONES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close
        db.close();
    }

    public void addEventos(Evento evento){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FECHA, evento.fecha); // get fecha
        values.put(KEY_DESC, evento.descripcion); // get desc
        // 3. insert
        db.insert(TABLE_EVENTOS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close
        db.close();
    }

    public List<Notificacion> getAllNotificaciones(){
        List<Notificacion> lNotificaciones= new LinkedList<Notificacion>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NOTIFICACIONES+" ORDER BY id DESC";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Notificacion nNotificacion=null;
        if(cursor.moveToFirst()){
            do{
                nNotificacion=new Notificacion();
                nNotificacion.id=(Integer.parseInt(cursor.getString(0)));
                nNotificacion.fecha=(cursor.getString(1));
                nNotificacion.descripcion=(cursor.getString(2));
                lNotificaciones.add(nNotificacion);
            }while (cursor.moveToNext());
        }
        db.close();
        Log.d("getAllNotificaciones()", lNotificaciones.toString());
        return lNotificaciones;
    }

    public List<Evento> getAllEventos(){
        List<Evento> lEventos= new LinkedList<Evento>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_EVENTOS+" ORDER BY id DESC";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Evento nEvento=null;
        if(cursor.moveToFirst()){
            do{
                nEvento=new Evento();
                nEvento.id=(Integer.parseInt(cursor.getString(0)));
                nEvento.fecha=(cursor.getString(1));
                nEvento.descripcion=(cursor.getString(2));
                lEventos.add(nEvento);
            }while (cursor.moveToNext());
        }
        db.close();
        Log.d("getAllEventos()", lEventos.toString());
        return lEventos;
    }
}
