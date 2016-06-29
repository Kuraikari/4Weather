package zario.ch.forweather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Weatherlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherlist);

        listview = (ListView) findViewById(R.id.listView);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

            }

        });

        adapter = new ArrayAdapter<String>(this,
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
                addItem("");
                Toast.makeText(this, name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();

    public ListView listview;
    public void addItem(String name) {
        listItems.add(name);
        adapter.notifyDataSetChanged();
    }
}
