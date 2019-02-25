package ahmed.fciibrahem.helwan.edu.eg.moviesappstage2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trilar implements Parcelable {
    private String Id;
    private String Key;
    private String Name;

    protected Trilar(Parcel in) {
        Id = in.readString();
        Key = in.readString();
        Name = in.readString();
    }
public Trilar()
{

}
    public static final Creator<Trilar> CREATOR = new Creator<Trilar>() {
        @Override
        public Trilar createFromParcel(Parcel in) {
            return new Trilar(in);
        }

        @Override
        public Trilar[] newArray(int size) {
            return new Trilar[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Key);
        dest.writeString(Name);
    }
}
