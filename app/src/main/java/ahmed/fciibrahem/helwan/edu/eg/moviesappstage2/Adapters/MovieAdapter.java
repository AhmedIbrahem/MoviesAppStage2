package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.DetailActivity;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<Movie> MovieList;
    Movie movie;
    Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
       this.MovieList = movieList;
        this.context = context;
    }



    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclr_item,viewGroup,false);
        MovieViewHolder holder=new MovieViewHolder(row);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int posation) {
        movie=MovieList.get(posation);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+movie.getPoster()).placeholder(R.drawable.ic_launcher_background).resize(160,200).into(movieViewHolder.Poster);


    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
    {
        TextView Title,VoteAverage,BackgroundPoster,OverView,RelaseData;
                ImageView Poster;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            Poster=itemView.findViewById(R.id.Movie_poster);
            Poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movie SelectedMovie=MovieList.get(getAdapterPosition());
                    Intent intent=new Intent(context,DetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("Movie",SelectedMovie);
                    context.startActivity(intent);
                }
            });


        }



    }
}
