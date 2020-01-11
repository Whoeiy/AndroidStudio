package com.finalproject.starbucksordering.admin;

import android.os.Bundle;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.admin.UsersManage.ShowUsersListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdminFuncActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private Fragment mCurrentFragment = null;
    private List<Fragment> fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_func);
        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.admin_functions_interface_nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initFragment();
    }

    private void initFragment() {
        if (fragments != null) {
            fragments.clear();
        } else {
            fragments = new ArrayList<>();
        }
        fragments.add(new ShowUsersListFragment());
//        fragments.add(new FragmentTab2());
//        fragments.add(new FragmentTab3());
//        fragments.add(new FragmentTab4());
    }

    private void showFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            Fragment fragment = fragments.get(position);
            if (null != fragment && mCurrentFragment != fragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                mCurrentFragment = fragment;
                if (!fragment.isAdded()) {
                    transaction.add(R.id.admin_functions_interface, fragment);
                } else {
                    transaction.show(fragment);
                }
                transaction.commit();
            }
        }
    }

}
