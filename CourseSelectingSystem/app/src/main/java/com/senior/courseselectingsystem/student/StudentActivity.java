package com.senior.courseselectingsystem.student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.senior.courseselectingsystem.LoginActivity;
import com.senior.courseselectingsystem.R;
import com.senior.courseselectingsystem.TeacherActivity;
import com.senior.courseselectingsystem.model.Course;
import com.senior.courseselectingsystem.model.User;
import com.senior.courseselectingsystem.utils.OkHttpUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StudentActivity extends AppCompatActivity implements CourseFragment.OnListFragmentInteractionListener, CourseDetailFragment.OnFragmentInteractionListener {
    private TextView mTextMessage;
    private Fragment mCourseFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private String stunum, backstr;
    private Course mSelectedCrs;

    // layout
    private RelativeLayout mCrsListLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mCrsListLayout.setVisibility(View.VISIBLE);
//                    mMyCrsLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String response = (String) msg.obj;
            if (response != null) {
                try {
                    //jsonArrayString --> JsonArray
                    ArrayList<Course> courses = new ArrayList<Course>();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject course = (JSONObject) jsonArray.get(i);
                        String num = course.getString("num");
                        String name = course.getString("name");
                        String descrip = course.getString("description");
                        String teacher = course.getString("teacher");
                        int uplimit = course.getInt("uplimit");
                        int chosen = course.getInt("choosen");
                        Course crs = new Course(num, name, descrip, teacher, uplimit, chosen);
                        courses.add(crs);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("courses", courses);

                    CourseFragment courseFragment = new CourseFragment();
                    courseFragment.setArguments(bundle);
                    transaction.add(R.id.container_for_fragment, courseFragment).addToBackStack(null).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(StudentActivity.this, "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler takenhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.obj != null){
                backstr = msg.obj.toString();
            }else{
                Toast.makeText(StudentActivity.this, "网络繁忙！", Toast.LENGTH_SHORT).show();
            }

            Bundle bundle = new Bundle();
            bundle.putString("istaken", backstr);
            bundle.putParcelable("Course", mSelectedCrs);

            CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
            courseDetailFragment.setArguments(bundle);
            transaction.add(R.id.container_for_fragment, courseDetailFragment).addToBackStack(null).commit();
        }
    };

    Handler choosehandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.obj != null){
                backstr = msg.obj.toString();
                Toast.makeText(StudentActivity.this, backstr, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(StudentActivity.this, "网络繁忙！", Toast.LENGTH_SHORT).show();
            }
            getSupportFragmentManager().popBackStack();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 导航切换界面
        mCrsListLayout = (RelativeLayout) findViewById(R.id.container_for_fragment);
//        mMyCrsLayout = (LinearLayout) findViewById(R.id.layout_th_my_course);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // - 从LoginActivity获得学生编号
        Intent intent = getIntent();
        stunum = intent.getStringExtra("usernum");

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        final String strUrl = getResources().getString(R.string.fp_showcourselist_url);
        HashMap<String, String> params = null;

        // 异步GET请求
        try {
            OkHttpUtils.doGetAsync(strUrl, params, myhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListFragmentInteraction(Course item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Course", item);

        String course = item.getNum();
        mSelectedCrs = item;


        final String strUrl = getResources().getString(R.string.fp_iscoursetaken_url);
        final HashMap<String, String> params = new HashMap<>();
        params.put("student", stunum);
        params.put("course", course);

        // 异步GET请求
        try {
            OkHttpUtils.doGetAsync(strUrl, params, takenhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 同步GET请求
        new Thread(new Runnable() {

            String result = null;

            @Override
            public void run() {

                try{
                    result = OkHttpUtils.doGetSync(strUrl, params);
                }catch(Exception e){
                    Toast.makeText(StudentActivity.this, e.toString(), Toast.LENGTH_SHORT);
                }

                Message msg = myhandler.obtainMessage();
                msg.obj = result;
                takenhandler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void onFragmentInteraction(String course) {

        DateFormat dateTimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strBeginDate = dateTimeformat.format(new Date());
//        System.out.println(strBeginDate);  //2017-10-20 11:59:23

        final String strUrl = getResources().getString(R.string.fp_addchoose_url);
        final HashMap<String, String> params = new HashMap<>();
        params.put("course", course);
        params.put("student", stunum);
        params.put("datetime", strBeginDate);

        // 同步POST请求
        new Thread(new Runnable() {

            String result = null;
            @Override
            public void run() {
                try{
                    result = OkHttpUtils.doPostSync(strUrl, params);
                }catch(Exception e){
                    Toast.makeText(StudentActivity.this, e.toString(), Toast.LENGTH_SHORT);
                }

                Message msg = myhandler.obtainMessage();
                msg.obj = result;
                choosehandler.sendMessage(msg);
            }
        }).start();
    }
}
