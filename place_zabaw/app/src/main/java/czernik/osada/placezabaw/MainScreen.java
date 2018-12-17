package czernik.osada.placezabaw;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import czernik.osada.placezabaw.PlaygroundListAdapter.PlaygroundSearchListItem;
import czernik.osada.placezabaw.PlaygroundListAdapter.PlaygroundsListAdapter;

public class MainScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView playgroundsListView;
    private Toolbar toolbar;
    private Button searchButton;
    private TextView listHeaderText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        initToolbar();
        initHamburgetMenu();
        initListHeaderText();
        initSearchButton();
        initPlaygroundList();

        listHeaderText.setText(R.string.your_playgrounds);
    }

    private void initListHeaderText() {
        listHeaderText = new TextView(this);
        listHeaderText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        listHeaderText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        listHeaderText.setTextSize(32);
        listHeaderText.setTypeface(listHeaderText.getTypeface(), Typeface.BOLD);
        listHeaderText.setPadding(0, 20, 0, 20);
    }

    private void initSearchButton() {
        searchButton = new Button(this);
        searchButton.setText(R.string.find_your_playground);
        Drawable ico = getResources().getDrawable(R.drawable.playground_search);
        ico.setBounds(250, 0, 350, 100);
        searchButton.setCompoundDrawables(ico, null, null, null);
        searchButton.setCompoundDrawablePadding(20);
        searchButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        searchButton.setTextColor(Color.WHITE);
        searchButton.setTextSize(20f);
        searchButton.setAllCaps(false);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchButtonClick(view);
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initHamburgetMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initPlaygroundList() {
        List<PlaygroundSearchListItem> itemsList = getFavoritePlaygrounds(5);

        PlaygroundsListAdapter playgroundsListAdapter = new PlaygroundsListAdapter(this, itemsList);
        playgroundsListView = (ListView) findViewById(R.id.playgroundsList);
        playgroundsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onPlaygroundListItemClicked(adapterView, view, i, l);
            }
        });
        playgroundsListView.setAdapter(playgroundsListAdapter);

        playgroundsListView.addHeaderView(listHeaderText);
        playgroundsListView.addFooterView(searchButton);
    }

    private void onPlaygroundListItemClicked(AdapterView<?> adapterView, View view, int i, long l) {
        PlaygroundSearchListItem item = (PlaygroundSearchListItem) adapterView.getItemAtPosition(i);
//        Intent intent = new Intent(this, .class);
//        intent.putExtra("address", item.getLocation()); /* TODO - change addess with ID */
//        startActivity(intent);
        Toast.makeText(MainScreen.this, item.getLocation(), Toast.LENGTH_LONG).show();
    }

    private List<PlaygroundSearchListItem> getFavoritePlaygrounds(int count) {
        //TODO add connection to DB
        List<PlaygroundSearchListItem> favorites  = new ArrayList<>(5);
        favorites.add(new PlaygroundSearchListItem("Wrocław, al. Kromera 67", "1,5 km", null, 4.5f));
        favorites.add(new PlaygroundSearchListItem("Wrocław, ul. Nowowiejska 5", "2,5 km", null, 3.5f));
        favorites.add(new PlaygroundSearchListItem("Wrocław, pl. Grunwaldzki 103", "3,7 km", null, 1.5f));
        favorites.add(new PlaygroundSearchListItem("Wrocław, ul. Kościuszki 67", "6,1 km", null, 2.0f));
        favorites.add(new PlaygroundSearchListItem("Wrocław, ul. Drzymały 1", "7,5 km", null, 3.7f));
        favorites.add(new PlaygroundSearchListItem("Wrocław, ul. Drzymały 1", "7,5 km", null, 3.7f));
        favorites.add(new PlaygroundSearchListItem("Wrocław, ul. Drzymały 1", "7,5 km", null, 3.7f));

        return favorites;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            minimizeApp();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileScreen.class);
            startActivity(intent);
        } else if (id == R.id.nav_add_playground) {
            Intent intent = new Intent(this, AddPlaygroundScreen.class);
            startActivity(intent);
        } else if (id == R.id.nav_delete_playground) {
            Toast.makeText(this, "Remove clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_report) {
            Toast.makeText(this, "Report clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_logout) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.logout_alert);
        adb.setIcon(android.R.drawable.ic_dialog_alert);

        adb.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainScreen.this, StartScreen.class);
                startActivity(intent);
            } });

        adb.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            } });

        adb.show();
    }

    private void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    public void onSearchButtonClick(View view) {
        Intent intent = new Intent(this, SearchScreen.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "BACK TO MAIN", Toast.LENGTH_LONG).show();
        if (requestCode == 1) {
            //TODO add connection to DB
            if(resultCode == Activity.RESULT_OK){
                listHeaderText.setText(R.string.search_result_text);
                String address = data.getStringExtra("address");
                double priceFrom = data.getDoubleExtra("priceFrom", 0.0);
                double priceTo = data.getDoubleExtra("priceTo", Double.MAX_VALUE);
                double ratingFrom = data.getDoubleExtra("ratingFrom", 0);
                double ratingTo = data.getDoubleExtra("ratingTo", 5);
                String functionalities = data.getStringExtra("address");
                Toast.makeText(this, "RESULT_OK", Toast.LENGTH_LONG).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "RESULT_CANCELED", Toast.LENGTH_LONG).show();
            }
        }
    }
}
