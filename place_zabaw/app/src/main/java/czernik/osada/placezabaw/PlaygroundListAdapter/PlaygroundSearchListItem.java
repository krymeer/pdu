package czernik.osada.placezabaw.PlaygroundListAdapter;

import android.graphics.Bitmap;
import android.media.Image;

public class PlaygroundSearchListItem {
    private String distance;
    private String location;
    private Bitmap miniature;
    private float rating;

    public PlaygroundSearchListItem(String location, String distance, Bitmap miniature, float rating)
    {
        setDistance(distance);
        setLocation(location);
        setMiniature(miniature);
        setRating(rating);
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Bitmap getMiniature() {
        return miniature;
    }

    public void setMiniature(Bitmap miniature) {
        this.miniature = miniature;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
