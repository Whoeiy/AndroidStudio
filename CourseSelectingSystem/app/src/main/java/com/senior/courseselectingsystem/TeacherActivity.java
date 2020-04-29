package com.senior.courseselectingsystem;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.senior.courseselectingsystem.model.NewCourse;
import com.senior.courseselectingsystem.service.AddAudioService;
import com.senior.courseselectingsystem.utils.HttpUtils;
import com.senior.courseselectingsystem.utils.OkHttpUtils;
import com.senior.courseselectingsystem.utils.PathUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class TeacherActivity extends AppCompatActivity {
    // layout
    private RelativeLayout mAddCrsLayout;
    private LinearLayout mMyFileLayout;

    // widget
    private EditText mCrsName;
    private EditText mCrsUpLimit;
    private EditText mCrsDescrip;
    private Button mButton, mPlay;

    // viarable
    private String name, uplimit, descrip, teachernum;
    private String param, backstr;

    // uploadfile
    private Button mUpload;
    private TextView mFileName;
    private File file;
    private String path;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mAddCrsLayout.setVisibility(View.VISIBLE);
                    mMyFileLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    mAddCrsLayout.setVisibility(View.GONE);
                    mMyFileLayout.setVisibility(View.VISIBLE);
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

    Handler uploadhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            backstr = msg.obj.toString();
            Toast.makeText(TeacherActivity.this, backstr, Toast.LENGTH_SHORT).show();
            mFileName.setText(file.getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        BottomNavigationView navView = findViewById(R.id.nav_view);

//        checkPermission();

        // 导航切换界面
        mAddCrsLayout = (RelativeLayout) findViewById(R.id.layout_th_add_course);
        mMyFileLayout = (LinearLayout) findViewById(R.id.layout_th_my_file);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Button - Audio
        mPlay = (Button) findViewById(R.id.assist_btn);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherActivity.this, AddAudioService.class);
                startService(intent);
            }
        });

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

        // 上传文件界面
        mFileName = (TextView) findViewById(R.id.tv_file_name);
        mUpload = (Button) findViewById(R.id.upload_file_btn);
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFile(view);
                uploadFile();
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

    public void pickFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        this.startActivityForResult(intent, 999);
    }

    // 获取文件的真实路径
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }

        checkPermission();
        Uri uri = data.getData(); // 获取用户选择文件的URI
        // 获取文件路径
        path = PathUtils.getPath(this, uri);
        Log.w("path",path);
    }

    private void uploadFile() {
        String strUrl = getResources().getString(R.string.fp_uploadfile_url);
        try {
            OkHttpUtils.doUploadPostAsync(strUrl, path, uploadhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkPermission() {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //如果没有写sd卡权限
                isGranted = false;
            }
            if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            Log.i("cbs","isGranted == "+isGranted);
            if (!isGranted) {
                this.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
        }

    }

}
