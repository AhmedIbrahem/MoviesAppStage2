package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.RoomDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;

@Dao
public interface MovieDao {
    @Insert
    void InsertMovie(Movie movie);
    @Delete
    void DelteMovie(Movie movie);
    @Query("Select * From Movie")
    LiveData<List<Movie>>GetAllMovies();

    @Query("Select Id From Movie where Id=:id")
    String Search(String id);
}
