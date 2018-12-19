package czernik.osada.placezabaw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddCommentScreen extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    private TextView addressTextView;
    private LinearLayout gallery;
    List<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment_screen);

        addressTextView = findViewById(R.id.addressTextView);
        gallery = (LinearLayout) findViewById(R.id.gallery);
        images = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            if (intent.hasExtra("address")) {
                String address = bundle.getString("address");
                addressTextView.setText(address);
            }
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
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
                Toast.makeText(AddCommentScreen.this, R.string.something_wrong, Toast.LENGTH_LONG).show();
            }

        }else {
            Log.e("result", "else");
            Toast.makeText(AddCommentScreen.this, R.string.no_image_picked,Toast.LENGTH_LONG).show();
        }
    }

    public void onSendButtonClick(View view) {
        Toast.makeText(this, "OCEN", Toast.LENGTH_LONG).show();
    }
}
