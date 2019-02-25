package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.R;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Reviews;

public class ReviwesAdapter extends RecyclerView.Adapter<ReviwesAdapter.ReviewViewHolder>  {
ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Reviews reviews;
List<Reviews> Reviews=new ArrayList<>();
Context context;
    TextView Author,Content,show;

    public ReviwesAdapter(List<Reviews> reviews, Context applicationContext) {
        this.Reviews=reviews;
        this.context=applicationContext;
    }


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_recycler_item,viewGroup,false);
        ReviewViewHolder holder=new ReviewViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int pos) {
        reviews=Reviews.get(reviewViewHolder.getAdapterPosition());
        Author.setText(Reviews.get(pos).getAuthor().toString());
        Content.setText(Reviews.get(pos).getContent().toString());

    }

    @Override
    public int getItemCount() {
        return Reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder
   {

       public ReviewViewHolder(@NonNull View itemView) {
           super(itemView);
           Author=itemView.findViewById(R.id.Author);
           Content=itemView.findViewById(R.id.review_content);
           show=itemView.findViewById(R.id.show);
           show.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Log.d("content+auther", ""+Reviews.get(getAdapterPosition()).getAuthor().toString()+""+Reviews.get(getAdapterPosition()).getContent().toString());
                   AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                   builder.setTitle(Reviews.get(getAdapterPosition()).getAuthor().toString()).setMessage(Reviews.get(getAdapterPosition()).getContent().toString()).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   });
                   AlertDialog alertDialog=builder.create();
                   alertDialog.show();
               }
           });
           }
   }
}
