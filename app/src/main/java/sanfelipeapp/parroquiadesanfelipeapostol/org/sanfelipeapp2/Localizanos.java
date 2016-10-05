package sanfelipeapp.parroquiadesanfelipeapostol.org.sanfelipeapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Localizanos extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_localizanos);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sanFelipe = new LatLng(28.646176, -106.086348);
            mMap.addMarker(new MarkerOptions().position(sanFelipe).title("Parroquia De San Felipe Apostol"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sanFelipe));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanFelipe, 14.0f));
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
