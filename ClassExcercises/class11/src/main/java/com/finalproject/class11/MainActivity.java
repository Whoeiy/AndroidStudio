package com.finalproject.class11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private Button mBtn1, mBtn2, mBtn3;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initeViews();

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = readAssetsContent("test.txt");
                mTextView.setText(content);
            }
        });
         mBtn2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, WebActivity.class);
                 startActivity(intent);
             }
         });
         mBtn3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String content = readRawContent(R.raw.ttt);
                 mTextView.setText(content);
             }
         });
    }

    public void initeViews(){
        mBtn1 = (Button) findViewById(R.id.button1);
        mBtn2 = (Button) findViewById(R.id.button2);
        mBtn3 = (Button) findViewById(R.id.button3);
        mTextView = (TextView) findViewById(R.id.text);
    }

    public String readAssetsContent(String filename){
        BufferedReader  reader = null;
        StringBuilder sb = new StringBuilder();
        try{
            InputStream is = this.getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null){
                sb.append(line);
                line = reader.readLine();
            }
            return sb.toString();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String readRawContent(int filename){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        InputStream is = getResources().openRawResource(filename);
        reader = new BufferedReader(new InputStreamReader(is));

        try{
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            return  sb.toString();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
