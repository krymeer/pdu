package czernik.osada.placezabaw;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends AppCompatActivity implements View.OnFocusChangeListener {

    private AutoCompleteTextView addressText;
    private CheckBox freeEntryCheckBox;
    private AutoCompleteTextView priceFromText;
    private AutoCompleteTextView priceToText;
    private AutoCompleteTextView ratingFromText;
    private AutoCompleteTextView ratingToText;
    private TextView featuresTextView;
    private Locator locator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        setToolbar();

        addressText         = findViewById(R.id.search_searchField_address);
        freeEntryCheckBox   = findViewById(R.id.search_priceFields_freeEntryCheckbox);
        priceFromText       = findViewById(R.id.search_priceFields_priceFrom);
        priceToText         = findViewById(R.id.search_priceFields_priceTo);
        ratingFromText      = findViewById(R.id.search_ratingField_ratingFrom);
        ratingToText        = findViewById(R.id.search_ratingField_ratingTo);
        featuresTextView    = findViewById(R.id.search_featuresList);

        priceFromText.setOnFocusChangeListener(this);
        priceToText.setOnFocusChangeListener(this);
    }

    private void setToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onSearchButtonClick(View view) {
        String address = addressText.getText().toString();

        if (TextUtils.isEmpty(address)) {
            addressText.setError(getString(R.string.error_field_required));
            addressText.requestFocus();
        }
        else {
            double priceFrom = 0;
            double priceTo = 0;
            double ratingFrom = Double.MIN_VALUE;
            double ratingTo = Double.MAX_VALUE;
            String features = featuresTextView.getText().toString();

            if (!freeEntryCheckBox.isChecked()) {
                if (TextUtils.isEmpty(priceFromText.getText().toString()))
                    priceFrom = Double.MIN_VALUE;
                else priceFrom = Double.parseDouble(priceFromText.getText().toString());

                if (TextUtils.isEmpty(priceToText.getText().toString()))
                    priceTo = Double.MAX_VALUE;
                else priceTo = Double.parseDouble(priceToText.getText().toString());
            }

            if (!TextUtils.isEmpty(ratingFromText.getText().toString()))
                ratingFrom = Double.parseDouble(ratingFromText.getText().toString());
            if (!TextUtils.isEmpty(ratingToText.getText().toString()))
                ratingTo = Double.parseDouble(ratingToText.getText().toString());

            if (features == getString(R.string.prompt_functionalities)) features = "";

            Intent backIntent = new Intent(this, MainScreen.class);
            backIntent.putExtra("address", address);
            backIntent.putExtra("priceFrom", priceFrom);
            backIntent.putExtra("priceTo", priceTo);
            backIntent.putExtra("ratingFrom", ratingFrom);
            backIntent.putExtra("ratingTo", ratingTo);
            backIntent.putExtra("functionalities", features);
            Log.e("set result", "result");
            setResult(Activity.RESULT_OK, backIntent);
            Log.e("set result", "result2");
            finish();
            Log.e("set result", "result3");
        }
        Log.e("set result", "result4");
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)view;
        String text = autoCompleteTextView.getText().toString();
        if (!hasFocus && !TextUtils.isEmpty(text)) {
            freeEntryCheckBox.setChecked(false);
        }
    }

    public void onFeaturesListClick(View view)
    {
        FunctionalitiesAlertDialogBuilder builder = new FunctionalitiesAlertDialogBuilder(this, featuresTextView);
        builder.show();
    }

    public void onLocationButtonClick(View view) {
        locator = new Locator(this, this, addressText);
        locator.getLocation();

    }
}
