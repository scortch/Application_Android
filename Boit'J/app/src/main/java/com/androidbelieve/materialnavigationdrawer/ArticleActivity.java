package com.androidbelieve.materialnavigationdrawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class ArticleActivity extends AppCompatActivity {

    static Bitmap imageBitmap;
    public static boolean erreurImage;
    private ShareButton shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_article);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarArticle);
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String idArticle = this.getIntent().getStringExtra("idArticle");

        /*System.out.println(
                "****\r\"****\n\"****\n\"****\n\"****\n\"****\n\"****\n\"****\n" +
                "id article = "+idArticle+"\n"+
                "****\r\"****\n\"****\n\"****\n\"****\n\"****\n\"****\n\"****");*/

        TextView titre = (TextView) findViewById(R.id.Titre);
        TextView contenu = (TextView) findViewById(R.id.Contenu);
        shareButton = (ShareButton) findViewById(R.id.fb_share_button);
        TextView dateArticle = (TextView) findViewById(R.id.DateArticle);




        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "arial.ttf");
        titre.setTypeface(myTypeface);
        contenu.setTypeface(myTypeface);
        dateArticle.setTypeface(myTypeface);
        titre.setText(Html.fromHtml(MainActivity.bdd.getTitreArticle(idArticle)));
        contenu.setText(Html.fromHtml(MainActivity.bdd.getTexteArticle(idArticle)));
        dateArticle.setText("Paru le "+MainActivity.bdd.getDateArticle(idArticle));


        imageBitmap = null;
        erreurImage = false;
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                ArticleActivity.imageBitmap = MainActivity.bdd.getImageArticle(idArticle);
                if(ArticleActivity.imageBitmap == null){
                    ArticleActivity.erreurImage = true;
                }
                //ArticleActivity.miseAJourImage();
            }
        });
        T.start();



        int compteur = 0;
        while(imageBitmap == null && compteur < 100 && !erreurImage){
            compteur++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(imageBitmap!=null) {
            ImageView image = (ImageView) findViewById(R.id.Image);
            image.setImageBitmap(imageBitmap);
        }
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://boitj.wordpress.com/"))
                .setImageUrl(Uri.parse("http://www.culture31.com/images/LOGOS/logo_mairiecugnaux.jpg"))
                .setContentTitle(titre.getText().toString())
                .setContentDescription(contenu.getText().toString())
                .build();

        shareButton.setShareContent(content);

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
