package com.example.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

//Chapter7 —— CrimeActivity

public class CrimeActivityChpt7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //FragmentManager类负责管理fragment并将它们的视图添加到activity的视图层级结构中
        //FragmentManager类具体管理：fragment队列和fragment事务回退栈
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //创建并提交了一个fragment事务
        if(fragment == null){
            fragment = new CrimeFragment();
            //FragmentManager.beginTrasaction()方法创建并返回FragmentTransaction实例
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment) //执行一个fragment添加操作，两个参数(容齐视图资源IDm 新创建的CrimeFragment)
                    .commit();  //提交该事务
        }

    }
}
