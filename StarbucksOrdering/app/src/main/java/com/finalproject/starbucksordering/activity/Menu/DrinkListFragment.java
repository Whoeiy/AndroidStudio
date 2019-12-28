package com.finalproject.starbucksordering.activity.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.finalproject.starbucksordering.R;

public class DrinkListFragment extends Fragment {
    private static final String DIALOG = "wtf";


    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink_list, container, false);
        mButton = view.findViewById(R.id.drink_list_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DrinkDialogFragment df = new DrinkDialogFragment();
                df.show(manager, DIALOG);
            }
        });

        return view;
    }
}
