package com.example.criminalintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//Chapter8 —— CrimeActivity

public class CrimeActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new CrimeFragment();
    }
}
