package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
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
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Drawer
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, myToolBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
            message = "You clicked item 1";
        } else if (id == R.id.mainActivityUser) {
            message = "You clicked on the search";
        } else if (id == R.id.mainActivityHelp) {
            message = "You clicked on help";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String message = null;

        int id = item.getItemId();
        if (id == R.id.mainActivityHome) {
            message = "You clicked item 1";
        } else if (id == R.id.mainActivityUser) {
            message = "You clicked on the search";
        } else if (id == R.id.mainActivityHelp) {
            message = "You clicked on help";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}