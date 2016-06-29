package zario.ch.forweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bsuttm on 29.06.2016.
 */
public class ortlist {

    public ArrayList<String> list = new ArrayList<String>();

    public void ortsuchen(final String ort) {
        new AsyncTask<String, String, String[]>() {

            public String[] doInBackground(String... text) {
                try {
                    URL url = new URL("http://api.geonames.org/searchJSON?q="+ort+"&maxRows=10&username=demo");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.getResponseCode();
                    String stream = IOUtils.toString(urlc.getInputStream());
                    return parse(stream);

                } catch (MalformedURLException e) {
                    return new String[0];
                } catch (IOException e) {
                    return new String[0];
                } catch (NetworkOnMainThreadException e) {
                    return new String[0];
                }
            }

            public void onPostExecute(String[] result) {
                list.clear();
                for (String s : result) {
                    list.add(s);
                }
            }
        }.execute();
    }

    public String[] parse(String text) {
        try {
            JSONObject json = new JSONObject(text);
            json = json.getJSONObject("geonames");
//asdasd
            Iterator keys = json.keys();

            ArrayList<String> poollist = new ArrayList<>();

            while (keys.hasNext()) {
                JSONObject next = json.getJSONObject(keys.next().toString());

                String beckenname = next.getString("beckenname");
                String beckentemp = next.getString("temp");

                poollist.add(beckenname + " - " + beckentemp + "°C");
            }

            return new String[0];
        } catch (JSONException e) {
            Log.v("ortliste", text);
            return new String[0];
        }
    }
}
