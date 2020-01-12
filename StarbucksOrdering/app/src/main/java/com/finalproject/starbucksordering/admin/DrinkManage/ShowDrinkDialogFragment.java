package com.finalproject.starbucksordering.admin.DrinkManage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.a.model.DrinkLab;

import java.util.List;

public class ShowDrinkDialogFragment extends DialogFragment {
    private String name;
    private ImageButton mExitImageButton;
    private ImageView mDrinkImageView;
    private TextView mNameTextView;
    private TextView mDetailTextView;
    private Button mCommitButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        name = getArguments().getString("drink_name");
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_admin_show_drink_dialog, null);
        initView(v);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }

    public void initView(View view){
        DrinkLab drinkLab = DrinkLab.get(getActivity());
        List<Drink> drinks = drinkLab.getDrinksByName(name);

        if(drinks.size() != 0){
            mDrinkImageView = (ImageView) view.findViewById(R.id.admin_drink_dialog_img);
            mDrinkImageView.setImageURI(Uri.parse(drinks.get(0).getImage()));

            mNameTextView = (TextView) view.findViewById(R.id.admin_drink_dialog_name);
            mNameTextView.setText(drinks.get(0).getName()+" ¥ "+drinks.get(0).getPrice());

            mDetailTextView = (TextView) view.findViewById(R.id.admin_drink_dialog_detail);
            mDetailTextView.setText(drinks.get(0).getDetail());

            mCommitButton = (Button) view.findViewById(R.id.admin_drink_dialog_btn_commit);
            mCommitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
    }

}
