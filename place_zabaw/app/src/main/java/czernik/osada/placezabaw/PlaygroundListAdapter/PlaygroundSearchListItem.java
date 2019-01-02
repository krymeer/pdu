package czernik.osada.placezabaw.PlaygroundListAdapter;

import android.graphics.Bitmap;
//import android.media.Image;

public class PlaygroundSearchListItem {
    private int id;
    private String distance;
    private String town;
    private String street;
    private Bitmap miniature;
    private float rating;

    public PlaygroundSearchListItem(int id, String town, String street, String distance, Bitmap miniature, float rating) {
        setId(id);
        setDistance(distance);
        setTown(town);
        setStreet(street);
        setMiniature(miniature);
        setRating(rating);
    }

    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getDistance() {
        return this.distance;
    }

    private void setDistance(String distance) {
        this.distance = distance;
    }

    String getTown() {
        return this.town;
    }

    private void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return this.street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    Bitmap getMiniature() {
        return miniature;
    }

    private void setMiniature(Bitmap miniature) {
        this.miniature = miniature;
    }

    float getRating() {
        return this.rating;
    }

    private void setRating(float rating) {
        this.rating = rating;
    }
}
