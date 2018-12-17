package czernik.osada.placezabaw;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FunctionalitiesAlertDialogBuilder extends AlertDialog.Builder {

    private ListView functionalitiesList;
    private TextView externalTextView;

    public FunctionalitiesAlertDialogBuilder(Context context, TextView textView) {
        super(context);
        functionalitiesList = getFunctionalitiesList(context);
        externalTextView = textView;
        init();
    }

    private void init() {
        setTitle("Lista funkcjonalności");
        setMessage("Wybierz:")
                .setView(functionalitiesList)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newText = "";
                        externalTextView.setText(newText);
                        SparseBooleanArray checked = functionalitiesList.getCheckedItemPositions();
                        for (int i = 0; i < checked.size(); i++) {
                            if (checked.valueAt(i)) {
                                newText += functionalitiesList.getItemAtPosition(i) + "; ";
                            }
                        }
                        externalTextView.setText(newText);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
    }

    private ListView getFunctionalitiesList(Context context) {
        List<String> functionalities = getFunctionalities();

        ListView listView = new ListView(context);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, functionalities);

        listView.setAdapter(adapter);

        return listView;
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
