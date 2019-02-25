package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Loaders;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.AsyncTaskCompleteListener;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Trilar;

public class TrilarLoader extends AsyncTask<String,Void,String> {
    public AsyncTaskCompleteListener callback;
    static String TrilarData="";

    @Override
    protected String doInBackground(String... strings) {
        Log.d("trilarBackground", "doInBackground: ");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            final String API_KEY_PARAM = "api_key";
            final String PAGE_PARAM = "page";
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("api.themoviedb.org")
                    .appendPath("3")
                    .appendPath("movie")
                    .appendPath(strings[0])
                    .appendPath("videos")
                    .appendQueryParameter(API_KEY_PARAM,"09ef905fb0a8f078de853a6aa6ac8983").build();
            URL url = new URL(builder.toString());
            Log.d("trailerurl", ""+url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            TrilarData = buffer.toString();
            Log.d("TrailerData", ""+TrilarData);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {


                }
            }
        }

        return TrilarData;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (TrilarData != null) {
            List<Trilar> TrilarList=new ArrayList();

            try {

                Log.d("post", "onPostExecute: ");
                JSONObject Trilar = new JSONObject(TrilarData);
                JSONArray TrilarArray = Trilar.getJSONArray("results");
                for (int x = 0; x < TrilarArray.length(); x++) {
                    Trilar trilar = new Trilar();
                    trilar.setKey(TrilarArray.getJSONObject(x).get("key").toString());
                    trilar.setName(TrilarArray.getJSONObject(x).get("name").toString());
                    trilar.setId(TrilarArray.getJSONObject(x).get("id").toString());
                    TrilarList.add(trilar);
                    Log.d("trailar"+(x), "" + trilar.getKey().toString());


                }}

            catch(JSONException e){
                e.printStackTrace();
            }

            callback.onTrilarComplite(TrilarList);
        }


    }
}

