package com.example.mehed.sqlemailvalidation.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.mehed.sqlemailvalidation.models.PersonDetail;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "information";
    private static final String TABLE_NAME = "Email";
    private static final int VERSION = 1;

    private static final String COLUMN_ID = "id";
    private static final String USER_NAME = "user_name";
    private static final String NAME = "name";
    private static final String USER_EMAIL = "email_name";
    private static final String PASSWORD = "password";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT,"
            + USER_EMAIL + " TEXT,"
            + USER_NAME + " TEXT,"
            + PASSWORD + " TEXT"
            + ")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(PersonDetail personDetails) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, personDetails.getName());
        contentValues.put(USER_EMAIL, personDetails.getEmail());
        contentValues.put(PASSWORD, personDetails.getPassword());
        contentValues.put(USER_NAME, personDetails.getUsername());

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public boolean checkEmailPassword(String username, String password) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();

        boolean result = false;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No data Found", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String userName = cursor.getString(cursor.getColumnIndex(USER_NAME));
                    String pass = cursor.getString(cursor.getColumnIndex(PASSWORD));

                    if (userName.equals(username) && pass.equals(password)) {
                        result = true;
                        break;
                    }
                } while (cursor.moveToNext());
            }
        }

        return result;
    }
}
