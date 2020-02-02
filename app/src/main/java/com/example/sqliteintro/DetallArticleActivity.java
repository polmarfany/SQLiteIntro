package com.example.sqliteintro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DetallArticleActivity extends AppCompatActivity {

    private Button btnGuardarCanvis, btnCancelar;

    private EditText descripcio, PVP, stock;
    private TextView codiArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detall_article_activity_layout);

        Bundle extras = getIntent().getExtras();
        final Article article = (Article) extras.getSerializable("article") ;

        codiArticle = findViewById(R.id.edtCoditArticle);
        descripcio = findViewById(R.id.edtDescripcio);
        PVP = findViewById(R.id.edtPVP);
        stock = findViewById(R.id.edtStock);

        final String codiArticleString = article.getCodiArticle();

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Article: "+codiArticleString);

        codiArticle.setText(codiArticleString);
        descripcio.setText(article.getDescripcio());
        PVP.setText(String.valueOf(article.getPVP() ) );
        stock.setText(String.valueOf(article.getStock() ) );

        btnGuardarCanvis = findViewById(R.id.btnGuardarCanvis);
        btnGuardarCanvis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String problem = "";
                boolean bdesc = true, bpvp = true, bstock = true;
                String desc = descripcio.getText().toString();
                float pvp = Float.valueOf(PVP.getText().toString());
                int sto = Integer.valueOf(stock.getText().toString());

                if (desc.length() > 0) {
                    bdesc = false;
                    problem = problem + "Camp descripcio a omplenar.";
                }
                if (pvp >= 0) {
                    bpvp = false;
                    problem = problem + "Camp PVP ha de ser 0 o mes gran.";
                }
                if (sto >= 0) {
                    bstock = false;
                    problem = problem + "Camp stock ha de ser 0 o mes gran.";
                }

                if (bdesc && bpvp && bstock) {
                    article.setDescripcio(desc);
                    article.setPVP(pvp);
                    article.setStock(sto);

                    Intent intentGuardar = new Intent(DetallArticleActivity.this, MainActivity.class);
                    intentGuardar.putExtra("article", article);
                    startActivity(intentGuardar);
                }
                else {
                    Toast.makeText(DetallArticleActivity.this, problem, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intentCancelar = new Intent(DetallArticleActivity.this, MainActivity.class);
                startActivity(intentCancelar);
            }
        });
    }



}
