package com.finalproject.starbucksordering.a.welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.R;

public class WelcomeFragment extends Fragment {

    private Button mLoginButton;
    private Button mRegisterButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences adminsharedpreference = getActivity().getSharedPreferences("admin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = adminsharedpreference.edit();
        editor.putString("username", "170811000");
        editor.putString("name", "Alex");
        editor.putString("password", "000000");
        editor.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance){
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);


        // Button - Login
        mLoginButton = (Button) v.findViewById(R.id.welcome_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new LoginChooseCharacterFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Button - Register
        mRegisterButton = (Button) v.findViewById(R.id.welcome_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new RegisterFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }
}
