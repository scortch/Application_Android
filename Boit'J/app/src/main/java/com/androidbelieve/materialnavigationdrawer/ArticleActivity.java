package com.androidbelieve.materialnavigationdrawer;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarArticle);
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String idArticle = this.getIntent().getStringExtra("idArticle");

        TextView titre = (TextView) findViewById(R.id.Titre);
        TextView contenu = (TextView) findViewById(R.id.Contenu);
        ImageView image = (ImageView) findViewById(R.id.Image);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "arial.ttf");
        titre.setTypeface(myTypeface);
        titre.setText(Html.fromHtml(MainActivity.bdd.getTitreArticle(idArticle)));
        contenu.setText(Html.fromHtml(MainActivity.bdd.getTexteArticle(idArticle)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            Intent intent = new Intent(ArticleActivity.this,MainActivity.class);
            //intent.putExtra("Fragment",6);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
