package com.example.criminalintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    // 2.
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    // 1. 实现fragment声明周期方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    /**
     * 实例化fragment视图的布局，然后将实例化的View返回给托管activity
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        //通过调用LayoutInflater.inflate()方法传入布局资源ID生成的。
        //第二个参数是视图的父视图，通常需要父视图来正确配置组建。
        //第三个参数告诉布局生成器是否将生成的视图添加给父视图（这里传入了false参数，因为要用代码添加视图）。

        // 2. 在fragment中实例化组件
        // EditText
        mTitleField = (EditText) v.findViewById(R.id.crime_title); //调用View.findViewById(Int)方法
        mTitleField.addTextChangedListener(new TextWatcher() {  //监听方法
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {//CharSequence 代表用户输入
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        //Button
        mDateButton = (Button) v.findViewById(R.id.crime_data);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false); //禁用Button按钮

        //Checkbox
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });



        return v;
    }

//    // 14.4 Crime数据刷新
//    @Override
//    public void onPause(){
//        super.onPause();
//        CrimeLab.get(getActivity())
//                .updaterCrime(mCrime);
//    }
}
