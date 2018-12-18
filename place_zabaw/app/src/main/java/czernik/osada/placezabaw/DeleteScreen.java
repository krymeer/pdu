package czernik.osada.placezabaw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DeleteScreen extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;

    private AutoCompleteTextView addressTextView;
    private Spinner deleteReasonSpinner;
    private EditText descriptionTextView;
    private LinearLayout gallery;
    List<Bitmap> images;
    private Locator locator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_screen);

        setToolbar();
        addressTextView = (AutoCompleteTextView) findViewById(R.id.address);
        deleteReasonSpinner = (Spinner) findViewById(R.id.delete_reason_spinner);
        descriptionTextView = (EditText) findViewById(R.id.delete_description_edit_view);
        images = new ArrayList<>();
        gallery = (LinearLayout) findViewById(R.id.gallery);
        locator = new Locator(this, this, addressTextView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void onAddPhotoClick(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    private ImageView getNextImageView() {
        ImageView imageView = new ImageView(this);
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (100 * scale + 0.5f);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(pixels, pixels);
        lp.gravity = Gravity.CENTER;
        imageView.setLayoutParams(lp);
        imageView.setImageResource(R.drawable.ic_menu_gallery);

        return imageView;
    }

    public void onSendButtonClick(View view) {
        addressTextView.setError(null);

        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(addressTextView.getText().toString())) {
            addressTextView.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = addressTextView;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            removePlayground();
        }
    }

    private void removePlayground() {
        Toast.makeText(this, "USUŃ PLAC ZABAW", Toast.LENGTH_LONG).show();
        String address = addressTextView.getText().toString();
        String type = deleteReasonSpinner.getSelectedItem().toString();
        String description = descriptionTextView.getText().toString();
        //images - list of bitmaps
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Log.e("result", "wchodze");
            try {
                Log.e("result", "try");
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                images.add(selectedImage);
                ImageView newGalleryImageView = getNextImageView();
                newGalleryImageView.setImageBitmap(selectedImage);
                gallery.addView(newGalleryImageView);
                Log.e("result", "try7");
            } catch (FileNotFoundException e) {
                Log.e("result", "catch");
                e.printStackTrace();
                Toast.makeText(DeleteScreen.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Log.e("result", "else");
            Toast.makeText(DeleteScreen.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public void onLocationButtonClick(View view) {
        locator.getLocation();
    }
}
