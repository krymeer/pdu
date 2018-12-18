package czernik.osada.placezabaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlaygroundDetailsScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_details);
        TextView addressTextView    = findViewById(R.id.playgroundDetails_address);
        TextView distanceTextView   = findViewById(R.id.playgroundDetails_distance);
        RatingBar ratingBar         = findViewById(R.id.playgroundDetails_rating);
        Intent intent               = getIntent();
        Bundle bundle               = intent.getExtras();
        String address;
        String distance;
        float rating;

        if (bundle != null) {
            if (intent.hasExtra("address"))
            {
                address = bundle.getString("address");
                addressTextView.setText(address);
            }

            if (intent.hasExtra("distance"))
            {
                distance = bundle.getString("distance");
                distanceTextView.setText(distance);
            }

            if (intent.hasExtra("rating"))
            {
                rating = bundle.getFloat("rating");
                ratingBar.setRating(rating);
            }
        }
    }
}
