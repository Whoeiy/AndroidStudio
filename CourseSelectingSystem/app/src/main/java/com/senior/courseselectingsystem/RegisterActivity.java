package com.senior.courseselectingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.senior.courseselectingsystem.model.NewUser;

public class RegisterActivity extends AppCompatActivity {

    private NewUser mNewUser;
    //
    private EditText mUsernameEditText;
    private EditText mNameEditText;
    private EditText mPasswordEditText;
    private Spinner mGenderSpinner;
    private Spinner mYearSpinner;
    private Spinner mMonthSpinner;
    private EditText mPhoneEditText;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
