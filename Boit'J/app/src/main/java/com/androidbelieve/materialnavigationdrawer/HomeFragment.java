package com.androidbelieve.materialnavigationdrawer;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static boolean erreurImage;
    private static Bitmap imageBitmap;
    final private String ID_ARTICLE = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_layout, container, false);


        TextView titre = (TextView) v.findViewById(R.id.TitreAccueil);
        TextView contenu = (TextView) v.findViewById(R.id.ContenuAccueil);

        //Typeface myTypeface = Typeface.createFromAsset(getAssets(), "arial.ttf");
        //titre.setTypeface(myTypeface);



        while(MainActivity.bdd.listeJson == null || MainActivity.bdd.listeJson.size() == 0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        titre.setText(Html.fromHtml(MainActivity.bdd.getTitreArticle(ID_ARTICLE)));
        contenu.setText(Html.fromHtml(MainActivity.bdd.getTexteArticle(ID_ARTICLE)));


        imageBitmap = null;
        erreurImage = false;
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                HomeFragment.imageBitmap = MainActivity.bdd.getImageArticle(ID_ARTICLE);
                if(HomeFragment.imageBitmap == null){
                    HomeFragment.erreurImage = true;
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
            ImageView image = (ImageView) v.findViewById(R.id.Image);
            image.setImageBitmap(imageBitmap);
        }

        return v;
    }

}
