package com.example.fooddoor.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fooddoor.fragment.AdminNewOrdersFragment;
import com.example.fooddoor.fragment.AdminOngoingOrdersFragment;
import com.example.fooddoor.fragment.AdminPastOrdersFragment;

public class AdminOrdersPageAdapter extends FragmentStateAdapter {

    public AdminOrdersPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new AdminNewOrdersFragment();

        } else if (position == 1) {
            return new AdminOngoingOrdersFragment();

        } else if (position == 2) {
            return new AdminPastOrdersFragment();
        }

        return new AdminOngoingOrdersFragment();
    }

    @Override
    public int getItemCount() {
        return 3; // 3 tabs for admin
    }
}
