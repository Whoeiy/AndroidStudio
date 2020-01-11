package com.finalproject.starbucksordering.a.welcome;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.User;
import com.finalproject.starbucksordering.a.model.UserLab;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    private User mUser;
    private UserLab mUserLab;
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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mUser = new User();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_register_interface, container, false);


        // EditText - Username
        mUsernameEditText = (EditText) v.findViewById(R.id.welcome_register_interface_username_et);
        mUsernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String teststr = charSequence.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(getActivity(), "用户名不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if (teststr.length() != 9) {
                    Toast.makeText(getActivity(), "用户名共有9位，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if(!strIsNumber(charSequence)){
                    Toast.makeText(getActivity(), "用户名不能包含非数字字符，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    mUser.setUsername(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // EditText - Name
        mNameEditText = (EditText) v.findViewById(R.id.welcome_register_interface_name_et);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String teststr = charSequence.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(getActivity(), "姓名不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mUser.setName(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // EditText - Password
        mPasswordEditText = (EditText) v.findViewById(R.id.welcome_register_interface_password_et);
        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String teststr = charSequence.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(getActivity(), "密码不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if (teststr.length() < 6 || teststr.length() >18) {
                    Toast.makeText(getActivity(), "密码不能少于6位，多于18位，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mUser.setPassword(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Spinner - Gender
        mGenderSpinner = (Spinner) v.findViewById(R.id.welcome_register_interface_gender_spinner);

        String[] genderarr = {"男","女"};

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, genderarr);
        mGenderSpinner.setAdapter(genderAdapter);
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String gender = adapterView.getItemAtPosition(i).toString();
                mUser.setGender(gender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner - Year
        mYearSpinner = (Spinner) v.findViewById(R.id.welcome_register_interface_birthday_year_spinner);

        Calendar calendar = Calendar.getInstance();
        String yearstr = "";
        int year_now = calendar.get(Calendar.YEAR);
        for (int i = year_now; i >= 1920; i--){
            yearstr = yearstr + i +",";

        }
        String[] yeararr = yearstr.split(",");

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, yeararr);
        mYearSpinner.setAdapter(yearAdapter);
        mYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String year = adapterView.getItemAtPosition(i).toString();
                mUser.setYear(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner - Month
        mMonthSpinner = (Spinner) v.findViewById(R.id.welcome_register_interface_birthday_month_spinner);

        String[] montharr = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, montharr);
        mMonthSpinner.setAdapter(monthAdapter);
        mMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String month = adapterView.getItemAtPosition(i).toString();
                mUser.setMonth(month);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // EditText - Phone
        mPhoneEditText = (EditText) v.findViewById(R.id.welcome_register_interface_phone_et);
        mPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String teststr = charSequence.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(getActivity(), "电话号码不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if (teststr.length() != 11) {
                    Toast.makeText(getActivity(), "电话号码共有11位，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if(!strIsNumber(charSequence)){
                    Toast.makeText(getActivity(), "电话号码不能包含非数字字符，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    mUser.setPhone(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Button - register
        mAddButton = (Button) v.findViewById(R.id.welcome_register_btn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUser.getUsername() == null || mUser.getName() == null || mUser.getPassword() == null
                        || mUser.getGender() == null || mUser.getYear() == null || mUser.getMonth() == null
                        || mUser.getPassword() == null){
                    Toast.makeText(getActivity(), "信息填写不全，不能为空！", Toast.LENGTH_SHORT).show();
                }
                else{
                    mUserLab = UserLab.get(getActivity());
                    mUserLab.addUser(mUser);
                    RegisterResDialogFragment dialog = RegisterResDialogFragment.newInstace();
                    dialog.show(getActivity().getSupportFragmentManager(), "AddResultDialog");
                }
            }
        });


        return v;
    }


    public boolean strIsNumber(CharSequence s){

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(s);
        boolean res = matcher.matches();
        return res;
    }
}
