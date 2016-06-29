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

/**
 * Created by bsuttm on 29.06.2016.
 */
public class Wetter {
    public Wetter(String ort, String land, Activity activity) {
        ortladen(ort, land, activity);
    }

    public void ortladen(final String ort, final String land, final Activity activity) {
        new AsyncTask<String, String, String>() {

            ProgressDialog p;

            public void onPreExecute() {
                // Show progress
                p = new ProgressDialog(activity);
                p.setTitle("Loading data");
                p.show();
            }

            public String doInBackground(String... text) {
                try {
                    String stadt = "bern";
                    String land = "ch";
                    URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+stadt+"%2C%20"+land+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.getResponseCode();
                    return IOUtils.toString(urlc.getInputStream());

                } catch (MalformedURLException e) {
                    Log.v("Wetter", "url error");
                    return "URL Error";
                } catch (IOException e) {
                    Log.v("Wetter", "file error");
                    return "File error";
                } catch (NetworkOnMainThreadException e) {
                    Log.v("Wetter", "internet exception");
                    return "Internet not available";
                }
            }

            public void onPostExecute(String result) {
                parseJson(result);
                p.hide();
            }
        }.execute();
    }

    String city, country, ctemp, ctext;

    public void parseJson(String text) {
        try {
            JSONObject json = new JSONObject(text);
            json = json.getJSONObject("query");
            json = json.getJSONObject("results");
            json = json.getJSONObject("channel");

            JSONObject location = json.getJSONObject("location");
            city = location.getString("city");
            country = location.getString("country");

            JSONObject item = json.getJSONObject("item");

            JSONObject condition = item.getJSONObject("condition");
            ctemp = condition.getString("temp");
            ctext = condition.getString("text");
        }
        catch (JSONException e) {
            Log.v("Wetter", "jsdn exception");
            Log.v("Wetter", text);
        }
    }

    public String toString(){
        if (city != null) {
            return city + " - " + ctemp + "°C, " + ctext;
        }
        else {
            return "Internet error";
        }
    }
}
