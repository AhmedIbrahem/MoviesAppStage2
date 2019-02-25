package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Reviews implements Parcelable{
    private String Author;
    private String Content;

    protected Reviews(Parcel in) {
        Author = in.readString();
        Content = in.readString();
    }
    public Reviews()
    {

    }

    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel in) {
            return new Reviews(in);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Author);
        dest.writeString(Content);
    }
}
