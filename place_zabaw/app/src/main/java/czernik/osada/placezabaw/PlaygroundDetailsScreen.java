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
import android.widget.Toast;

import czernik.osada.placezabaw.database.PlaygroundTable;
import czernik.osada.placezabaw.database.PlaygroundsDataBase;

public class PlaygroundDetailsScreen extends AppCompatActivity{
    private int id;
    private String town;
    private String street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_details);
        TextView descriptionTextView    = findViewById(R.id.playgroundDetails_descriptionView);
        TextView distanceTextView       = findViewById(R.id.playgroundDetails_distance);
        TextView featuresTextView       = findViewById(R.id.playgroundDetails_features);
        TextView streetTextView         = findViewById(R.id.playgroundComments_street);
        TextView townTextView           = findViewById(R.id.playgroundComments_town);
        RatingBar ratingBar             = findViewById(R.id.playgroundDetails_rating);
        Intent intent                   = getIntent();
        Bundle bundle                   = intent.getExtras();

        if (bundle != null)
        {
            if (intent.hasExtra("playgroundId"))
            {
                this.id                 = bundle.getInt("playgroundId");
                PlaygroundTable table   = PlaygroundsDataBase.getInstance().getPlayground(this.id);

                if (table != null)
                {
                    String description  = table.getDescription();
                    String distance     = table.getDistance() + " km";
                    String features     = table.getFeatures().toString().replaceAll("[\\[\\]]", "").replaceAll("(;\\s*|,\\s*)", "\n");
                    String street       = table.getStreet();
                    String town         = table.getTown();
                    float rating        = (float) table.getRating();
                    this.town           = town;
                    this.street         = street;

                    distanceTextView.setText(distance);
                    descriptionTextView.setText(description);
                    featuresTextView.setText(features);
                    streetTextView.setText(street);
                    townTextView.setText(town);
                    ratingBar.setRating(rating);
                }
            }
        }
        else
        {
            Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_LONG).show();
        }
    }

    public void onRateBtnClick(View view)
    {
        Intent intent = new Intent(this, PlaygroundCommentsScreen.class);
        intent.putExtra("playgroundId", this.id);
        intent.putExtra("street", this.street);
        intent.putExtra("town", this.town);
        startActivity(intent);
    }

    public void onMapBtnClick(View view) {
        String address  = this.town + ", " + this.street;
        Uri intentUri   = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent intent   = new Intent(Intent.ACTION_VIEW, intentUri);
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

    public void onReportBtnClick(View view) {
        Intent intent   = new Intent(this, ReportScreen.class);
        String address  = this.town + ", " + this.street;
        intent.putExtra("playgroundId", this.id);
        intent.putExtra("address", address);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Snackbar.make(findViewById(android.R.id.content), R.string.request_sent, Snackbar.LENGTH_LONG).show();
        }
    }
}
