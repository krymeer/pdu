package czernik.osada.placezabaw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class ProfileScreen extends AppCompatActivity {

    private ImageView profilePhotoImageView;
    private TextView nameTextView;
    private TextView dateTextView;
    private TextView ratingTextView;
    private TextView reportsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        profilePhotoImageView = (ImageView) findViewById(R.id.profile_photo);
        nameTextView = (TextView) findViewById(R.id.user_name_textview);
        dateTextView = (TextView) findViewById(R.id.profile_date_textview);
        ratingTextView = (TextView) findViewById(R.id.number_of_ratings_textview);
        reportsTextView = (TextView) findViewById(R.id.number_of_reports_textview);

        setToolbar();
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setUserPhoto(Bitmap newPhoto) {
        profilePhotoImageView.setImageBitmap(newPhoto);
    }

    public void setUserName(String newName) {
        nameTextView.setText(newName);
    }

    public void setDateOfRegistration(Date newDate) {
        dateTextView.setText(getString(R.string.profile_date) + newDate);
    }

    public void setNumberOfRatings(int ratings) {
        ratingTextView.setText(getString(R.string.number_of_ratings) + Integer.toString(ratings));
    }

    public void setNumberOfReports(int reports) {
        reportsTextView.setText(getString(R.string.number_of_ratings) + Integer.toString(reports));
    }
}