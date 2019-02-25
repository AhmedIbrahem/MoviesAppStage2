package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;



import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Loaders.ReviewsLoader;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Loaders.TrilarLoader;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Reviews;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Trilar;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Adapters.ReviwesAdapter;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Adapters.TrilarAdapter;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.RoomDatabase.MovieViewModel;


public class DetailActitvyFragment extends Fragment implements AsyncTaskCompleteListener{
    RecyclerView TrilarRecyclrView;
    RecyclerView ReviewRecyclerView;
    AsyncTaskCompleteListener call;
    Movie movie=new Movie();
    static List<Trilar> TrilarList=new ArrayList<>();
    static List<Reviews> ReviewsList=new ArrayList<>();
     LiveData<List<Movie>> Data;
     private MovieViewModel movieViewModel;
     TextView TextViewTrilar,TextViewRevies;

     Context context;
     static boolean s;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        movieViewModel=ViewModelProviders.of(this).get(MovieViewModel.class);

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_detail_actitvy,container,false);
        TextView Title,Overview,RelaseData,VoteAvrage;
        ImageView Poster,BackgroundPoster;
        TrilarRecyclrView=v.findViewById(R.id.Trilar_Recyclar_view);
        ReviewRecyclerView=v.findViewById(R.id.Reviws_Recycler_view);
        Poster=v.findViewById(R.id.poster);
        BackgroundPoster=v.findViewById(R.id.BackgroundPoster);
        Title=v.findViewById(R.id.Title);
        RelaseData=v.findViewById(R.id.relaseDate);
        VoteAvrage=v.findViewById(R.id.voteavrage);
        TextViewTrilar=v.findViewById(R.id.Movie_Text_Trailar);
        TextViewRevies=v.findViewById(R.id.Movie_Text_Reviews);
        Intent getData=getActivity().getIntent();
        final MaterialFavoriteButton materialFavoriteButton =v.findViewById(R.id.favorite_button);
        movie=getData.getParcelableExtra("Movie");
        s=movieViewModel.Search(movie.getId().toString())==("2");
        Log.d("s value", "onCreateView: "+""+movieViewModel.Search(movie.getId().toString()));
        if(s)
        {
            materialFavoriteButton.setBackgroundColor(Color.RED);
        }
        else
        {
            materialFavoriteButton.setBackgroundColor(Color.WHITE);
        }


        if(movie !=null)
        {


            Title.setText(movie.getTitle().toString());
            RelaseData.setText(movie.getRelaseData().toString());
            VoteAvrage.setText(movie.getVoteAverage().toString());
            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/"+movie.getPoster()).into(Poster);
            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/"+movie.getBackgroundPoster()).into(BackgroundPoster);

            if (savedInstanceState !=null)
            {
                Log.d("onsave", "onCreateViewFragmentDetails: ");
                TrilarList=savedInstanceState.getParcelableArrayList("MovieTrailars");
                ReviewsList=savedInstanceState.getParcelableArrayList("MovieReviewa");
                call=this;
                call.onTrilarComplite(TrilarList);
                call.onReviwsTaskComplete(ReviewsList);

            }
            else {

                TrilarLoader trilarLoader = new TrilarLoader();
                trilarLoader.callback = this;
                trilarLoader.execute(movie.getId().toString());
                ReviewsLoader reviewsLoader = new ReviewsLoader();
                reviewsLoader.callback = this;
                reviewsLoader.execute(movie.getId().toString());

            }
        }

        Log.d("title",""+movie.getBackgroundPoster());
        materialFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s)
                {
                    Log.d("Delet", "onClick:De;ete "+s);
                    movieViewModel.Delte(movie);
                    Log.d("insert", "onClick:deleted ");
                    materialFavoriteButton.setBackgroundColor(Color.WHITE);
                    s=false;



                }
                else
                {
                    Log.d("insert", "onClick:inserterd "+s);
                    movieViewModel.insert(movie);
                    Log.d("insert", "onClick:inserterd ");
                    materialFavoriteButton.setBackgroundColor(Color.RED);
                    s=true;
                }
            }
        });




 return v;
    }



    @Override
    public List<Movie> onTaskComplete(List<Movie> Movies) {
        return null;
    }

    @Override
    public List<Trilar> onTrilarComplite(List<Trilar> Trilars) {
        TrilarList=Trilars;
        if(Trilars.size()>0) {
            TextViewTrilar.setText("Movie Trilars");
        }
            TrilarAdapter trilarAdapter = new TrilarAdapter(Trilars, getActivity().getApplicationContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
            TrilarRecyclrView.setLayoutManager(layoutManager);
            TrilarRecyclrView.setAdapter(trilarAdapter);


        return null;

    }

    @Override
    public List<Reviews> onReviwsTaskComplete(List<Reviews> reviews) {
        ReviewsList=reviews;
        if(reviews.size()>0) {
            TextViewRevies.setText("Movie Reviews");
        }
            ReviwesAdapter reviwesAdapter = new ReviwesAdapter(reviews, getActivity().getApplicationContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
            ReviewRecyclerView.setLayoutManager(layoutManager);
            ReviewRecyclerView.setAdapter(reviwesAdapter);

        return null;
    }




    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MovieTrailars", (ArrayList<? extends Parcelable>) TrilarList);
        outState.putParcelableArrayList("MovieReviewa", (ArrayList<? extends Parcelable>) ReviewsList);

    }




}

