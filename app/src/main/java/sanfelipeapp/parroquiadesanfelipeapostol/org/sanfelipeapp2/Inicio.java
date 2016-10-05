package sanfelipeapp.parroquiadesanfelipeapostol.org.sanfelipeapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.appindexing.AppIndex;
import com.onesignal.OneSignal;
import com.onesignal.OneSignal.NotificationOpenedHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inicio);

        /*OneSignal.startInit(this).init();*/

            OneSignal.startInit(this)
                    .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                    .setAutoPromptLocation(true)
                    .init();


            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try{
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {

            } else if (id == R.id.nav_eventos) {
                Intent myIntent = new Intent(Inicio.this, Eventos.class);
                Inicio.this.startActivity(myIntent);
            } else if (id == R.id.nav_sacramentos) {
                Intent myIntent = new Intent(Inicio.this, Sacramentos.class);
                Inicio.this.startActivity(myIntent);
            } else if (id == R.id.nav_notificaciones) {
                Intent myIntent = new Intent(Inicio.this, Notificaciones.class);
                Inicio.this.startActivity(myIntent);
            } else if (id == R.id.nav_horarios) {
                Intent myIntent = new Intent(Inicio.this, Horarios.class);
                Inicio.this.startActivity(myIntent);
            } else if (id == R.id.nav_contacto) {
                Intent myIntent = new Intent(Inicio.this, Contacto.class);
                Inicio.this.startActivity(myIntent);

            } else if(id==R.id.nav_localizanos){
                Intent myIntent = new Intent(Inicio.this, Localizanos.class);
                Inicio.this.startActivity(myIntent);
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }catch (Throwable t) {
            t.printStackTrace();
        }
        return true;
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        /**
         * Callback to implement in your app to handle when a notification is opened from the Android status bar or
         * a new one comes in while the app is running.
         * This method is located in this Application class as an example, you may have any class you wish implement NotificationOpenedHandler and define this method.
         *
         * @param message        The message string the user seen/should see in the Android status bar.
         * @param additionalData The additionalData key value pair section you entered in on onesignal.com.
         * @param isActive       Was the app in the foreground when the notification was received.
         */
        @Override
        public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
            String additionalMessage = "";

            try {
                if (additionalData != null) {
                    if (additionalData.has("actionSelected"))
                        additionalMessage += "Pressed ButtonID: " + additionalData.getString("actionSelected");

                    additionalMessage = message + "\nFull additionalData:\n" + additionalData.toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
                    String currentDateandTime = sdf.format(new Date());

                    String[] arrayEvento=message.split("-");
                    if(arrayEvento[0].trim().equals("Evento")){
                        Evento nEvento=new Evento(arrayEvento[2].trim(),arrayEvento[1].trim());
                        MySQLiteHelper db=new MySQLiteHelper(Inicio.this);
                        db.addEventos(nEvento);

                        Intent myIntent = new Intent(Inicio.this, Eventos.class);
                        Inicio.this.startActivity(myIntent);
                    }else{
                        Notificacion nNotificacion=new Notificacion(currentDateandTime,arrayEvento[1].trim());
                        MySQLiteHelper db = new MySQLiteHelper(Inicio.this);
                        db.addNotificacion(nNotificacion);

                        Intent myIntent = new Intent(Inicio.this, Notificaciones.class);
                        Inicio.this.startActivity(myIntent);
                    }


                }

                Log.d("OneSignalExample", "message:\n" + message + "\nadditionalMessage:\n" + additionalMessage);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }



}
