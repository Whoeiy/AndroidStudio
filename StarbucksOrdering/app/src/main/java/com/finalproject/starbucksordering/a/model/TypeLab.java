package com.finalproject.starbucksordering.a.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.DrinkBaseHelper;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.DrinkCursorWrapper;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.DrinkTypeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TypeLab {
    private static TypeLab sTypeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TypeLab get(Context context){
        if(sTypeLab == null){
            sTypeLab = new TypeLab(context);
        }
        return sTypeLab;
    }

    private TypeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DrinkBaseHelper(mContext)
                .getWritableDatabase();
    }



    private DrinkCursorWrapper queryTypes(String whereClause, String[] whereArgs){
        Cursor  cursor = mDatabase.query(
                DrinkTypeTable.TNAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new DrinkCursorWrapper(cursor);
    }



    // 遍历取出Type
    public List<Type> getTypes(){
        List<Type> types = new ArrayList<>();

        DrinkCursorWrapper cursor = queryTypes(null, null);

        try{
            cursor.moveToFirst();   //指向第一个元素
            while(!cursor.isAfterLast()){   //还有下一个元素
                types.add(cursor.getType());
                cursor.moveToNext();    //读取下一行
            }
        }finally {
            cursor.close();
        }
        return types;
    }

    // 取出已存在的首条记录
    public Type getType(UUID id){
        DrinkCursorWrapper cursor = queryTypes(
                DrinkTypeTable.Cols.UUID + " = ?",
                new String[]{
                        id.toString()
                }
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getType();
        }finally {
            cursor.close();
        }
    }
}
