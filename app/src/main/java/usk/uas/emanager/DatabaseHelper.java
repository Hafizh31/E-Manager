package usk.uas.emanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "item.db";

    public static final String TABLE_ITEM = "tb_item";

    public static final String KEY_ID = "id";
    public static final String KEY_NAMABARANG = "namabarang";
    public static final String KEY_HARGA = "harga";
    public static final String KEY_SATUAN = "satuan";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NAMABARANG + " TEXT,"
                    + KEY_HARGA + " TEXT,"
                    + KEY_SATUAN + " TEXT)";

        db.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

