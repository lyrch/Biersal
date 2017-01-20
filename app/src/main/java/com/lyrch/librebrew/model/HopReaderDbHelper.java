package com.lyrch.librebrew.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaredwilkerson on 1/5/17.
 */
public class HopReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Hops.db";

    private static HopReaderDbHelper sInstance;
    private static final String SQL_CREATE_HOP_ENTRIES =
            "CREATE TABLE " + HopReaderContract.HopEntry.TABLE_NAME + " (" +
            HopReaderContract.HopEntry._ID + " INTEGER PRIMARY KEY," +
            HopReaderContract.HopEntry.COLUMN_NAME_HOP_NAME + " TEXT." +
            HopReaderContract.HopEntry.COLUMN_NAME_LIST_VERSION + " INTEGER," +
            HopReaderContract.HopEntry.COLUMN_NAME_MAX_ALPHA_ACID + " TEXT" +
            HopReaderContract.HopEntry.COLUMN_NAME_MIN_ALPAH_ACID + " TEXT)";

    private static final String SQL_DELETE_HOP_ENTRIES =
            "DELETE TABLE IF EXISTS " + HopReaderContract.HopEntry.TABLE_NAME;

    private HopReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static HopReaderDbHelper getsInstance(Context context) {
        if(null == sInstance) {
            sInstance = new HopReaderDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HOP_ENTRIES);
        loadHops();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Need to figure this out.
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO Figure this out.
    }

    public List<HopIngredient> retrieveAllHops() {
        List<HopIngredient> hops = new ArrayList<HopIngredient>();
        String SELECT_ALL_HOPS_QUERY = "SELECT * FROM " + HopReaderContract.HopEntry.TABLE_NAME;
        SQLiteDatabase readableDb = getReadableDatabase();
        Cursor cursor = readableDb.rawQuery(SELECT_ALL_HOPS_QUERY, null);

        try {
            if(cursor.moveToFirst()) {
                do {
                    HopIngredient hop = new HopIngredient();
                    hop.name = cursor.getString(cursor.getColumnIndex(HopReaderContract.HopEntry.COLUMN_NAME_HOP_NAME));
                    hop.version = cursor.getInt(cursor.getColumnIndex(HopReaderContract.HopEntry.COLUMN_NAME_LIST_VERSION));
                    hop.maxAlphaAcid = cursor.getInt(cursor.getColumnIndex(HopReaderContract.HopEntry.COLUMN_NAME_MAX_ALPHA_ACID));
                    hop.minAlphaAcid = cursor.getInt(cursor.getColumnIndex(HopReaderContract.HopEntry.COLUMN_NAME_MIN_ALPAH_ACID));
                } while(cursor.moveToNext());
            }
        } catch(Exception e) {
            Log.d("HopReaderDbHelper", "Error while retrieving hops from the database.");
        } finally {
            if(null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return hops;
    }

    private void loadHops() {
        SQLiteDatabase writeableDb = getWritableDatabase();

        writeableDb.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(HopReaderContract.HopEntry.COLUMN_NAME_HOP_NAME, "cascade");
            values.put(HopReaderContract.HopEntry.COLUMN_NAME_LIST_VERSION, 1);
            values.put(HopReaderContract.HopEntry.COLUMN_NAME_MAX_ALPHA_ACID, 4.5);
            values.put(HopReaderContract.HopEntry.COLUMN_NAME_MIN_ALPAH_ACID, 6);

            writeableDb.insertOrThrow(HopReaderContract.HopEntry.TABLE_NAME, null, values);
            writeableDb.setTransactionSuccessful();
        } catch(Exception e) {
            Log.d("HopReaderDbHelper", "Error while insterting hops into the database.");
        } finally {
            writeableDb.endTransaction();
        }
    }
}
