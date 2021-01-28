package com.example.tesstapp.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.example.tesstapp.R;
import com.google.android.material.navigation.NavigationView;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MainInterface {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;
    private ProgressBar progressBar;
    private String state = "name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        progressBar = findViewById(R.id.progress_bar);
        init();
    }


    private void init(){
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }else {
                    return false;
                }
            }
            case R.id.name:{
                state = getString(R.string.name);
                break;
            }case R.id.forname:{
                state = getString(R.string.forname);
                break;
            }
            case R.id.middlename:{
                state = getString(R.string.middlename);
                break;
            }
            case R.id.phonenumber:{
                state = getString(R.string.phone_number);
                break;
            }
            case R.id.age:{
                state = getString(R.string.age);
                break;
            }
//            default:
//                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.list_of_users:{
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.nav, true)
                        .build();

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.listUserFragment,
                        null,
                        navOptions);
                break;
            }
            case R.id.add_user:{
                if(isValidDestination(R.id.addUserFragment)){
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.addUserFragment);
                }
                break;
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isValidDestination(int destination){
        return destination!=Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }

    @Override
    public void setVesible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setInvisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public String setStateForSearch() {
        return state;
    }
}