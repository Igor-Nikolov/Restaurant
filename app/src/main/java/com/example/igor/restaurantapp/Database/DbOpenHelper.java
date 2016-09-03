package com.example.igor.restaurantapp.Database;

/**
 * Created by Igor on 11-Aug-16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "MyOrders";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "thumbnailUrl";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_RATING = "rating";

    public static final String TABLE_NAME2 = "MenuItems";
    public static final String COLUMN_ID2 = "_id";
    public static final String COLUMN_TITLE2 = "title";
    public static final String COLUMN_IMAGE2 = "thumbnailUrl";
    public static final String COLUMN_PRICE2 = "price";
    public static final String COLUMN_RATING2 = "rating";



    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "RestaurantAppDB.db";

    //posle primary key autoincrement ako sakame ID da dodava
    private static final String DATABASE_CREATE = String
            .format("create table %s (%s  integer primary key, "
                            + "%s text not null, %s text, %s text, %s text);",
                    TABLE_NAME, COLUMN_ID, COLUMN_TITLE, COLUMN_IMAGE, COLUMN_PRICE, COLUMN_RATING);

    private static final String DATABASE_CREATE2 = String
            .format("create table %s (%s  integer primary key, "
                            + "%s text not null, %s text, %s text, %s text);",
                    TABLE_NAME2, COLUMN_ID2, COLUMN_TITLE2, COLUMN_IMAGE2, COLUMN_PRICE2, COLUMN_RATING2);

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Don't do this in real end-user applications
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME2));
        onCreate(db);
    }

}

