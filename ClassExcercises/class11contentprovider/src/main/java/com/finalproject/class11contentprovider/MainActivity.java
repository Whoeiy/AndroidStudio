package com.finalproject.class11contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView1, mTextView2;
    private ListView mListView;
    private List<Phone> mPhoneList = new ArrayList<Phone>();
    private Myadapter mMyadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btnquery);


    }

    public void initViews(){

        mTextView1 = (TextView) findViewById(R.id.text);
    }
}
