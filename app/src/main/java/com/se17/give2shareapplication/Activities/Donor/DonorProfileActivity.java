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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.se17.give2shareapplication.Activities.Authentication.LoginActivity;
import com.se17.give2shareapplication.Adapters.PageAdapter;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.R;

import java.util.ArrayList;

public class DonorProfileActivity extends AppCompatActivity implements SharingsFragment.OnDataPass {

    private Context ctx = DonorProfileActivity.this;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private TabItem tab1, tab2;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private TextView accountName, sharingCount;
    public PagerAdapter pagerAdapter;
    private Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        init();
    }

    private void init() {
        //check internet connection
        checkConnection();

        toolbar = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);

        //editProfileBtn
        editProfileBtn = findViewById(R.id.btnEditProfile);
        editProfileBtn.setOnClickListener(v->{
            startActivity(new Intent(ctx, DonorEditProfileActivity.class));
        });

        //sharing counting i.e. total donor sharing
        sharingCount = findViewById(R.id.txtAccountSharingCount);

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

    @Override
    public void onDataPass(String data) {
        sharingCount.setText(data);
    }

    private void logout(){
        startActivity(new Intent(ctx, LoginActivity.class));
    }

    //check internet connection method
    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null==activeNetwork){

            Toast.makeText(this, "Internet not Available! Please check your connection.", Toast.LENGTH_LONG).show();

        }
    }
}
