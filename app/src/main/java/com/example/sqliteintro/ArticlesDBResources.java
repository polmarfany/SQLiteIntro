package com.example.sqliteintro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ArticlesDBResources {

    public static final String table_ARTICLES = "articles";
    public static final String ARTICLES_CODI = "codi";
    public static final String ARTICLES_DESCRIPCIO = "descripcio";
    public static final String ARTICLES_PVP = "pvp";
    public static final String ARTICLES_STOCK = "stock";

    private SQLiteHelper dbHelper;
    private SQLiteDatabase dbW, dbR;

    // CONSTRUCTOR
    public ArticlesDBResources(Context ctx) {
        dbHelper = new SQLiteHelper(ctx);
        open();
    }

    private void open() {
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }

    // DESTRUCTOR
    protected void finalize () {
        dbW.close();
        dbR.close();
    }

    //cursors
    public Cursor articlesAll() {
        return dbR.query(table_ARTICLES, new String[]{ARTICLES_CODI, ARTICLES_DESCRIPCIO, ARTICLES_PVP, ARTICLES_STOCK},
                null, null,
                null, null, ARTICLES_CODI);
    }


    //DML
    public void articleAdd(String codi, String descripcio, float pvp, int stock) {
        if (articleCheckCodi(codi) && articleCheckDescricio(descripcio) && articleCheckPVP(pvp) ) {
            ContentValues values = new ContentValues();
            values.put(ARTICLES_DESCRIPCIO, descripcio);
            values.put(ARTICLES_PVP, pvp);
            values.put(ARTICLES_STOCK, stock);

            dbW.insert(table_ARTICLES, null, values);
        }
    }

    public void articleUpdate(long codi, String descripcio, float pvp, int stock) {
        if (articleCheckDescricio(descripcio) && articleCheckPVP(pvp)) {
            ContentValues values = new ContentValues();
            values.put(ARTICLES_DESCRIPCIO, descripcio);
            values.put(ARTICLES_PVP, pvp);
            values.put(ARTICLES_STOCK, stock);

            dbW.update(table_ARTICLES, values, ARTICLES_CODI + "=?", new String[]{String.valueOf(codi)});
        }
    }

    public void articleDelete(long codi) {
        dbW.delete(table_ARTICLES, ARTICLES_CODI + "=?", new String[] { String.valueOf(codi) });
    }



    //eines del resourcer
    public boolean articleCheckCodi(String codi) {
        boolean boo = true;

        Cursor check = dbR.query(table_ARTICLES, new String[]{ARTICLES_CODI}, ARTICLES_CODI + "=?", new String[]{String.valueOf(codi)}, null, null, null);
        check.moveToFirst();

        if (!check.equals(null) ){
            boo = false;
            System.out.println("El codi d'article introduit ja existeix!");
        }

        return boo;
    }

    public boolean articleCheckDescricio(String descripcio) {
        boolean boo = true;

        if (descripcio.length() == 0) {
            boo = false;
            System.out.println("El camp descripció és obligatori!");
        }

        return boo;
    }

    public boolean articleCheckPVP(float pvp) {
        boolean boo = true;

        if (pvp < 0) {
            boo = false;
            System.out.println("El pvp ha de ser positiu!");
        }
        return boo;
    }
}


