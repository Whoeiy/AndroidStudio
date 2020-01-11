package com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.finalproject.starbucksordering.a.model.Type;
import com.finalproject.starbucksordering.a.model.TypeLab;
import com.finalproject.starbucksordering.a.model.User;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.DrinkTable;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.DrinkTypeTable;

import java.util.UUID;

import static com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.*;

public class DrinkCursorWrapper extends CursorWrapper {
    public DrinkCursorWrapper(Cursor cursor){
        super(cursor);
    }


    // 获取饮品类型
    public Type getType(){
        String type = getString(getColumnIndex(DrinkTypeTable.Cols.TYPE));

        Type drink_type = new Type();
        drink_type.setType(type);

        return drink_type;
    }

    // 获取用户
    public User getUser(){
        String username = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String name = getString(getColumnIndex(UserTable.Cols.NAME));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String gender = getString(getColumnIndex(UserTable.Cols.GENDER));
        String year = getString(getColumnIndex(UserTable.Cols.YEAR));
        String month = getString(getColumnIndex(UserTable.Cols.MONTH));
        String phone = getString(getColumnIndex(UserTable.Cols.PHONE));

        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setGender(gender);
        user.setYear(year);
        user.setMonth(month);
        user.setPhone(phone);

        return user;
    }

    public Drink getDrink(){
        Drink drink = new Drink();

        String uuidString = getString(getColumnIndex(DrinkTable.Cols.UUID));
        String name = getString(getColumnIndex(DrinkTable.Cols.NAME));
        String type = getString(getColumnIndex(DrinkTable.Cols.TYPE));
        int isHot = getInt(getColumnIndex(DrinkTable.Cols.HOT));
        double price = getDouble(getColumnIndex(DrinkTable.Cols.PRICE));
        String detail = getString(getColumnIndex(DrinkTable.Cols.DETAIL));
        String image = getString(getColumnIndex(DrinkTable.Cols.IMAGE));

        return drink;

    }
}
