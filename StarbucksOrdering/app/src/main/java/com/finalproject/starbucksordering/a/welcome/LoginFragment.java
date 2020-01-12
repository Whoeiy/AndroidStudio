package com.finalproject.starbucksordering.a.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.User;
import com.finalproject.starbucksordering.a.model.UserLab;
import com.finalproject.starbucksordering.admin.AdminFuncActivity;
import com.finalproject.starbucksordering.user.MenuWithCart.UserFuncActivity;

public class LoginFragment extends Fragment {
    // 用户登录信息
    private String username;
    private String password;
    private String character;

    // 控件
    private TextView mTitleTextView;
    private EditText mUsernameEditText; //Username
    private EditText mPasswordEditText; //Password
    private Button mSubmmitButton;   //login
    private Button mForgetButton;   //forget

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        character  = getArguments().getString("character");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_login_interface, container, false);

        SharedPreferences status = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        if (status.getString("status","").equals("auto")){  //自动登录
            mUsernameEditText = (EditText) v.findViewById(R.id.welcome_login_interface_account_input);
            mUsernameEditText.setText(status.getString("loginusername",""));

            mPasswordEditText = (EditText) v.findViewById(R.id.welcome_login_interface_password_input);
            mPasswordEditText.setText(status.getString("loginpassword",""));

            character = status.getString("logincharacter","");

            mSubmmitButton = (Button) v.findViewById(R.id.welcome_login_interface_btn_login);
            mSubmmitButton.setPressed(true);
        }
        else{
            // Tittle
            mTitleTextView = (TextView) v.findViewById(R.id.welcome_login_interface_title);
            mTitleTextView.setText(character);

            // EditText - Username
            mUsernameEditText = (EditText) v.findViewById(R.id.welcome_login_interface_account_input);
            mUsernameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    username = charSequence.toString();
                    if(username.length() == 0){
                        Toast.makeText(getActivity(), "用户名不能为空！", Toast.LENGTH_SHORT)
                                .show();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            // EditText - Password
            mPasswordEditText = (EditText) v.findViewById(R.id.welcome_login_interface_password_input);
            mPasswordEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    password = charSequence.toString();
                    if(password.length() == 0){
                        Toast.makeText(getActivity(), "密码不能为空！", Toast.LENGTH_SHORT)
                                .show();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            // Button - Submmit
            mSubmmitButton = (Button) v.findViewById(R.id.welcome_login_interface_btn_login);
        }
        mSubmmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;
                if(character.equals("admin")){  // 工作人员
                    SharedPreferences admin = getActivity().getSharedPreferences("admin", Context.MODE_PRIVATE);
                    // 用户名
                    if(username.equals(admin.getString("username",""))){
                        flag = true;
                    }
                    // 密码
                    if(password.equals(admin.getString("password",""))){
                        flag = true;
                    }
                    else{
                        flag = false;
                    }

                    if(flag == false){
                        Toast.makeText(getActivity(), "用户名或密码不正确！", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else{
                        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = login.edit();
                        editor.putString("loginusername", username);
                        editor.putString("loginname", admin.getString("name",""));
                        editor.putString("loginpassword", password);
                        editor.putString("logincharacter", character);
                        editor.putString("loginstatus", "auto");
                        editor.commit();
                        Intent intent = new Intent(getActivity(), AdminFuncActivity.class);
                        startActivity(intent);
                    }
                }

                if (character.equals("user")){
                    UserLab userLab = UserLab.get(getActivity());
                    User user = userLab.getUser(username);
                    if (user.getUsername() == null){
                        Toast.makeText(getActivity(), "该用户不存在，请先注册！", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else if(!password.equals(user.getPassword())){
                        Toast.makeText(getActivity(), "密码不正确，请重新输入！", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else{
                        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = login.edit();
                        editor.putString("loginusername", username);
                        editor.putString("loginname", user.getName());
                        editor.putString("loginpassword", user.getPassword());
                        editor.putString("logincharacter", character);
                        editor.putString("loginstatus", "auto");
                        editor.commit();
                        Intent intent = new Intent(getActivity(), UserFuncActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });



        return v;
    }
}
