package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.AsyncTaskCompleteListener;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;


public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> MovieData;
    AsyncTaskCompleteListener callback;
   public  String Movie_Id;


    MovieRepository(){};
    MovieRepository(Application application)
    {
        MovieRoomDataBase dataBase=MovieRoomDataBase.getInastance(application);
        movieDao=dataBase.movieDao();
        MovieData=movieDao.GetAllMovies();

    }
    LiveData<List<Movie>> getMovieData()
    {
        return MovieData;
    }

    public void insert(Movie movie)
    {
        new InsetrAsyncTask(movieDao).execute(movie);
    }
    public void Delete(Movie movie)
    {
        new DeleteAsyncTask(movieDao).execute(movie);
    }
    public String Search(String id)
    {
        try {
            new  SearchAsyncTask(movieDao).execute(id).get() ;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Movie_Id;
    }
    private static class InsetrAsyncTask extends android.os.AsyncTask<Movie,Void,Void>
   {
       private MovieDao movieDao;
       InsetrAsyncTask(MovieDao dao)
       {
           movieDao=dao;
       }

       @Override
       protected Void doInBackground(Movie... movies) {
           movieDao.InsertMovie(movies[0]);
           return null;
       }
   }
    private static class DeleteAsyncTask extends AsyncTask<Movie,Void,Void>
    {
        private MovieDao movieDao;
        DeleteAsyncTask(MovieDao dao)
        {
            movieDao=dao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.DelteMovie(movies[0]);
            return null;
        }
    }
    private class SearchAsyncTask extends AsyncTask<String,String,String>
    {
        AsyncTaskCompleteListener callback;
        private MovieDao movieDao;
        SearchAsyncTask(MovieDao dao)
        {
            movieDao=dao;
        }

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            if (movieDao.Search(strings[0])==null)
            {
                result="1";
            }
            else
            {
                result="2";
            }
            Movie_Id=result;
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Movie_Id=s;

            Log.d("ssss", "onPostExecute: "+s);

        }

    }

}
