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
            HopReaderContract.HopEntry.HOP_NAME + " TEXT," +
            HopReaderContract.HopEntry.LIST_VERSION + " INTEGER," +
            HopReaderContract.HopEntry.MAX_ALPHA_ACID + " TEXT," +
            HopReaderContract.HopEntry.MIN_ALPAH_ACID + " TEXT)";

    private static final String SQL_DELETE_HOP_ENTRIES =
            "DELETE TABLE IF EXISTS " + HopReaderContract.HopEntry.TABLE_NAME;

    private HopReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static HopReaderDbHelper getInstance(Context context) {
        if(null == sInstance) {
            sInstance = new HopReaderDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HOP_ENTRIES);
        loadHops(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Need to figure this out.
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO Figure this out.
    }

//    public List<HopIngredient> retrieveAllHops() {
//        List<HopIngredient> hops = new ArrayList<HopIngredient>();
//        String SELECT_ALL_HOPS_QUERY = "SELECT * FROM " + HopReaderContract.HopEntry.TABLE_NAME;
//        SQLiteDatabase readableDb = getReadableDatabase();
//        Cursor cursor = readableDb.rawQuery(SELECT_ALL_HOPS_QUERY, null);
//
//        try {
//            if(cursor.moveToFirst()) {
//                do {
//                    HopIngredient hop = new HopIngredient();
//                    hop.name = cursor.getString(cursor.getColumnIndex(HopReaderContract.HopEntry.HOP_NAME));
//                    hop.version = cursor.getInt(cursor.getColumnIndex(HopReaderContract.HopEntry.LIST_VERSION));
//                    hop.maxAlphaAcid = cursor.getInt(cursor.getColumnIndex(HopReaderContract.HopEntry.MAX_ALPHA_ACID));
//                    hop.minAlphaAcid = cursor.getInt(cursor.getColumnIndex(HopReaderContract.HopEntry.MIN_ALPAH_ACID));
//                } while(cursor.moveToNext());
//            }
//        } catch(Exception e) {
//            Log.d("HopReaderDbHelper", "Error while retrieving hops from the database.");
//        } finally {
//            if(null != cursor && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
//        return hops;
//    }

    private void loadHops(SQLiteDatabase db) {

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(HopReaderContract.HopEntry.HOP_NAME, "cascade");
            values.put(HopReaderContract.HopEntry.LIST_VERSION, 1);
            values.put(HopReaderContract.HopEntry.MAX_ALPHA_ACID, 4.5);
            values.put(HopReaderContract.HopEntry.MIN_ALPAH_ACID, 6);

            db.insertOrThrow(HopReaderContract.HopEntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch(Exception e) {
            Log.d("HopReaderDbHelper", "Error while insterting hops into the database.");
        } finally {
            db.endTransaction();
        }
    }
}
