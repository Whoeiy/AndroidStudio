package com.senior.courseselectingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.senior.courseselectingsystem.model.User;
import com.senior.courseselectingsystem.student.StudentActivity;
import com.senior.courseselectingsystem.utils.OkHttpUtils;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    // 用户登录信息
    private String username;
    private String password;
    private String character;
    private User mUser;

    // 控件
    private EditText mUseridEditText; //Username
    private EditText mPasswordEditText; //Password
    private Button mSubmmitButton;   //login
    private Button mRegisterButton;   //forget

    //
    private String num, pswd;
    private String[] str;

    Handler myhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.obj != null){
                str = msg.obj.toString().split(",");
                Toast.makeText(LoginActivity.this, str[0], Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this, "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            }
            // 跳转
            if(str[0].equals("登录成功")){
                Intent intent = null;
                if(str[1].equals("1")){
                    intent = new Intent(LoginActivity.this, TeacherActivity.class);
                }
                if(str[1].equals("2")){
                    intent = new Intent(LoginActivity.this, StudentActivity.class);
                }
                intent.putExtra("usernum", mUser.getNum());     //将usernum传给对应的activity中
                startActivity(intent);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final String strUrl = getResources().getString(R.string.fp_login_url);

        // EditText - Username
        mUseridEditText = (EditText) findViewById(R.id.welcome_login_interface_account_input);

        // EditText - Password
        mPasswordEditText = (EditText) findViewById(R.id.welcome_login_interface_password_input);

        // Button - Submmit
        mSubmmitButton = (Button) findViewById(R.id.welcome_login_interface_btn_login);
        mSubmmitButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean flag = false;

            // 异步POST请求
            num = mUseridEditText.getText().toString();
            pswd = mPasswordEditText.getText().toString();

            final HashMap<String, String> params = new HashMap<>();
            params.put("num", num);
            params.put("pswd", pswd);
            mUser = new User(num, pswd);

            // 异步网络访问，可以在主线程中调用，执行时会自动开启一个工作线程
            try{
                OkHttpUtils.doPostAsync(strUrl, params, myhandler);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    });


        mRegisterButton = (Button) findViewById(R.id.welcome_login_interface_btn_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
