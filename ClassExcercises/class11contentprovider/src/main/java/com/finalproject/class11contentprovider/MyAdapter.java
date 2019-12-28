package com.finalproject.class11contentprovider;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Phone> mPhoneList;

    public MyAdapter(Context context, List<Phone> list){
        mContext = context;
        mPhoneList = list;
    }

    public void setContent(Context context){
        mContext = context;
    }

    public void
}
