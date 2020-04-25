package com.senior.courseselectingsystem.student;

import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private Fragment mCourseFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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
                        String name = course.getString("name");
                        String descrip = course.getString("descrip");
                        String teacher = course.getString("teacher");
                        int uplimit = course.getInt("uplimit");
                        int chosen = course.getInt("chosen");
                        Course crs = new Course(name, descrip, teacher, uplimit, chosen);
                        courses.add(crs);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("courses", courses);

                    CourseFragment courseFragment = new CourseFragment();
                    courseFragment.setArguments(bundle);
                    transaction.add(R.id.container_for_fragment, courseFragment).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(StudentActivity.this, "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();


        boolean flag = false;

        final String strUrl = getResources().getString(R.string.fp_showcourselist_url);
        HashMap<String, String> params = null;

        // 异步GET请求
        try {
            OkHttpUtils.doGetAsync(strUrl, params, myhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
