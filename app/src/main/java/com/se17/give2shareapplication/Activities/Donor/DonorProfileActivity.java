package com.se17.give2shareapplication.Activities.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.se17.give2shareapplication.Activities.Authentication.LoginActivity;
import com.se17.give2shareapplication.Adapters.PageAdapter;
import com.se17.give2shareapplication.R;

public class DonorProfileActivity extends AppCompatActivity {

    private Context ctx = DonorProfileActivity.this;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private TabItem tab1, tab2;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private TextView accountName;
    public PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);

        accountName = findViewById(R.id.txtAccountName);
        //for Account Owner Name
        SharedPreferences userPref = getSharedPreferences("user", MODE_PRIVATE);
        String account_Name = userPref.getString("name", "");

        accountName.setText(account_Name);

        //tab coding
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tab1 = (TabItem) findViewById(R.id.SharingsTab);
        tab2 = (TabItem) findViewById(R.id.DonationsTab);
        viewPager = (ViewPager) findViewById(R.id.pager);

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
