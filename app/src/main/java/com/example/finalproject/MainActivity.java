package com.example.finalproject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageButton newsButton;
    ImageButton savedButton;
    ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Drawer
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Get the header view from the navigation drawer
        View headerView = navigationView.getHeaderView(0);
        TextView navLogin = headerView.findViewById(R.id.nav_login);
        TextView navLogout = headerView.findViewById(R.id.nav_logout);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String loggedInUser = prefs.getString("loggedInUser", null);

        // Find the TextView in activity_main.xml to show the login status
        TextView userStatusText = findViewById(R.id.user_status_text); // Ensure this TextView is in your layout

        if (loggedInUser != null) {
            userStatusText.setText("Signed in as " + loggedInUser);
            navLogin.setVisibility(View.GONE); // Hide the login button in the header
            navLogout.setVisibility(View.VISIBLE); // Show the logout button in the header
        } else {
            userStatusText.setText("Not signed in");
            navLogin.setVisibility(View.VISIBLE); // Show the login button in the header
            navLogout.setVisibility(View.GONE); // Hide the logout button in the header
        }

        // Add an OnClickListener to the "Login" button
        navLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Add an OnClickListener to the "Logout" button
        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the stored user information
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("loggedInUser");
                editor.apply();

                // Update the UI
                userStatusText.setText("Not signed in");
                navLogin.setVisibility(View.VISIBLE);
                navLogout.setVisibility(View.GONE);

                Toast.makeText(MainActivity.this, "You have logged out", Toast.LENGTH_SHORT).show();
            }
        });

        newsButton = findViewById(R.id.news_imageButton);
        savedButton = findViewById(R.id.saved_imageButton);
        settingsButton = findViewById(R.id.settings_imageButton);
        newsButton.setOnClickListener(this::handleButtonToggle);
        savedButton.setOnClickListener(this::handleButtonToggle);
        settingsButton.setOnClickListener(this::handleButtonToggle);
    }


    private void handleButtonToggle(View clickedButton){
        Fragment fragment = null;
        int id = clickedButton.getId();
        if (id == R.id.news_imageButton) {
            fragment = new NewsFragment();
        } else if (id == R.id.saved_imageButton) {
            fragment = new SavedFragment();
        } else if (id == R.id.settings_imageButton) {
            fragment = new SettingsFragment();
        }

        if (fragment != null) {
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        //Look at your menu XML file. Put a case for every id in that file:
        int id = item.getItemId();
        if (id == R.id.mainActivityHome) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
            if (currentFragment != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(currentFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.mainActivityUser) {
            message = "You clicked on the search";
        } else if (id == R.id.mainActivityHelp) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.mainhelp_alertdialog_title)
                    .setMessage(R.string.mainhelp_alertdialog_message)
                    .setPositiveButton("Ok, thanks", (click, arg) -> {});
            alertDialogBuilder.create().show();
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String message = null;

        int id = item.getItemId();
        if (id == R.id.mainActivityHome) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
            if (currentFragment != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(currentFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.mainActivityUser) {
            message = "You clicked on the search";
        } else if (id == R.id.mainActivityHelp) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.mainhelp_alertdialog_title)
                    .setMessage(R.string.mainhelp_alertdialog_message)
                    .setPositiveButton("Ok, thanks", (click, arg) -> {});
            alertDialogBuilder.create().show();
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}