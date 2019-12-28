package com.finalproject.starbucksordering.activity.Menu;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.SingleFragmentActivity;

public class DrinkListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DrinkListFragment();
    }
}
