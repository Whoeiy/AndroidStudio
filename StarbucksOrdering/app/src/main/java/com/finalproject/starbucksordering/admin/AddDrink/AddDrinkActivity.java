package com.finalproject.starbucksordering.admin.AddDrink;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.SingleFragmentActivity;

public class AddDrinkActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AddDrinkFragment();
    }
}
