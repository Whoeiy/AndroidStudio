package com.finalproject.starbucksordering.a.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.DrinkBaseHelper;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.DrinkCursorWrapper;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema;

import java.util.ArrayList;
import java.util.List;

import static com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.*;

public class UserLab {
    private static UserLab sUserLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserLab get(Context context){
        if(sUserLab == null){
            sUserLab = new UserLab(context);
        }
        return sUserLab;
    }

    private UserLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DrinkBaseHelper(mContext)
                .getWritableDatabase();
    }


    // 增
    public void addUser(User u){
        ContentValues values = getContentValues(u);
        mDatabase.insert(UserTable.TNAME, null, values);
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, user.getUsername());
        values.put(UserTable.Cols.NAME, user.getName());
        values.put(UserTable.Cols.PASSWORD, user.getPassword());
        values.put(UserTable.Cols.GENDER, user.getGender());
        values.put(UserTable.Cols.YEAR, user.getYear());
        values.put(UserTable.Cols.MONTH, user.getMonth());
        values.put(UserTable.Cols.PHONE, user.getPhone());

        return values;

    }

    // 删
    public void deleteUser(String username){
        String[] arr = {username};
        mDatabase.delete(UserTable.TNAME, UserTable.Cols.USERNAME, arr);
    }


    // 查
    private DrinkCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                UserTable.TNAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new DrinkCursorWrapper(cursor);
    }
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();

        DrinkCursorWrapper cursor = queryUsers(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return users;
    }
}
