package com.se17.give2shareapplication.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.se17.give2shareapplication.Activities.Donor.DonationsFragment;
import com.se17.give2shareapplication.Activities.Donor.SharingsFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int numoftabs;

    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numoftabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new SharingsFragment();
            case 1:
                return  new DonationsFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
