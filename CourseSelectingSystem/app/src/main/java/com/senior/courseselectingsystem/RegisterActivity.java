package com.senior.courseselectingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.senior.courseselectingsystem.model.NewUser;
import com.senior.courseselectingsystem.utils.HttpUtils;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private NewUser newUser;
    //
    private EditText mUsernameEditText;
    private EditText mNameEditText;
    private EditText mPasswordEditText;
    private Spinner mIdentitySpinner;
    private Spinner mYearSpinner;
    private Spinner mClassesSpinner;
    private EditText mPhoneEditText;
    private Button mAddButton;
    //
    private String num, name, pswd, contact, param;
    private String identity = "3";
    private int cls;
    private String backstr;

    Handler myhandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            tv_res.setText(msg.obj.toString());
            backstr = msg.obj.toString();
            Toast.makeText(RegisterActivity.this, backstr, Toast.LENGTH_SHORT).show();
            if(backstr.equals("注册成功\n")){
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // EditText - Num
        mUsernameEditText = (EditText) findViewById(R.id.welcome_register_interface_usernum_et);
        mUsernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                String teststr = editable.toString();
//                if (teststr.length() == 0) {
//                    Toast.makeText(RegisterActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (teststr.length() != 7) {
//                    Toast.makeText(RegisterActivity.this, "用户名共有7位，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                } else if(!strIsNumber(editable)){
//                    Toast.makeText(RegisterActivity.this, "用户名不能包含非数字字符，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                }
//                else {
                    num = editable.toString();
//                }
            }
        });

        // EditText - Name
        mNameEditText = (EditText) findViewById(R.id.welcome_register_interface_name_et);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String teststr = editable.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "姓名不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    name = editable.toString();
                }
            }
        });

        // EditText - Password
        mPasswordEditText = (EditText) findViewById(R.id.welcome_register_interface_password_et);
        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                String teststr = editable.toString();
//                if (teststr.length() == 0) {
//                    Toast.makeText(RegisterActivity.this, "密码不能为空，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (teststr.length() < 6 || teststr.length() >18) {
//                    Toast.makeText(RegisterActivity.this, "密码不能少于6位，多于18位，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                } else {
                    pswd = editable.toString();
//                }
            }
        });

        // Spinner - Identity
        mIdentitySpinner = (Spinner) findViewById(R.id.welcome_register_interface_identity_spinner);

        String[] idenarr = {"教师","学生"};

        ArrayAdapter<String> identityAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                R.layout.support_simple_spinner_dropdown_item, idenarr);
        mIdentitySpinner.setAdapter(identityAdapter);
        mIdentitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                identity = i + "";
                Log.i("identity", identity);

                // Spinner - Class
                if(identity.equals("1")){

                    mClassesSpinner = (Spinner) findViewById(R.id.welcome_register_interface_class_spinner);
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.welcome_register_interface_rl_cls);
                    relativeLayout.setVisibility(View.VISIBLE);

                    String[] clsarr = {"2012自动化1班","2012自动化2班","2012自动化3班"};

                    ArrayAdapter<String> clsAdapter = new ArrayAdapter<String>(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item, clsarr);
                    mClassesSpinner.setAdapter(clsAdapter);
                    mClassesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            cls = i + 1;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        // EditText - Phone
        mPhoneEditText = (EditText) findViewById(R.id.welcome_register_interface_phone_et);
        mPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                String teststr = editable.toString();
//                if (teststr.length() == 0) {
//                    Toast.makeText(RegisterActivity.this, "电话号码不能为空，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (teststr.length() != 11) {
//                    Toast.makeText(RegisterActivity.this, "电话号码共有11位，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                } else if(!strIsNumber(editable)){
//                    Toast.makeText(RegisterActivity.this, "电话号码不能包含非数字字符，请重新输入！", Toast.LENGTH_SHORT)
//                            .show();
//                }
//                else {
                    contact = editable.toString();
//                }
            }
        });

        //Button - register
        mAddButton = (Button) findViewById(R.id.welcome_register_btn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num == null || name == null || pswd == null
                        || identity == null || contact == null){
                    Toast.makeText(RegisterActivity.this, "信息填写不全，不能为空！", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(identity.equals("0")){
                        newUser = new NewUser(num, name, pswd, identity, 0, contact);
                    }
                    else{
                        newUser = new NewUser(num, name, pswd, identity, cls, contact);

                    }

                    param = newUser.toString();
                    String tmp = "123";

                    // 直接实例化一个Thread对象
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String response;
                            String strUrl = getResources().getString(R.string.fp_register_url);

                            // 网络访问
                            try{
                                // 不要把所有的操作都放在Activity中，当逻辑比较复杂时还是应该遵循MVC设计模式
                                response = HttpUtils.doGet(strUrl, param);
                                // 将网络访问定义为一个方法，在Activity中调用
                            }catch(Exception e){
                                e.printStackTrace();
                                response = "网络请求失败，请稍后再试! ";
                            }

                            // 2. 将服务器相应结果返回主线程
                            Message message = myhandler.obtainMessage();
                            message.obj = response;
                            myhandler.sendMessage(message);
                        }
                    }).start();
                }
            }
        });

    }

    public boolean strIsNumber(CharSequence s){

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(s);
        boolean res = matcher.matches();
        return res;
    }
}
