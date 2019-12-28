package com.finalproject.starbucksordering.activity.Menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;


import com.finalproject.starbucksordering.R;

import java.util.List;

public class DrinkDialogFragment extends DialogFragment {
    private ImageButton mImageButton;
    private Button mbtn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_drink_dialog, null);
        initView(v);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }


    public void initView(View view){
        mbtn = view.findViewById(R.id.drink_dialog_btn_add);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

