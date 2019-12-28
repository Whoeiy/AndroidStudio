package com.example.class1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mLoggin;
    private Button mCancel;
    private EditText mUsrname;
    private EditText mPassword;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResult = (TextView) findViewById(R.id.result);
        mLoggin = (Button) findViewById(R.id.btn_loggin);
        mLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsrname = (EditText) findViewById(R.id.usr_input);
                mPassword = (EditText) findViewById(R.id.pswd_input);
                checkInfo(mUsrname, mPassword);
            }
        });

        mCancel = (Button) findViewById(R.id.btn_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsrname = (EditText) findViewById(R.id.usr_input);
                mPassword = (EditText) findViewById(R.id.pswd_input);
                mUsrname.setText(null);
                mPassword.setText(null);
                mResult.setText(null);
            }
        });
    }

    private void checkInfo(EditText mUsrname, EditText mPassword){
        String usr = mUsrname.getText().toString();
        String pswd = mPassword.getText().toString();
        if(usr.equals("admin") && pswd.equals("123")){
            mResult.setText(R.string.notice_in);
        }
        else{
            mResult.setText(R.string.notice_for);
        }
    }

}
