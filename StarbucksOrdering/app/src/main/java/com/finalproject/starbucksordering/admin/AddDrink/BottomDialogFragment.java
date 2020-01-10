package com.finalproject.starbucksordering.admin.AddDrink;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.finalproject.starbucksordering.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;

public class BottomDialogFragment extends DialogFragment {

    private String uristr;
    private View mView;
    private Window mWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = inflater.inflate(R.layout.fragment_admin_add_drink_bottom_dialog, null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextView camera = (TextView) view.findViewById(R.id.admin_add_drink_btmdialog_camera);
        TextView album = (TextView) view.findViewById(R.id.addmin_add_drink_btmdialog_from_album);

        // 选择照相
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //null
            }
        });

        // 选择相册
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageAlbum();
            }
        });


    }

    @Override
    public void onStart(){
        super.onStart();

        mWindow = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        mWindow.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置动画
        //mWindow.setWindowAnimations(R.style.bottomDialog);

        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.width = getResources().getDisplayMetrics().widthPixels;
        mWindow.setAttributes(params);
    }

    // 选择图片
    public void pickImageAlbum(){
        Intent intent = new Intent();
        //允许用户获取多个已存在的文件夹
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        //获取可以使用打开的URI
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //指定数据类型
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,0x001);
    }

    // 获取选择图片的uri
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED){
            return;
        }
        Bitmap bm=null;
        ContentResolver resolver = getActivity().getContentResolver();
        if(requestCode == 0x001){
            Uri uri = data.getData();
            Log.i("test","uri"+uri);
//            try {
//                bm= MediaStore.Images.Media.getBitmap(resolver,uri);
//                Log.i("test",bm+"");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                bm= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            mDrinkImg.setImageBitmap(bm);
//            mDrinkImg.setImageURI(uri);

            uristr = uri.toString();

            giveuri(uristr);

        }
    }

    // 将uri以字符串的形式传给AddDrinkFragment.java
    public void giveuri(String uristr){
        Intent intent = new Intent();
        intent = intent.putExtra("photo", uristr);
        getTargetFragment().onActivityResult(0x002, Activity.RESULT_OK, intent);
    }

}
