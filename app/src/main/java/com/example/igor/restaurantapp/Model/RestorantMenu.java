package com.example.igor.restaurantapp.Model;

/**
 * Created by Igor on 07-Aug-16.
 */
import android.os.Parcelable;
import android.os.Parcel;
import java.util.ArrayList;

public class RestorantMenu implements Parcelable {

    private Long id;
    private String title;
    private String thumbnailUrl;
    private String price;
    private double rating;
    private ArrayList<String> genre;

    public RestorantMenu() {
    }



    public RestorantMenu(Long id, String name, String thumbnailUrl, String price, double rating,
                         ArrayList<String> genre) {
        this.id=id;
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.rating = rating;
        this.genre = genre;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {this.price = price;}

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }


    protected RestorantMenu(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readLong();
        title = in.readString();
        thumbnailUrl = in.readString();
        price = in.readString();
        rating = in.readDouble();
        if (in.readByte() == 0x01) {
            genre = new ArrayList<String>();
            in.readList(genre, String.class.getClassLoader());
        } else {
            genre = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(id);
        }
        dest.writeString(title);
        dest.writeString(thumbnailUrl);
        dest.writeString(price);
        dest.writeDouble(rating);
        if (genre == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(genre);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RestorantMenu> CREATOR = new Parcelable.Creator<RestorantMenu>() {
        @Override
        public RestorantMenu createFromParcel(Parcel in) {
            return new RestorantMenu(in);
        }

        @Override
        public RestorantMenu[] newArray(int size) {
            return new RestorantMenu[size];
        }
    };
}