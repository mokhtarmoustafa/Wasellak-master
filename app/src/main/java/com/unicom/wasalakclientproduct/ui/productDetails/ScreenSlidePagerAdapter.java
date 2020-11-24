package com.unicom.wasalakclientproduct.ui.productDetails;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.unicom.wasalakclientproduct.ui.productDetails.ProductOverviewFragment;
import com.unicom.wasalakclientproduct.ui.productDetails.ProductRatingFragment;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 3;

    public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0 :return new ProductOverviewFragment();
                case 1:return  new ProductSpecificationsFragment();
                case 2 :return  new ProductRatingFragment();
            }
            return new ProductOverviewFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }


}