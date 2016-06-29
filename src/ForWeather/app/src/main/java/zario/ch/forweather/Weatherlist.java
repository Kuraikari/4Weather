package zario.ch.forweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Weatherlist extends AppCompatActivity {

    Activity main;
    public static Wetter wetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherlist);

        main = this;
        listview = (ListView) findViewById(R.id.listView);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent i = new Intent(main, wetterort.class);
                wetter = new Wetter("Bern", "ch", main);
                startActivity(i);
            }

        });

        adapter = new ArrayAdapter<Wetter>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
        listview.setAdapter(adapter);
    }

    public void onAddClick(View v) {
        Intent intent = new Intent(this, addLocation.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                addItem(new Wetter("Zürich", "zh", main));
                Toast.makeText(this, name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    ArrayAdapter<Wetter> adapter;
    ArrayList<Wetter> listItems = new ArrayList<Wetter>();

    public ListView listview;
    public void addItem(Wetter wetter) {
        listItems.add(wetter);
        adapter.notifyDataSetChanged();
    }
}