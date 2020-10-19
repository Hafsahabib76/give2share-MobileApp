package com.se17.give2shareapplication.Activities.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.se17.give2shareapplication.Activities.Authentication.LoginActivity;
import com.se17.give2shareapplication.R;

public class DonorProfileActivity extends AppCompatActivity {

    private Context ctx = DonorProfileActivity.this;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);


        //for Bottom navigation code
        bottomNavigationView = findViewById(R.id.bottom_nav);
        //setting home as a selection
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        //Item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.nav_home:
                    startActivity(new Intent(ctx, DonorHomeActivity.class));
                    return true;


                case R.id.nav_profile:
                    finish();
                    return true;

            }
            return false;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_logout: {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Do you want to logout?");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    private void logout(){
        startActivity(new Intent(ctx, LoginActivity.class));
    }
}
