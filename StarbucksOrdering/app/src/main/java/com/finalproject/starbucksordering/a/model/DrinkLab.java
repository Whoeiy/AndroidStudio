package com.finalproject.starbucksordering.a.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.DrinkBaseHelper;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.DrinkCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.*;

public class DrinkLab {
    private static DrinkLab sDrinkLab;
    private List<Drink> mDrinks;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DrinkLab get(Context context){
        if(sDrinkLab == null){
            sDrinkLab = new DrinkLab(context);
        }
        return sDrinkLab;
    }

    private DrinkLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DrinkBaseHelper(mContext)
                .getWritableDatabase();

    }

    public List<Drink> getDrinks(){
        return new ArrayList<>();
    }


    // 插入记录
    public void addDrink(Drink d){
        mDatabase.execSQL("insert into " + DrinkTable.TNAME + " values(null,?,?,?,?,?,?,?)",new Object[]{
                d.getId(), d.getName(), d.getType(), d.isHot(), d.getPrice(), d.getDetail(), d.getImage()
        });
//        mDatabase.execSQL("insert into " + DrinkTable.TNAME + " values(null,?,?,?,'false',?,?)",new Object[]{
//                d.get(0), d.get(1), d.get(2), d.get(3), d.get(4), d.get(5)
//        });

    }

    // 更新记录
    public void updateDrink(Drink d){
        String uuidString = d.getId().toString();
        ContentValues values = getContentValues(d);

        mDatabase.update(DrinkTable.TNAME, values,
                DrinkTable.Cols.UUID + " = ? ",
                new String[]{
                        uuidString
                });
    }

    private static ContentValues getContentValues(Drink drink){
        ContentValues values = new ContentValues();
        values.put(DrinkTable.Cols.UUID, drink.getId().toString());
        values.put(DrinkTable.Cols.NAME, drink.getName());
        values.put(DrinkTable.Cols.TYPE, drink.getType());
        values.put(DrinkTable.Cols.HOT, drink.isHot());
        values.put(DrinkTable.Cols.PRICE, drink.getPrice());
        values.put(DrinkTable.Cols.DETAIL, drink.getDetail());
        values.put(DrinkTable.Cols.IMAGE, drink.getImage());

        return values;

    }



}
