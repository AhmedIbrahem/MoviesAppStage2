package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.R;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Trilar;

public class TrilarAdapter extends RecyclerView.Adapter<TrilarAdapter.TrilarViewHolder> {
   static List<Trilar> Trilars=new ArrayList<>();
    Trilar trilar;
    Context context;

    public TrilarAdapter(List<Trilar> trilars, Context context) {
        this.Trilars=trilars;
        this.context=context;

    }


    @NonNull
    @Override
    public TrilarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trilar_recycler_item,viewGroup,false);
        TrilarViewHolder holder=new TrilarViewHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrilarViewHolder trilarViewHolder, int i) {
        trilar=Trilars.get(i);
        String ImageVedio="https://img.Youtube.com/vi/"+trilar.getKey()+"/1.jpg";
        Log.d("imageurl", ""+ImageVedio);
        Picasso.with(context).load(ImageVedio.toString()).resize(200,100).into(trilarViewHolder.TrailerVideo);

    }

    @Override
    public int getItemCount() {
        return Trilars.size();
    }

    public class TrilarViewHolder extends RecyclerView.ViewHolder
    {
        ImageView TrailerVideo;

        public TrilarViewHolder(@NonNull View itemView) {
            super(itemView);
            TrailerVideo=itemView.findViewById(R.id.Trilar_view);
            TrailerVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Trilar selectedTrailer=Trilars.get(getAdapterPosition());
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+selectedTrailer.getKey().toString())).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    Log.d("vido", ": "+trilar.getName().toString()+""+trilar.getKey().toString());
                }
            });

        }
    }
}
