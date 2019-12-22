//package com.example.criminalintent.database.CrimeDbSchema;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.criminalintent.Crime;
//import com.example.criminalintent.database.CrimeDbSchema.CrimeDbSchema.CrimeTable;
//
//public class CrimeBaseHepler extends SQLiteOpenHelper {
//    private static final int VERSION = 1;
//    private static final String DATABASE_NAME = "crimeBase.db";
//
//    public CrimeBaseHepler(Context context){
//        super(context, DATABASE_NAME, null, VERSION);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // 14.2 创建数据表
//        db.execSQL("create table " + CrimeTable.NAME + "(" +
//                "_id integer primary key autoincrement, " +
//                CrimeTable.Cols.UUID + ", " +
//                CrimeTable.Cols.TITLE + ", " +
//                CrimeTable.Cols.DATE + ", " +
//                CrimeTable.Cols.SOLVED +
//                ")"
//        );
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//}
