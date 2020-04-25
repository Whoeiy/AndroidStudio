package com.senior.courseselectingsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.senior.courseselectingsystem.model.NewCourse;
import com.senior.courseselectingsystem.utils.HttpUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class TeacherActivity extends AppCompatActivity {
    // layout
    private RelativeLayout mAddCrsLayout;
    private LinearLayout mMyCrsLayout;

    // widget
    private EditText mCrsName;
    private EditText mCrsUpLimit;
    private EditText mCrsDescrip;
    private Button mButton;

    // viarable
    private String name, uplimit, descrip, teachernum;
    private String param, backstr;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mAddCrsLayout.setVisibility(View.VISIBLE);
                    mMyCrsLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    mAddCrsLayout.setVisibility(View.GONE);
                    mMyCrsLayout.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    Handler myhandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            tv_res.setText(msg.obj.toString());
            backstr = msg.obj.toString();
            Toast.makeText(TeacherActivity.this, backstr, Toast.LENGTH_SHORT).show();
            showNormalDialog(backstr);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 导航切换界面
        mAddCrsLayout = (RelativeLayout) findViewById(R.id.layout_th_add_course);
        mMyCrsLayout = (LinearLayout) findViewById(R.id.layout_th_my_course);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // 申请课程界面
        // - 从LoginActivity获得教师编号
        Intent intent = getIntent();
        teachernum = intent.getStringExtra("usernum");

        // - EditText
        mCrsName = (EditText) findViewById(R.id.add_course_name_et);
        mCrsUpLimit = (EditText) findViewById(R.id.add_course_uplimit_et);
        mCrsDescrip = (EditText) findViewById(R.id.add_course_descrip_et);

        // - Button
        mButton = (Button) findViewById(R.id.add_course_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = mCrsName.getText().toString();
                uplimit = mCrsUpLimit.getText().toString();
                descrip = mCrsDescrip.getText().toString();

                NewCourse newCourse = new NewCourse(name, uplimit, descrip, teachernum);
                param = newCourse.toString();
                String tmp = "123";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String response;
                        String strUrl = getResources().getString(R.string.fp_addcourse_url);

                        // 1. 网络访问(Post)
                        try{
                            response = HttpUtils.doPost(strUrl, param);
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
        });

    }

    private void showNormalDialog(String backstr){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(TeacherActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage(backstr);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCrsName.setText(null);
                        mCrsUpLimit.setText(null);
                        mCrsDescrip.setText(null);
                    }
                });
        // 显示
        normalDialog.show();
    }

}
