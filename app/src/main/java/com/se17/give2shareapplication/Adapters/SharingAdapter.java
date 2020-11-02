package com.se17.give2shareapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.se17.give2shareapplication.Model.GlideApp;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.R;

import java.util.ArrayList;

public class SharingAdapter extends RecyclerView.Adapter<SharingAdapter.SharingHolder> {

    private Context context;
    private ArrayList<Items> itemList;
    private View.OnClickListener mOnItemClickListener;

    public SharingAdapter(Context context, ArrayList<Items> arrayList) {
        this.context = context;
        this.itemList = arrayList;
    }

    @NonNull
    @Override
    public SharingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sharings_row_data, parent, false);
        return new SharingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SharingHolder holder, int position) {
        String image = "https://webomizer.com/givetoshare/public/" + itemList.get(position).getImage();


        holder.sharingTitle.setText(itemList.get(position).getTitle());
        holder.sharingStatus.setText(itemList.get(position).getStatus());

        //set Image
        GlideApp.with(context)
                .load(image)
                .override(150, 200)
                .into(holder.sharingImg);


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    public class SharingHolder extends RecyclerView.ViewHolder{

        private TextView sharingTitle, sharingStatus;
        private ImageView sharingImg;

        public SharingHolder(@NonNull View itemView) {
            super(itemView);
            sharingImg = itemView.findViewById(R.id.itemImage);
            sharingTitle = itemView.findViewById(R.id.itemTitle);
            sharingStatus = itemView.findViewById(R.id.sharingStatus);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }

    }



}
