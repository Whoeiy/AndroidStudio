package com.finalproject.starbucksordering.user.MenuWithCart;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.SingleFragmentActivity;

public class DrinkListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DrinkListFragment();
    }
}
