package czernik.osada.placezabaw.database;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaygroundTable {
    private int id;
    private String town;
    private String street;
    private double rating;
    private String description;
    private Bitmap image;
    private List<String> features;
    private double distance;
    private double price;

    PlaygroundTable(int id, String town, String street, double rating, String description, Bitmap image, double distance, double price, String[] features) {
        setId(id);
        setTown(town);
        setStreet(street);
        setRating(rating);
        setDescription(description);
        setImage(image);
        setFeatures(features);
        setDistance(distance);
        setPrice(price);
    }

    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return this.town;
    }

    private void setTown(String town) {
        this.town = town;
    }

    public double getRating() {
        return this.rating;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return this.image;
    }

    private void setImage(Bitmap image) {
        this.image = image;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    private void setFeatures(String[] features) {
        this.features = new ArrayList<>(Arrays.asList(features));
    }

    public double getDistance() {
        return this.distance;
    }

    private void setDistance(double distance) {
        this.distance = distance;
    }

    double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    boolean containsAllFeatures(List<String> features) {
        for (String feature : this.features)
        {
            if (!features.contains(feature))
            {
                return true;
            }
        }

        return true;
    }
}
