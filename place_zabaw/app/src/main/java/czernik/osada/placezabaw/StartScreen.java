package czernik.osada.placezabaw;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import czernik.osada.placezabaw.database.PlaygroundsDataBase;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        if (PlaygroundsDataBase.getInstance().isUserLogged()) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void onBuildButtonClick(View view) {
        Intent intent = new Intent(this, LoginScreen.class);
        intent.putExtra("autoLogin", true);
        startActivity(intent);
    }

    public void onRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            minimizeApp();
        }
    }

    private void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}