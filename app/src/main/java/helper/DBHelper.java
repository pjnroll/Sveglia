package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static helper.Constants.*;

/**
 * Created by pier on 17/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context ctx) {
        super(ctx, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                C_LABEL + " VARCHAR(30) DEFAULT NULL," +
                C_YEAR + " INTEGER(4) DEFAULT NULL," +
                C_MONTH + " INTEGER(2) DEFAULT NULL," +
                C_DAY + " INTEGER(2) DEFAULT NULL," +
                C_HOURS + " INTEGER(2) DEFAULT NULL," +
                C_MINUTES + " INTEGER(2) DEFAULT NULL" +
                ");";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
