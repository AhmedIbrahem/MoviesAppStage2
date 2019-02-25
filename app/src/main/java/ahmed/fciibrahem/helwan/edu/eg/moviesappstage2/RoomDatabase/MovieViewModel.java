package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> AllMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepository(application);
        AllMovies=movieRepository.getMovieData();
    }
    public LiveData<List<Movie>> getAllMovies()
    {
        return AllMovies;
    }

    public void insert(Movie movie)
    {
        movieRepository.insert(movie);

    }
    public void Delte(Movie movie)
    {
        movieRepository.Delete(movie);
    }
    public String Search(String id)
    {
        String result=movieRepository.Search(id);
        Log.d("result", "Search: "+result);

        return  result;
    }
}
