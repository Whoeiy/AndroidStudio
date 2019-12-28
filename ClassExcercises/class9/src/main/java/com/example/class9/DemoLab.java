package com.example.class9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DemoLab {
    private static DemoLab mDemoLab;

//    private List<Demo> mDemos; *
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DemoLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DemoBaseHepler(mContext)
                .getReadableDatabase();
//        mDemos = new ArrayList<>(); *
    }
}
