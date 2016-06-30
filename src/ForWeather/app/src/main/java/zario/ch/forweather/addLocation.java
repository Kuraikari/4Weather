package zario.ch.forweather;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class addLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        name = (EditText) findViewById(R.id.editText);
    }

    EditText name;

    public void onAdd(View v) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("point",new Point(10,10));

        Toast.makeText(this, returnIntent.getStringExtra("name"),
                Toast.LENGTH_LONG).show();
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    public void getUserLocation()
    {

    }
}
