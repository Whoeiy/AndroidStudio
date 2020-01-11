package com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.finalproject.starbucksordering.a.model.Type;
import com.finalproject.starbucksordering.a.model.User;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.DrinkTable;

import java.util.UUID;

import static com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.*;


public class DrinkBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "starbucksOrderingBase.db";

    public DrinkBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 14.2 创建数据表
        // 饮品类别表
        db.execSQL("create table " + DrinkTypeTable.TNAME + "(" +
                "_id integer primary key autoincrement, " +
                DrinkTypeTable.Cols.TYPE +
                ")"
        );


        db.execSQL("insert into " + DrinkTypeTable.TNAME + " values(null, '浓缩咖啡')");
        db.execSQL("insert into " + DrinkTypeTable.TNAME + " values(null, '星冰乐')");
        db.execSQL("insert into " + DrinkTypeTable.TNAME + " values(null, '茶瓦纳')");
        db.execSQL("insert into " + DrinkTypeTable.TNAME + " values(null, '冰淇淋')");
        db.execSQL("insert into " + DrinkTypeTable.TNAME + " values(null, '玩味特调')");


        db.execSQL("create table " + DrinkTable.TNAME + "(" +
                "_id integer primary key autoincrement, " +
                DrinkTable.Cols.UUID + ", " +
                DrinkTable.Cols.NAME + ", " +
                DrinkTable.Cols.TYPE + ", " +
                DrinkTable.Cols.HOT + "," +
                DrinkTable.Cols.PRICE + "," +
                DrinkTable.Cols.DETAIL + "," +
                DrinkTable.Cols.IMAGE +
                ")"
        );

        db.execSQL("create table " + UserTable.TNAME + "("+
                        "_id integer primary key autoincrement, " +
                        UserTable.Cols.USERNAME + ", " +
                        UserTable.Cols.NAME + ", " +
                        UserTable.Cols.PASSWORD + ", " +
                        UserTable.Cols.GENDER + ", " +
                        UserTable.Cols.YEAR + ", " +
                        UserTable.Cols.MONTH + ", " +
                        UserTable.Cols.PHONE +
                        ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
