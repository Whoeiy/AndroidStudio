package com.example.class2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImageView;
    private TextView mTextView;
    private ImageButton mBackImgBtn, mForwardImgBtn;
    private int mCurrentIndex;

    private Image[] mImageAlbum = new Image[]{
            new Image(R.drawable.desert,R.string.desert), new Image(R.drawable.gongbaojiding, R.string.gbjd),
            new Image(R.drawable.hamburger, R.string.humburger), new Image(R.drawable.hongshaorou, R.string.hsr),
            new Image(R.drawable.malejikuai, R.string.mljk), new Image(R.drawable.opera, R.string.opera),
            new Image(R.drawable.sanwich, R.string.sandwhich), new Image(R.drawable.shuizhurou, R.string.szr),
            new Image(R.drawable.souffle, R.string.souffle), new Image(R.drawable.tiramisu, R.string.tiramisu),
            new Image(R.drawable.yuxiangrousi, R.string.yxrs)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mBackImgBtn.setOnClickListener(this);
        mForwardImgBtn.setOnClickListener(this);
    }

    private void initViews(){
        mImageView = (ImageView) findViewById(R.id.img_view);
        mTextView = (TextView) findViewById(R.id.img_name_text_view);
        mBackImgBtn = (ImageButton) findViewById(R.id.back_imgBtn);
        mForwardImgBtn = (ImageButton) findViewById(R.id.forward_imgBtn);
        mImageView.setImageResource(mImageAlbum[mCurrentIndex].getImg());
        mTextView.setText(mImageAlbum[mCurrentIndex].getImgName());
    }
    
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_imgBtn:
                if (mCurrentIndex == 0){
                    mBackImgBtn.setEnabled(false);
                }
                else{
                    mCurrentIndex--;
                    mBackImgBtn.setEnabled(true);
                    mForwardImgBtn.setEnabled(true);
                }
                mImageView.setImageResource(mImageAlbum[mCurrentIndex].getImg());
                mTextView.setText(mImageAlbum[mCurrentIndex].getImgName());
                break;
            case R.id.forward_imgBtn:
                if(mCurrentIndex == mImageAlbum.length - 1){
                    mForwardImgBtn.setEnabled(false);
                }
                else{
                    mCurrentIndex++;
                    mBackImgBtn.setEnabled(true);
                    mForwardImgBtn.setEnabled(true);
                }
                Log.i("Test","i="+mCurrentIndex);   //?
                mImageView.setImageResource(mImageAlbum[mCurrentIndex].getImg());
                mTextView.setText(mImageAlbum[mCurrentIndex].getImgName());
                break;
        }
    }
}
