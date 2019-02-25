package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Movie;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Reviews;
import ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models.Trilar;

public interface AsyncTaskCompleteListener {
    public List<Movie> onTaskComplete(List<Movie> Movies);
    public List<Trilar>onTrilarComplite(List<Trilar> Trilars);
    public List<Reviews> onReviwsTaskComplete(List<Reviews> reviews);

}
