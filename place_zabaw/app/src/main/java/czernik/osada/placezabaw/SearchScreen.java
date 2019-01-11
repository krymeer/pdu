package czernik.osada.placezabaw;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class SearchScreen extends AppCompatActivity implements View.OnFocusChangeListener {
    private AutoCompleteTextView addressText;
    private CheckBox freeEntryCheckBox;
    private AutoCompleteTextView priceFromText;
    private AutoCompleteTextView priceToText;
    private AutoCompleteTextView ratingFromText;
    private AutoCompleteTextView ratingToText;
    private TextView featuresTextView;
    private ConstraintLayout priceFieldsLayout;

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
        priceFieldsLayout   = findViewById(R.id.search_priceFields);

        priceFromText.setOnFocusChangeListener(this);
        priceToText.setOnFocusChangeListener(this);

        freeEntryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    priceFromText.setEnabled(false);
                    priceToText.setEnabled(false);
                    priceFieldsLayout.setAlpha(0.5f);
                }
                else
                {
                    priceFromText.setEnabled(true);
                    priceToText.setEnabled(true);
                    priceFieldsLayout.setAlpha(1.0f);
                }
            }
        });
    }

    private void setToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onSearchButtonClick(View view) {
        String address = addressText.getText().toString();

        if (TextUtils.isEmpty(address))
        {
            addressText.setError(getString(R.string.error_field_required));
            addressText.requestFocus();
        }
        else
        {
            double priceFrom    = 0;
            double priceTo      = 0;
            double ratingFrom   = Double.MIN_VALUE;
            double ratingTo     = Double.MAX_VALUE;
            String features     = featuresTextView.getText().toString();

            if (!freeEntryCheckBox.isChecked())
            {
                if (TextUtils.isEmpty(priceFromText.getText().toString()))
                {
                    priceFrom = Double.MIN_VALUE;
                }
                else
                {
                    priceFrom = Double.parseDouble(priceFromText.getText().toString());
                }

                if (TextUtils.isEmpty(priceToText.getText().toString()))
                {
                    priceTo = Double.MAX_VALUE;
                }
                else
                {
                    priceTo = Double.parseDouble(priceToText.getText().toString());
                }
            }

            if (!TextUtils.isEmpty(ratingFromText.getText().toString()))
            {
                ratingFrom = Double.parseDouble(ratingFromText.getText().toString());
            }

            if (!TextUtils.isEmpty(ratingToText.getText().toString()))
            {
                ratingTo = Double.parseDouble(ratingToText.getText().toString());
            }

            if (features.equals(getString(R.string.prompt_functionalities)))
            {
                features = "";
            }

            Intent backIntent = new Intent(this, MainScreen.class);
            backIntent.putExtra("address", address);
            backIntent.putExtra("features", features);
            backIntent.putExtra("priceFrom", priceFrom);
            backIntent.putExtra("priceTo", priceTo);
            backIntent.putExtra("ratingFrom", ratingFrom);
            backIntent.putExtra("ratingTo", ratingTo);

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
        AutoCompleteTextView autoCompleteTextView   = (AutoCompleteTextView) view;
        String text                                 = autoCompleteTextView.getText().toString();

        if (!hasFocus && !TextUtils.isEmpty(text))
        {
            freeEntryCheckBox.setChecked(false);
        }
    }

    public void onFeaturesListClick(View view)
    {
        FunctionalitiesAlertDialogBuilder builder = new FunctionalitiesAlertDialogBuilder(this, featuresTextView);
        builder.show();
    }

    public void onLocationButtonClick(View view) {
        FusedLocator locator = new FusedLocator(this, this, addressText);
        locator.getLocation();
    }
}
