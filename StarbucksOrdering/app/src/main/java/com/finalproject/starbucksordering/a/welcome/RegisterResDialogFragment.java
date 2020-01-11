package com.finalproject.starbucksordering.a.welcome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.finalproject.starbucksordering.R;

public class RegisterResDialogFragment extends DialogFragment {
    public static RegisterResDialogFragment newInstace(){
        RegisterResDialogFragment registerResDialogFragment = new RegisterResDialogFragment();
        return registerResDialogFragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceStste){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("注册成功！");

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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
        return dialog.create();
    }
}
