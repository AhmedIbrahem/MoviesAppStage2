package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "Movie")
public class Movie implements Parcelable {
    @PrimaryKey
    @NonNull
    private String  Id;

    private String Title;
    private String VoteAverage;
    private boolean Video;
    private long VoteCount;
    private String Poster;
    private String BackgroundPoster;
    private String OverView;
    private String RelaseData;

    protected Movie(Parcel in) {
        Id = in.readString();
        Title = in.readString();
        VoteAverage = in.readString();
        Video = in.readByte() != 0;
        VoteCount = in.readLong();
        Poster = in.readString();
        BackgroundPoster = in.readString();
        OverView = in.readString();
        RelaseData = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getVoteAverage() {
        return VoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        VoteAverage = voteAverage;
    }

    public boolean isVideo() {
        return Video;
    }

    public void setVideo(boolean video) {
        Video = video;
    }

    public long getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(long voteCount) {
        VoteCount = voteCount;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getBackgroundPoster() {
        return BackgroundPoster;
    }

    public void setBackgroundPoster(String backgroundPoster) {
        BackgroundPoster = backgroundPoster;
    }

    public String getOverView() {
        return OverView;
    }

    public void setOverView(String overView) {
        OverView = overView;
    }

    public String getRelaseData() {
        return RelaseData;
    }

    public void setRelaseData(String relaseData) {
        RelaseData = relaseData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Title);
        dest.writeString(VoteAverage);
        dest.writeByte((byte) (Video ? 1 : 0));
        dest.writeLong(VoteCount);
        dest.writeString(Poster);
        dest.writeString(BackgroundPoster);
        dest.writeString(OverView);
        dest.writeString(RelaseData);
    }
}
