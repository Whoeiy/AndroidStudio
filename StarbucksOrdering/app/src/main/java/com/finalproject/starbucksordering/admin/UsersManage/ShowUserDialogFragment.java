package com.finalproject.starbucksordering.admin.UsersManage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.User;
import com.finalproject.starbucksordering.a.model.UserLab;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.UserTable;

import java.util.List;

public class ShowUserDialogFragment extends DialogFragment {

    private String username;
    private ImageButton mExitImageButton;
    private TextView mUsernameTextView;
    private TextView mNameTextView;
    private TextView mGenderTextView;
    private TextView mBirthdayTextView;
    private TextView mPhoneTextView;
    private Button mDeleteButton;
    private Button mCommitButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        username = getArguments().getString("username");
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_admin_show_user_dialog, null);
        initView(v);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }

    public void initView(View view){

        mUsernameTextView = (TextView) view.findViewById(R.id.admin_show_user_dialog_username);
        mUsernameTextView.setText(username);

        UserLab userLab = UserLab.get(getActivity());
        User user = userLab.getUser(username);

        mNameTextView = (TextView) view.findViewById(R.id.admin_show_user_dialog_name);
        mNameTextView.setText(user.getName());

        mGenderTextView = (TextView) view.findViewById(R.id.admin_show_user_dialog_gender);
        mGenderTextView.setText(user.getGender());

        mBirthdayTextView = (TextView) view.findViewById(R.id.admin_show_user_dialog_birthday);
        mBirthdayTextView.setText(user.getYear()+"年"+user.getMonth()+"月");

        mPhoneTextView = (TextView) view.findViewById(R.id.admin_show_user_dialog_phone);
        mPhoneTextView.setText(user.getPhone());

        mDeleteButton = (Button) view.findViewById(R.id.admin_show_user_dialog_delete_btn);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flag = "true";
                UserLab userLab = UserLab.get(getActivity());
                userLab.deleteUser(username);
                flag = "false";
                Intent intent = new Intent();
                intent = intent.putExtra("flag", flag);
                getTargetFragment().onActivityResult(0x003, Activity.RESULT_OK, intent);
                dismiss();

            }
        });

        mCommitButton = (Button) view.findViewById(R.id.admin_show_user_dialog_commit_btn);
        mCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }

}
