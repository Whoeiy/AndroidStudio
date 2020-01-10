package com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.finalproject.starbucksordering.a.model.Type;
import com.finalproject.starbucksordering.a.model.TypeLab;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.DrinkTable;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.database.StarbucksOrderingDbSchema.StarbucksOrderingDbSchema.DrinkTypeTable;

import java.util.UUID;

public class DrinkCursorWrapper extends CursorWrapper {
    public DrinkCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Type getType(){
        String type = getString(getColumnIndex(DrinkTypeTable.Cols.TYPE));

        Type drink_type = new Type();
        drink_type.setType(type);

        return drink_type;
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
