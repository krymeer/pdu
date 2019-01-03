package czernik.osada.placezabaw.PlaygroundListAdapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.playground_search_item, viewGroup, false);
        }

        PlaygroundSearchListItem currentItem    = (PlaygroundSearchListItem) getItem(i);
        TextView distanceTextView               = view.findViewById(R.id.distance);
        TextView streetTextView                 = view.findViewById(R.id.searchItem_street);
        TextView townTextView                   = view.findViewById(R.id.searchItem_town);
        RatingBar ratingBar                     = view.findViewById(R.id.ratingBar);
        ImageView imageView                     = view.findViewById(R.id.imageView);
        String distance                         = currentItem.getDistance() + " km";

        distanceTextView.setText(distance);
        streetTextView.setText(currentItem.getStreet());
        townTextView.setText(currentItem.getTown());
        ratingBar.setRating(currentItem.getRating());
        setRatingBarColor(ratingBar);

        if (currentItem.getMiniature() != null)
        {
            imageView.setImageBitmap(currentItem.getMiniature());
        }

        return view;
    }

    private void setRatingBarColor(RatingBar ratingBar) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ResourcesCompat.getColor(this.context.getResources(), R.color.colorAccentLight, null), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(ResourcesCompat.getColor(this.context.getResources(), R.color.colorBackground, null), PorterDuff.Mode.SRC_ATOP);
    }
}
