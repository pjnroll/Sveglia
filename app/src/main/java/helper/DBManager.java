package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.Date;

import static helper.Constants.*;


public class DBManager {
    final static String TAG = "DBManager";
    private DBHelper dbHelper;

    public DBManager(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public boolean insert(String label, int y, int mo, int d, int h, int mi) {
        SQLiteDatabase db = getDbHelper().getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(C_LABEL, label);
        cv.put(C_YEAR, y);
        cv.put(C_MONTH, mo);
        cv.put(C_DAY, d);
        cv.put(C_HOURS, h);
        cv.put(C_MINUTES, mi);
        return (db.insert(TABLE_NAME, null, cv) != -1) ? true : false;

    }

    public void doQuery(String s) {
        SQLiteDatabase db = getDbHelper().getWritableDatabase();
        db.rawQuery(s, null);

    }
    public Cursor query() {
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getDbHelper().getReadableDatabase();
            //cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            //cursor = db.query(TABLE_NAME, new String[]{C_ID, C_LABEL, C_YEAR, C_MONTH, C_DAY, C_HOURS, C_MINUTES}, null, null, null, null, null);

            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            //cursor = db.rawQuery("DELETE * FROM " + TABLE_NAME, null);

        } catch(SQLiteException e) {
            Log.e(TAG, e.getMessage());
        }

        return cursor;
    }

    private DBHelper getDbHelper() {
        return dbHelper;
    }
}
