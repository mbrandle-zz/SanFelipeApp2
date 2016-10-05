package sanfelipeapp.parroquiadesanfelipeapostol.org.sanfelipeapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Sacramentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_sacramentos);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
