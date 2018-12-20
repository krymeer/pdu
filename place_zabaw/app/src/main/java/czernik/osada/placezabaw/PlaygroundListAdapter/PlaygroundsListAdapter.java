package czernik.osada.placezabaw.PlaygroundListAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import czernik.osada.placezabaw.R;

public class PlaygroundsListAdapter extends BaseAdapter {
    private Context context; //context
    private List<PlaygroundSearchListItem> items; //data source of the list adapter

    public PlaygroundsListAdapter(Context context, List<PlaygroundSearchListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.playground_search_item, viewGroup, false);
        }

        // get current item to be displayed
        PlaygroundSearchListItem currentItem = (PlaygroundSearchListItem) getItem(i);

        // get the TextView for item name and item description
        TextView locationTextView = (TextView) view.findViewById(R.id.street);
        TextView distanceTextView = (TextView) view.findViewById(R.id.distance);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        String distance = currentItem.getDistance() + " km";

        //sets the text for item name and item description from the current item object
        locationTextView.setText(currentItem.getLocation());
        distanceTextView.setText(distance);
        ratingBar.setRating(currentItem.getRating());
        if (currentItem.getMiniature() != null) {
            imageView.setImageBitmap(currentItem.getMiniature());
        }

        setRatingBarColor(ratingBar);

        // returns the view for the current row
        return view;
    }

    private void setRatingBarColor(RatingBar ratingBar) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.rgb(255, 128, 0), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
    }
}
