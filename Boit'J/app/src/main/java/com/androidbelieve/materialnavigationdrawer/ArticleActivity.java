package com.androidbelieve.materialnavigationdrawer;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "arial.ttf");
        TextView myTextView = (TextView)findViewById(R.id.Titre);
        myTextView.setTypeface(myTypeface);
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
