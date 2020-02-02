package com.example.sqliteintro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int db_VERSION = 1;
    private static final String db_NAME = "SQLiteHelper";

    public SQLiteHelper(Context context) {
        super(context, db_NAME, null, db_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TODOLIST =
                "CREATE TABLE todolist ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "CodiArticle TEXT," +
                        "Descripcio TEXT," +
                        "PVP FLOAT," +
                        "Estoc INTEGER)";

        sqLiteDatabase.execSQL(CREATE_TODOLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}