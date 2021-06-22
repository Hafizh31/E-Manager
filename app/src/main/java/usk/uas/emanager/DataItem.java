package usk.uas.emanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usk.uas.emanager.DatabaseHelper;

import static usk.uas.emanager.DatabaseHelper.KEY_NAMABARANG;
import static usk.uas.emanager.DatabaseHelper.KEY_HARGA;
import static usk.uas.emanager.DatabaseHelper.KEY_ID;
import static usk.uas.emanager.DatabaseHelper.KEY_SATUAN;
import static usk.uas.emanager.DatabaseHelper.TABLE_ITEM;

public class DataItem {
    private SQLiteDatabase database;
    private DatabaseHelper dbhelper;

    public DataItem(Context context){
        dbhelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void addItem(Item item){
        ContentValues values = new ContentValues();
        values.put(KEY_NAMABARANG, item.get_namabarang());
        values.put(KEY_HARGA, item.get_harga());
        values.put(KEY_SATUAN, item.get_satuan());

        //inserting row
        database.insert(TABLE_ITEM, null, values);
    }

    public List<Item> getAllItem(){
        List<Item> listItem = new ArrayList<Item>();

        //select all data item
        String allItem = "SELECT * FROM " + TABLE_ITEM + " ORDER BY namabarang";
        Cursor cursor = database.rawQuery(allItem, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.set_id(Integer.parseInt(cursor.getString(0)));
                item.set_namabarang(cursor.getString(1));
                item.set_harga(cursor.getString(2));
                item.set_satuan(cursor.getString(3));

                //adding item to the list
                listItem.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listItem;
    }

    public void deleteOne(String id) {
        String queryString = "DELETE FROM " + TABLE_ITEM + " WHERE " + KEY_ID + " = "+ id;
        database.execSQL(queryString);
    }

    public void updateOne(String id, String new_namabarang, String new_harga, String new_satuan){
        database = dbhelper.getWritableDatabase();
        String queryString = "UPDATE " + TABLE_ITEM +
                " SET "
                + KEY_NAMABARANG + " = '" + new_namabarang +"',"
                + KEY_HARGA + " = '" + new_harga + "',"
                + KEY_SATUAN + " = '" + new_satuan+
                "' WHERE " + KEY_ID + " = " +id;
//        String m = "UPDATE tb_item SET nama='aaaaaaaaaaa' WHERE id=8";
        database.execSQL(queryString);
    }





    public List<Item> searched(String s){
        List<Item> listSearched = new ArrayList<Item>();

        //select all data item
        String cari = "SELECT * FROM " + TABLE_ITEM + " WHERE namabarang = " + "'" + s + "'";
        Cursor cursor = database.rawQuery(cari, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.set_id(Integer.parseInt(cursor.getString(0)));
                item.set_namabarang(cursor.getString(1));
                item.set_harga(cursor.getString(2));
                item.set_satuan(cursor.getString(3));

                //adding item to the list
                listSearched.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listSearched;
    }

}
