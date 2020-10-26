package com.se17.give2shareapplication.Activities.Donor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.se17.give2shareapplication.Adapters.SharingAdapter;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Constant;
import com.se17.give2shareapplication.Model.GlideApp;
import com.se17.give2shareapplication.Model.ItemGlideApp;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.VISIBLE;
import static android.view.View.generateViewId;

public class SharingsFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<Items> itemList;
    private SharedPreferences preferences;
    private SharingAdapter adapter;
    private SharingAdapter.SharingHolder holder;

    public SharingsFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sharings, container, false);
        init();
        return view;
    }

    private void init() {
        preferences = getContext().getSharedPreferences("user",Context.MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.recyclerAccount);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    private void getData() {
        itemList = new ArrayList<>();

        Call<List<Items>> call = ClientApi.apIinterface().getItems();

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if(response.isSuccessful()){

                    itemList = (ArrayList<Items>) response.body();

                    //for donorID
                    SharedPreferences userPref = getContext().getSharedPreferences("user", MODE_PRIVATE);
                    String donor_id = String.valueOf((userPref.getInt("donor_id", -1)));

                    adapter = new SharingAdapter(getContext(),itemList);
                    recyclerView.setAdapter(adapter);

                }
                else {
                    Toast.makeText(getContext(), "Error Occured" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(getContext(), "Error Occured" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            getData();
        }

        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }



}
