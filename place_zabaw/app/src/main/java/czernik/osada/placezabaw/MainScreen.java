package czernik.osada.placezabaw;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import czernik.osada.placezabaw.PlaygroundListAdapter.PlaygroundSearchListItem;
import czernik.osada.placezabaw.PlaygroundListAdapter.PlaygroundsListAdapter;

public class MainScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView favoritePlaygroundsListView;
    private Toolbar toolbar;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        initToolbar();
        initHamburgetMenu();
        initPlaygroundFavoriteList();
        initFAB();
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

    private void initPlaygroundFavoriteList() {
        List<PlaygroundSearchListItem> itemsList = new ArrayList<>(6);
        itemsList.add(new PlaygroundSearchListItem("Wrocław, al. Kromera 67", "1,5 km", null, 4.5f));
        itemsList.add(new PlaygroundSearchListItem("Wrocław, ul. Nowowiesjka 5", "2,5 km", null, 3.5f));
        itemsList.add(new PlaygroundSearchListItem("Wrocław, pl. Grunwaldzki 103", "3,7 km", null, 1.5f));
        itemsList.add(new PlaygroundSearchListItem("Wrocław, ul. Kościuszki 67", "6,1 km", null, 2.0f));
        itemsList.add(new PlaygroundSearchListItem("Wrocław, ul. Drzymały 1", "7,5 km", null, 3.7f));
        itemsList.add(new PlaygroundSearchListItem("Wrocław, pl. Powstańców Śląskich 1", "10,5 km", null, 4.8f));

        PlaygroundsListAdapter playgroundsListAdapter = new PlaygroundsListAdapter(this, itemsList);
        favoritePlaygroundsListView = (ListView) findViewById(R.id.favoritePlaygroundsList);
        favoritePlaygroundsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlaygroundSearchListItem item = (PlaygroundSearchListItem) adapterView.getItemAtPosition(i);
                Toast.makeText(MainScreen.this, item.getLocation(), Toast.LENGTH_LONG).show();
            }
        });
        favoritePlaygroundsListView.setAdapter(playgroundsListAdapter);
    }

    private void initFAB() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
            Toast.makeText(this, "Search clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Toast.makeText(this, "Profil clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_add_playground) {
            Toast.makeText(this, "Add clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_delete_playground) {
            Toast.makeText(this, "Remove clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_report) {
            Toast.makeText(this, "Report clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "Logout clicked", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    public void onSearchButtonClick(View view) {
        Toast.makeText(this, "Search clicked", Toast.LENGTH_LONG).show();
    }
}
