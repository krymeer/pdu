package czernik.osada.placezabaw.database;

import android.graphics.Bitmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaygroundTable {
    private String address;
    private double rating;
    private String description;
    private Bitmap image;
    private List<String> functionalities;
    private double distance;
    private double price;

    public PlaygroundTable(String address, double rating, String description, Bitmap image, double distance, double price, String[] functionalities) {
        setAddress(address);
        setRating(rating);
        setDescription(description);
        setImage(image);
        setFunctionalities(functionalities);
        setDistance(distance);
        setPrice(price);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public List<String> getFunctionalities() {
        return functionalities;
    }

    public void setFunctionalities(String[] functionalities) {
        this.functionalities = new ArrayList<>(Arrays.asList(functionalities));
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean containsAllFunctionalities(List<String> functionalities) {
        for (String func: this.functionalities) {
            if (!functionalities.contains(func)) {
                return true;
            }
        }

        return true;
    }
}
