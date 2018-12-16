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
    private TextView functionatitiesTextView;
    private ListView functionalitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        setToolbar();
        functionalitiesList = getFunctionalitiesList();

        addressText = (AutoCompleteTextView)findViewById(R.id.address);
        freeEntryCheckBox = (CheckBox)findViewById(R.id.free_entry_checkbox);
        priceFromText = (AutoCompleteTextView)findViewById(R.id.priceFrom);
        priceToText = (AutoCompleteTextView)findViewById(R.id.priceTo);
        ratingFromText = (AutoCompleteTextView)findViewById(R.id.ratingFrom);
        ratingToText = (AutoCompleteTextView)findViewById(R.id.ratingTo);
        functionatitiesTextView = (TextView) findViewById(R.id.functionalities_text_view);

        priceFromText.setOnFocusChangeListener(this);
        priceToText.setOnFocusChangeListener(this);
    }

    private ListView getFunctionalitiesList() {
        List<String> functionalities = getFunctionalities();

        ListView listView = new ListView(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, functionalities);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchScreen.this, "item selected", Toast.LENGTH_LONG).show();
            }
        });

        listView.setAdapter(adapter);

        return listView;
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
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
            String functionalities = functionatitiesTextView.getText().toString();

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

            if (functionalities == getString(R.string.prompt_functionalities)) functionalities = "";

            Log.e("e", address);
            Log.e("e", Double.toString(priceFrom));
            Log.e("e", Double.toString(priceTo));
            Log.e("e", Double.toString(ratingFrom));
            Log.e("e", Double.toString(ratingTo));
            Log.e("e", functionalities);

            Intent backIntent = new Intent(this, MainScreen.class);
            backIntent.putExtra("address", address);
            backIntent.putExtra("priceFrom", priceFrom);
            backIntent.putExtra("priceTo", priceTo);
            backIntent.putExtra("ratingFrom", ratingFrom);
            backIntent.putExtra("ratingTo", ratingTo);
            backIntent.putExtra("functionalities", functionalities);
            setResult(Activity.RESULT_OK, backIntent);
            finish();
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)view;
        String text = autoCompleteTextView.getText().toString();
        if (!hasFocus && !TextUtils.isEmpty(text)) {
            freeEntryCheckBox.setChecked(false);
        }
    }

    public void onFunctionalitiesListClick(View view)
    {
        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lista funkcjonalności");
        builder.setMessage("Wybierz:")
                .setView(functionalitiesList)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newText = "";
                        functionatitiesTextView.setText(newText);
                        SparseBooleanArray checked = functionalitiesList.getCheckedItemPositions();
                        for (int i = 0; i < checked.size(); i++) {
                            if (checked.valueAt(i)) {
                                newText += functionalitiesList.getItemAtPosition(i) + "; ";
                            }
                        }
                        functionatitiesTextView.setText(newText);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).show();
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
}
