package czernik.osada.placezabaw;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlaygroundDetailsScreen extends AppCompatActivity{
    private String address;
    private float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_details);
        TextView addressTextView    = findViewById(R.id.playgroundDetails_address);
        TextView distanceTextView   = findViewById(R.id.playgroundDetails_distance);
        RatingBar ratingBar         = findViewById(R.id.playgroundDetails_rating);
        Intent intent               = getIntent();
        Bundle bundle               = intent.getExtras();
        String distance;

        if (bundle != null)
        {
            if (intent.hasExtra("address"))
            {
                this.address = bundle.getString("address");
                addressTextView.setText(this.address);
            }

            if (intent.hasExtra("distance"))
            {
                distance = bundle.getString("distance");
                distanceTextView.setText(distance);
            }

            if (intent.hasExtra("rating"))
            {
                this.rating = bundle.getFloat("rating");
                ratingBar.setRating(this.rating);
            }
        }
    }

    public void onRateBtnClick(View view)
    {
        Intent intent = new Intent(this, PlaygroundCommentsScreen.class);
        intent.putExtra("rating", this.rating);
        startActivity(intent);
    }

    public void onMapBtnClick(View view) {
        Uri intentUri = Uri.parse("geo:0,0?q=" + Uri.encode(this.address));
        Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

    public void onReportBtnClick(View view) {
        Intent intent = new Intent(this, ReportScreen.class);
        startActivity(intent);
    }
}
