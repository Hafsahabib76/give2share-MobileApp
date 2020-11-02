package com.se17.give2shareapplication.Activities.Requestor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.se17.give2shareapplication.Adapters.Requestor_Item_Adapter;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestorHomeActivity extends AppCompatActivity {
    private Context ctx = RequestorHomeActivity.this;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;

    private ArrayList<Items> itemList;
    private Requestor_Item_Adapter adapter;
    ArrayList<Items> ApprovedList;
    private LinearLayout noSharingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestor_home);

        init();
    }

    private void init() {
        //check internet connection
        checkConnection();

        noSharingImg = findViewById(R.id.imgLinear);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(RequestorHomeActivity.this, 2));
        recyclerView.setHasFixedSize(true);

        //for Bottom navigation code
        bottomNavigationView = findViewById(R.id.bottom_nav);
        //setting home as a selection
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        //Item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        finish();
                        return true;

                    case R.id.nav_profile:
//                        startActivity(new Intent(ctx, DonorProfileActivity.class));
                        return true;

                }
                return false;
            }
        });

    }

    private void getData() {
        itemList = new ArrayList<>();

        Call<List<Items>> call = ClientApi.apIinterface().getItems();

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()) {

                    itemList = (ArrayList<Items>) response.body();

                    //for showing approve data
                    ApprovedList = new ArrayList();
                    for (Items item : itemList) {

                        //for approved
                        if (item.getStatus().equals("approve")) {
                            ApprovedList.add(item);
                        }

                    }

                    adapter = new Requestor_Item_Adapter(ctx, ApprovedList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(onItemClickListener);

                    if(ApprovedList.isEmpty()){
                        noSharingImg.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        noSharingImg.setVisibility(View.INVISIBLE);
                    }

                } else {
                    Toast.makeText(ctx, "Error Occured", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(ctx, "Error Occured" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            String title = ApprovedList.get(position).getTitle();
            String image = "https://webomizer.com/givetoshare/public/" + ApprovedList.get(position).getImage();
            String category = ApprovedList.get(position).getProductCategory();
            String condition = ApprovedList.get(position).getCondition();
            String description = ApprovedList.get(position).getDesc();
            String quantity = String.valueOf(ApprovedList.get(position).getQuantity());
            String date = ApprovedList.get(position).getUpdatedAt();

            //intent
            Intent intent = new Intent(ctx, SingleItemActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("image", image);
            intent.putExtra("category", category);
            intent.putExtra("condition", condition);
            intent.putExtra("description", description);
            intent.putExtra("quantity", quantity);
            intent.putExtra("date", date);
            startActivity(intent);


        }
    };

    //check internet connection method
    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null==activeNetwork){

            Toast.makeText(this, "Internet not Available! Please check your connection.", Toast.LENGTH_LONG).show();

        }
    }

}
