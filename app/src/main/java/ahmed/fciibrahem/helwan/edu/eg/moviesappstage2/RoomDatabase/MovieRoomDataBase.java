package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;

@Database(entities = Movie.class,version = 1)
public abstract class MovieRoomDataBase extends RoomDatabase{
    private static MovieRoomDataBase Instance;
    public abstract MovieDao movieDao();
     static synchronized MovieRoomDataBase getInastance(Context context)
    {
        if (Instance==null)
        {
            Instance= Room.databaseBuilder(context.getApplicationContext(),MovieRoomDataBase.class,"Movie").build();
        }
return Instance;
    }


}
