package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Loaders.MovieLoader;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Reviews;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Trilar;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Adapters.MovieAdapter;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.RoomDatabase.MovieViewModel;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class ActivtyMainFragment extends Fragment implements AsyncTaskCompleteListener {
    RecyclerView MovieRecyclerView;
    static List<Movie> MoviesData = new ArrayList<>();
    MovieLoader movieLoader = new MovieLoader();
    ArrayList<Movie> MovieList = new ArrayList<>();
    AsyncTaskCompleteListener call;
    private MovieViewModel movieViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        else
            connected = false;

        if (!connected)
        {
            Toast.makeText(getActivity().getApplicationContext(),"No Internet Access please Open the Data or Wifi",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu,menu);    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mostPopular)
        {
            MovieLoader movieLoader=new MovieLoader();
            movieLoader.callback=this;
            movieLoader.execute("popular");

        }
         if(item.getItemId()==R.id.topRated)
        {
            MovieLoader movieLoader=new MovieLoader();
            movieLoader.callback=this;
            movieLoader.execute("top_rated");

        }
        if(item.getItemId()==R.id.favorit)
        {
            call=this;
            movieViewModel.getAllMovies().observe(getActivity(), new Observer<List<Movie>>() {
                @Override
                public void onChanged(@Nullable List<Movie> movies) {
                    if(movies.size()==0)
                    {
                        Toast.makeText(getActivity().getApplicationContext(),"No Favorite Movies",Toast.LENGTH_LONG).show();

                    }
                    call.onTaskComplete(movies);

                }
            });
        }


        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieViewModel= ViewModelProviders.of(this).get(MovieViewModel.class);

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_activty_main,container,false);
        MovieRecyclerView=v.findViewById(R.id.MovieRecyclarView);
        if(savedInstanceState !=null)
        {
          MoviesData= savedInstanceState.getParcelableArrayList("DataList");
            Log.d("iam in save instance", " "+MoviesData);
            call=this;
            call.onTaskComplete(MoviesData);
            Log.d("iam in save instance", " "+MoviesData);
        }
        else {
            movieLoader.callback = this;
            movieLoader.execute("popular");
            Log.d("iam in esynctask", "hiiii");

        }
        return v;

    }





    @Override
    public List<Movie> onTaskComplete(List<Movie> Movies) {
        MoviesData=Movies;
        Log.d("Movies", "onTaskComplete: "+Movies);
        MovieAdapter movieAdapter=new MovieAdapter(Movies,getActivity().getApplicationContext());
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity().getApplicationContext(),2);
        MovieRecyclerView.setLayoutManager(layoutManager);
        MovieRecyclerView.setAdapter(movieAdapter);
        return null;
    }

    @Override
    public List<Trilar> onTrilarComplite(List<Trilar> Trilars) {
        return null;
    }

    @Override
    public List<Reviews> onReviwsTaskComplete(List<Reviews> reviews) {
        return null;
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("DataList", (ArrayList<? extends Parcelable>) MoviesData);
    }
}