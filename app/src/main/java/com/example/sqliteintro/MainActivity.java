package com.example.sqliteintro;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends ListActivity {

    private static int REFRESH_BDD = 1;

    private ArticlesDBResources bdd;
    private SimpleCursorAdapter scTasks;

    private static String[] from = new String[]{
            ArticlesDBResources.ARTICLES_CODI,
            ArticlesDBResources.ARTICLES_DESCRIPCIO,
            ArticlesDBResources.ARTICLES_STOCK};

    private static int[] to = new int[]{
            R.id.tvCodiArticle,
            R.id.tvDescripcio,
            R.id.tvStock};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_list_view);

        setTitle("Articles BDD");

        bdd = new ArticlesDBResources(this);
        loadBDD();
    }

    private void loadBDD() {

        // Demanem totes les tasques
        Cursor cursorTasks = bdd.articlesAll();

        scTasks = new adapterTodoIcon(this, R.layout.main_activity_list_item, cursorTasks, from, to, 1);

        ListView lv = (ListView) findViewById(R.id.listCustom);
        lv.setAdapter(scTasks);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                       Intent articleDetall = new Intent(MainActivity.this , DetallArticleActivity.class);
                       articleDetall.putExtra("article", id);
                       startActivityForResult(articleDetall, REFRESH_BDD);
                    }
                }
        );
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REFRESH_BDD) {
            if (resultCode == RESULT_OK) {
                // Carreguem totes les tasques a lo bestia
                refreshTasks();
            }
        }
    }

    private void refreshTasks() {
        Cursor cursorArticle = null;

        bdd.articlesAll();

        scTasks.changeCursor(cursorArticle);
        scTasks.notifyDataSetChanged();
    }
}

class adapterTodoIcon extends android.widget.SimpleCursorAdapter {

    private MainActivity articles;

    public adapterTodoIcon(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        articles = (MainActivity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);

        Cursor articleRow = (Cursor) getItem(position);

        int stock = articleRow.getInt(articleRow.getColumnIndexOrThrow(ArticlesDBResources.ARTICLES_STOCK));

        if (stock <= 0) {
            view.setBackgroundColor(Color.RED);
        }

        return view;
    }
}

