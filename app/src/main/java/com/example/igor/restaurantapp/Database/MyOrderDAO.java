package com.example.igor.restaurantapp.Database;

/**
 * Created by Igor on 11-Aug-16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.igor.restaurantapp.Model.RestorantMenu;
import com.example.igor.restaurantapp.RestaurantHome;

import java.util.ArrayList;
import java.util.List;


public class MyOrderDAO {
    private SQLiteDatabase database;
    private DbOpenHelper dbHelper;
    // Database fields

    private String[] allColumns = {
            DbOpenHelper.COLUMN_ID,
            DbOpenHelper.COLUMN_TITLE,
            DbOpenHelper.COLUMN_IMAGE,
            DbOpenHelper.COLUMN_PRICE,
            DbOpenHelper.COLUMN_RATING,
    };

    public MyOrderDAO(Context context) {
        dbHelper = new DbOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
        dbHelper.close();
    }

    public boolean insert(RestorantMenu item) {

//    if (getById(item.getId()) != null) {
//      return update(item);
//    }

        long insertId = database.insert(DbOpenHelper.TABLE_NAME, null, itemToContentValues(item));

        if (insertId > 0) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean update(RestorantMenu item) {
        long numRowsAffected =
                database.update(DbOpenHelper.TABLE_NAME, itemToContentValues(item),
                        DbOpenHelper.COLUMN_ID + " = " + item.getId(), null);
        return numRowsAffected > 0;
    }

    public List<RestorantMenu> getAllItems() {

        List<RestorantMenu> items = new ArrayList<RestorantMenu>();

        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, allColumns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                items.add(cursorToItem(cursor));
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return items;
    }

    public RestorantMenu getById(long id) {

        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, allColumns,
                DbOpenHelper.COLUMN_ID + " = " + id, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                return cursorToItem(cursor);
            }
            else {
                // no items found
                return null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            cursor.close();
        }

    }

    public void deleteAll(){
        database.execSQL("delete from " + DbOpenHelper.TABLE_NAME);
    }

    public static RestorantMenu cursorToItem(Cursor cursor) {
        RestorantMenu item = new RestorantMenu();

        Long id = cursor.getLong(cursor.getColumnIndex(DbOpenHelper.COLUMN_ID));
        item.setId(id);

        String title = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_TITLE));
        item.setTitle(title);

        String image = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_IMAGE));
        item.setThumbnailUrl(image);

        String price = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_PRICE));
        item.setPrice(price);

        double rating = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.COLUMN_RATING));
        item.setRating(rating);

        return item;
    }

        //za vo baza se pravi struktura
    protected ContentValues itemToContentValues(RestorantMenu item) {
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_ID, item.getId());
        values.put(DbOpenHelper.COLUMN_TITLE, item.getTitle());
        values.put(DbOpenHelper.COLUMN_IMAGE, item.getThumbnailUrl());
        values.put(DbOpenHelper.COLUMN_PRICE, item.getPrice());
        values.put(DbOpenHelper.COLUMN_RATING, item.getRating());

        return values;
    }

    public void delete(Long id) {
        database.delete(DbOpenHelper.TABLE_NAME, DbOpenHelper.COLUMN_ID + " = " + id, null);
    }
}
