package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import org.mindrot.jbcrypt.BCrypt;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users (username TEXT PRIMARY KEY, email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    // Hash the password using bcrypt
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Register method - stores the user data with the hashed password
    public void register(String username, String email, String hashedPassword) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", hashedPassword); // Store hashed password
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    // Login method - compares the provided password with the stored hashed password
    public boolean login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        boolean isValidUser = false;

        try {
            String[] str = {username};
            c = db.rawQuery("SELECT * FROM users WHERE username=?", str);

            if (c != null && c.moveToFirst()) {
                @SuppressLint("Range") String storedHashedPassword = c.getString(c.getColumnIndex("password"));

                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    isValidUser = true;
                }
            }
        } catch (Exception e) {
            Log.e("Database", "Error during login", e);
        } finally {
            if (c != null) {
                c.close();
            }
            db.close();
        }

        return isValidUser;
    }
}
