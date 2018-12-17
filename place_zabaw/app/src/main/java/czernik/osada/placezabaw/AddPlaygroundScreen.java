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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddPlaygroundScreen extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;

    private AutoCompleteTextView addressTextView;
    private Spinner playgroundTypeSpinner;
    private AutoCompleteTextView priceTextView;
    private EditText descriptionTextView;
    private TextView functionatitiesTextView;
    private LinearLayout gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playground_screen);

        setToolbar();
        addressTextView = (AutoCompleteTextView) findViewById(R.id.address);
        playgroundTypeSpinner = (Spinner) findViewById(R.id.playground_type_spinner);
        priceTextView = (AutoCompleteTextView) findViewById(R.id.price_view);
        descriptionTextView = (EditText) findViewById(R.id.playground_description_edit_view);
        functionatitiesTextView = (TextView) findViewById(R.id.functionalities_text_view);
        gallery = (LinearLayout) findViewById(R.id.gallery);

        playgroundTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = adapterView.getItemAtPosition(i).toString();

                if (!selectedOption.equals(getResources().getStringArray(R.array.playground_types_array)[0])) {
                    priceTextView.setVisibility(View.VISIBLE);
                } else {
                    priceTextView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
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
                ImageView newGalleryImageView = getNextImageView();
                newGalleryImageView.setImageBitmap(selectedImage);
                gallery.addView(newGalleryImageView);
                Log.e("result", "try7");
            } catch (FileNotFoundException e) {
                Log.e("result", "catch");
                e.printStackTrace();
                Toast.makeText(AddPlaygroundScreen.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Log.e("result", "else");
            Toast.makeText(AddPlaygroundScreen.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    private List<String> getFunctionalities() {
        List<String> functionalities = new ArrayList<>();
        functionalities.add("Ślizgawka");
        functionalities.add("Huśtawka");
        functionalities.add("Pająk");
        functionalities.add("Piaskownica");
        functionalities.add("Drabinki");
        functionalities.add("Toaleta");
        functionalities.add("Karuzela");
        return functionalities;
    }

    public void onFunctionalitiesListClick(View view) {
        FunctionalitiesAlertDialogBuilder builder = new FunctionalitiesAlertDialogBuilder(this, functionatitiesTextView);
        builder.show();
    }

    public void onSendButtonClick(View view) {
        priceTextView.setError(null);
        addressTextView.setError(null);

        View focusView = null;
        boolean cancel = false;

        if (priceTextView.getVisibility() == View.VISIBLE && TextUtils.isEmpty(priceTextView.getText().toString())) {
            priceTextView.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = priceTextView;
        }

        if (TextUtils.isEmpty(addressTextView.getText().toString())) {
            addressTextView.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = addressTextView;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            addPlayground();
        }
    }

    private void addPlayground() {
        Toast.makeText(this, "DODAJ PLAC ZABAW", Toast.LENGTH_LONG).show();
    }

    public void onAddPhotoClick(View view) {
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
}
