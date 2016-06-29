package zario.ch.forweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class wetterort extends AppCompatActivity {

    Wetter wetter;

    TextView titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wetterort);

        titel = (TextView) findViewById(R.id.titel);
        wetter = Weatherlist.wetter;

        titel.setText(wetter.city);
    }
}
