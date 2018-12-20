package czernik.osada.placezabaw;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlaygroundDetailsScreen extends AppCompatActivity{
    private String address;
    private float rating;
    private String description;
    private String functionalities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_details);
        TextView addressTextView    = findViewById(R.id.playgroundDetails_address);
        TextView distanceTextView   = findViewById(R.id.playgroundDetails_distance);
        TextView playgroundDetailsFeaturesTextView  = findViewById(R.id.playgroundDetails_features);
        TextView playgroundDetailsDescriptionTextView   = findViewById(R.id.playgroundDetails_descriptionView);
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
                distance = bundle.getString("distance") + " km";
                distanceTextView.setText(distance);
            }

            if (intent.hasExtra("rating"))
            {
                this.rating = bundle.getFloat("rating");
                ratingBar.setRating(this.rating);
            }

            if (intent.hasExtra("description"))
            {
                this.description = bundle.getString("description");
                playgroundDetailsDescriptionTextView.setText(description);
            }

            if (intent.hasExtra("functionalities"))
            {
                this.functionalities =  bundle.getString("functionalities");
                this.functionalities = this.functionalities.replace(";", "\n");
                playgroundDetailsFeaturesTextView.setText(functionalities);
            }
        }
    }

    public void onRateBtnClick(View view)
    {
        Intent intent = new Intent(this, PlaygroundCommentsScreen.class);
        intent.putExtra("rating", this.rating);
        intent.putExtra("address", this.address);
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
        intent.putExtra("address", this.address);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Snackbar.make(findViewById(android.R.id.content), R.string.request_sent, Snackbar.LENGTH_LONG).show();
        }
    }
}
