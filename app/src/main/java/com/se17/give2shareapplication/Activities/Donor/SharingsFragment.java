package com.se17.give2shareapplication.Activities.Donor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.se17.give2shareapplication.Adapters.SharingAdapter;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SharingsFragment extends Fragment{

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<Items> itemList;
    ArrayList<Items> itemsArrayList;
    private SharedPreferences preferences;
    private SharingAdapter adapter;
    private SharingAdapter.SharingHolder holder;
    private SharedPreferences userPref;
    private OnDataPass dataPasser;;
    private LinearLayout noSharingImg;

    public SharingsFragment() {
    }

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

        noSharingImg = view.findViewById(R.id.imgLinear);
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

                    //for showing according to the user ID data
                    itemsArrayList = new ArrayList();
                    for(Items item : itemList) {
                        //for donorID
                        SharedPreferences userPref = getContext().getSharedPreferences("user", MODE_PRIVATE);
                        int donor_id = userPref.getInt("donor_id", -1);

                        if(item.getDonorId().equals(donor_id)) {
                            itemsArrayList.add(item);

                        }
                    }
                    adapter = new SharingAdapter(getActivity(),itemsArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(onItemClickListener);

                    String size = String.valueOf(itemsArrayList.size());
                    Log.d("g2s Size", size);
                    dataPasser.onDataPass(size);

                    if(itemsArrayList.isEmpty()){
                        noSharingImg.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        noSharingImg.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Error Occured" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                //Toast.makeText(getContext(), "Error Occured" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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

                String title = itemsArrayList.get(position).getTitle();
                String image = "https://webomizer.com/givetoshare/public/" + itemsArrayList.get(position).getImage();
                String category = itemsArrayList.get(position).getProductCategory();
                String condition = itemsArrayList.get(position).getCondition();
                String description = itemsArrayList.get(position).getDesc();
                String quantity = String.valueOf(itemsArrayList.get(position).getQuantity());
                String status = itemsArrayList.get(position).getStatus();
                String date = itemsArrayList.get(position).getCreatedAt();

            //intent
                Intent intent = new Intent(getActivity(), SingleSharingActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("image", image);
                intent.putExtra("category", category);
                intent.putExtra("condition", condition);
                intent.putExtra("description", description);
                intent.putExtra("quantity", quantity);
                intent.putExtra("status", status);
                intent.putExtra("date", date);
                startActivity(intent);


        }
    };

    public interface OnDataPass {
        public void onDataPass(String data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
}
