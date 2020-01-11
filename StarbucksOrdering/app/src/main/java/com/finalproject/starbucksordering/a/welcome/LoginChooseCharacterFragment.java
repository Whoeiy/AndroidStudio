package com.finalproject.starbucksordering.a.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.R;

public class LoginChooseCharacterFragment extends Fragment {

    private Button mUserButton;
    private Button mAdminButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance){
        View v = inflater.inflate(R.layout.fragment_login_choose_character, container, false);

        // Button - user
        mUserButton = (Button) v.findViewById(R.id.welcome_login_user);
        mUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                Bundle bundle = new Bundle();
                bundle.putString("character", "user");
                loginFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, loginFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Button - work
        mAdminButton = (Button) v.findViewById(R.id.welcome_login_admin);
        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                Bundle bundle = new Bundle();
                bundle.putString("character", "admin");
                loginFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, loginFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });



        return v;
    }


}
