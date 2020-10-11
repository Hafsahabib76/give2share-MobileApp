package com.se17.give2shareapplication.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.se17.give2shareapplication.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    private int bgimages[] = {
            R.drawable.green,
            R.drawable.blue,
            R.drawable.purple
    };

    private int images[] = {
            R.drawable.donate,
            R.drawable.share,
            R.drawable.request
    };

    private String titles[] = {
            "DONATE",
            "SHARE",
            "REQUEST"
    };

    private String descs[] = {
            "Donate money for different donation cause, using any type of money transfer services available in Pakistan.",
            "Share new, old and used goods by posting pictures and details about the item.",
            "Send request on the shared items according to your need, without any hesitation and embarrassment."
    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_pager,container,false);

        //init views
        LinearLayout linearLayout = v.findViewById(R.id.bgLinear);
        ImageView imageView = v.findViewById(R.id.imgViewPager);
        TextView txtTitle = v.findViewById(R.id.txtTitleViewPager);
        TextView textDesc = v.findViewById(R.id.txtDescViewPager);

        linearLayout.setBackgroundResource(bgimages[position]);
        imageView.setImageResource(images[position]);
        txtTitle.setText(titles[position]);
        textDesc.setText(descs[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
