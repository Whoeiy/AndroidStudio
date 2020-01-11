package com.finalproject.starbucksordering.a.welcome;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.SingleFragmentActivity;

public class WelcomeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new WelcomeFragment();
    }
}
