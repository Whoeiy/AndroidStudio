package com.example.class4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ImageButton mUsrnameImgBtn;
    private ImageButton mGenderImgBtn;
    private ImageButton mBdayImgBtn;
    private ImageButton mPhonenumImgBtn;

    private AlertDialog mUsernameDialog;
    private AlertDialog mGenderDialog;
    private DatePickerDialog mBdayDialog;
    private AlertDialog mPhonenumDialog;

    private TextView mTvres;
    private int mPixch;
    private int mFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mUsrnameImgBtn = (ImageButton) findViewById(R.id.usr_inbtn);
        mGenderImgBtn = (ImageButton) findViewById(R.id.gender_inbtn);
        mBdayImgBtn = (ImageButton) findViewById(R.id.bday_inbtn);
        mPhonenumImgBtn = (ImageButton) findViewById(R.id.pnum_inbtn);
        inputUsrname();
        chooseGender();
        chooseBday();
        inputPhonenum();
    }

    // 用户名
    private void inputUsrname(){

        mUsrnameImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUsrnameDialog();
                displayUsrnameDialog(view);
            }
        });

    }

        // 自定义对话框
    private void createUsrnameDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);//?
        View view = layoutInflater.inflate(R.layout.dialog_usrname,null);
        mTvres = (TextView) findViewById(R.id.usr_input);
        final EditText editText = (EditText) view.findViewById(R.id.usr_dlget);
        mUsernameDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("请输入用户名")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTvres.setText(editText.getText().toString());
                    }
                })
                .create();
    }

    public void displayUsrnameDialog(View view){
        mUsernameDialog.show();
    }

    // 性别
    private void chooseGender(){
        mGenderImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createGenderDialog();
                displayGenderDialog();
            }
        });
    }

        // 单选对话框
    private void createGenderDialog(){
        mTvres = (TextView) findViewById(R.id.gender_input);
        final String[] items = {"male","female"};
        mGenderDialog = new AlertDialog.Builder(this)
                .setTitle("请选择性别")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPixch = i;
                        mFlag = 1;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mFlag == 0){ //如果单选按钮未单击
                            mTvres.setText(items[0]);
                        }
                        else{   //如果单选按钮被单击
                            mTvres.setText(items[mPixch]);
                        }
                    }
                })
                .create();
    }

    public void displayGenderDialog(){
        mGenderDialog.show();
    }

    // 生日
    private void chooseBday(){
        mBdayImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBdayDialog();
                displayBdayDialog();
            }
        });
    }

        // datapickdialog
    private void createBdayDialog(){
        mTvres = (TextView) findViewById(R.id.bday_input);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());   //获取系统日期
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mBdayDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                mTvres.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        },year,month,day);
    }

    private void displayBdayDialog(){
        mBdayDialog.show();
    }

    // 手机号
    private void inputPhonenum(){

        mPhonenumImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPhonenumDialog();
                displayPhonenumDialog(view);
            }
        });

    }

        // 自定义对话框
    private void createPhonenumDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);//?
        View view = layoutInflater.inflate(R.layout.dialog_phonenum,null);
        mTvres = (TextView) findViewById(R.id.phonenum_input);
        final EditText editText = (EditText) view.findViewById(R.id.pnum_dlget);
        mPhonenumDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("请输入手机号")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTvres.setText(editText.getText().toString());
                    }
                })
                .create();
    }

    public void displayPhonenumDialog(View view){
        mPhonenumDialog.show();
    }
}
