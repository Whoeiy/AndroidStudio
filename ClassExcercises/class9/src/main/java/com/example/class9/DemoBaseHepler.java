package com.example.class9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.class9.DemoDbSchema.DemoTable;

public class DemoBaseHepler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "demoBase.db";

    public DemoBaseHepler(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + DemoTable.NAME + "(" + " _id integer primary key autoincrement, "
                + DemoTable.Cols.ST_ID + ", " + DemoTable.Cols.ST_Name + ", " + DemoTable.Cols.ST_Age + ", " + DemoTable.Cols.ST_Class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}



//cursor一开始指向第一条记录之前
