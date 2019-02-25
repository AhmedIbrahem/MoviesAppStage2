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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.AsyncTaskCompleteListener;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;

public class MovieLoader extends AsyncTask<String,Void,String>{
    public AsyncTaskCompleteListener callback;
    static String Data="";


    @Override
    protected String doInBackground(String... strings) {
        Log.d("Hiiiii", "doInBackground: ");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            Log.d("Tray", "doInBackground: ");
            final String API_KEY_PARAM = "api_key";
            final String PAGE_PARAM = "page";
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("api.themoviedb.org")
                    .appendPath("3")
                    .appendPath("movie")
                    .appendPath(strings[0])
                    .appendQueryParameter(API_KEY_PARAM, "09ef905fb0a8f078de853a6aa6ac8983");

            URL url = new URL(builder.toString());
            Log.d("ddd", "" + url);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            Log.d("input", "sssss: ");
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");

            }
            Data = buffer.toString();
            Log.d("Da", Data + "");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return Data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (Data != null) {
            List<Movie> MoviesData=new ArrayList();

            try {
                Log.d("post", "onPostExecute: ");
                JSONObject Movies = new JSONObject(Data);
                JSONArray MoviesArray = Movies.getJSONArray("results");
                for (int x = 0; x < MoviesArray.length(); x++) {
                    Movie movie = new Movie();

                    movie.setPoster(MoviesArray.getJSONObject(x).get("poster_path").toString());
                    movie.setBackgroundPoster(MoviesArray.getJSONObject(x).get("backdrop_path").toString());
                    movie.setTitle(MoviesArray.getJSONObject(x).get("original_title").toString());
                    movie.setVoteAverage(MoviesArray.getJSONObject(x).get("vote_average").toString());
                    movie.setRelaseData(MoviesArray.getJSONObject(x).get("release_date").toString());
                    movie.setOverView(MoviesArray.getJSONObject(x).get("overview").toString());
                    movie.setId(MoviesArray.getJSONObject(x).get("id").toString());
                    movie.setVoteCount(MoviesArray.getJSONObject(x).getLong("vote_count"));
                    movie.setVideo(MoviesArray.getJSONObject(x).getBoolean("video"));

                    MoviesData.add(movie);


                    Log.d("ohhhh", "" + movie.getTitle().toString());


                }}

            catch(JSONException e){
                e.printStackTrace();
            }

           callback.onTaskComplete(MoviesData);
        }
    }
}







