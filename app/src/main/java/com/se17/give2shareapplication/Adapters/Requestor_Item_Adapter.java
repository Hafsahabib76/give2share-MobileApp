package com.se17.give2shareapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.se17.give2shareapplication.Model.GlideApp;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.R;

import java.util.ArrayList;

public class Requestor_Item_Adapter extends RecyclerView.Adapter<Requestor_Item_Adapter.SharingHolder> {

    private Context context;
    private ArrayList<Items> itemList;
    private View.OnClickListener mOnItemClickListener;


    public Requestor_Item_Adapter(Context context, ArrayList<Items> arrayList) {
        this.context = context;
        this.itemList = arrayList;
    }

    @NonNull
    @Override
    public SharingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.req_row_data, parent, false);
        return new SharingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SharingHolder holder, int position) {
        final Items temp = itemList.get(position);
        String image = "https://webomizer.com/givetoshare/public/" + itemList.get(position).getImage();

        holder.itemTitle.setText(itemList.get(position).getTitle());
        holder.itemCondition.setText(itemList.get(position).getCondition());

        //set Image
        GlideApp.with(context)
                .load(image)
                .override(150, 200)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }



    public class SharingHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle, itemCondition;
        private ImageView image;
        private Button requester_btn;

        public SharingHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemCondition = itemView.findViewById(R.id.itemCondition);
            requester_btn = itemView.findViewById(R.id.request_btn);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);


        }
    }
}
