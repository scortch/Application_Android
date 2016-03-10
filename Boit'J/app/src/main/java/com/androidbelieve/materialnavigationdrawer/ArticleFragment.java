package com.androidbelieve.materialnavigationdrawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/9/2015.
 */
public class ArticleFragment extends android.support.v4.app.Fragment {

    private static Bitmap imageBitmap;
    private static boolean erreurImage;
    ListView mListView;
    AccesBDD bdd = MainActivity.bdd;
    String idCateguorie = "all";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sport_layout, null);
        mListView = (ListView) view.findViewById(R.id.listView);

        afficherListeArticles();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ArticleActivity.class);
                intent.putExtra("idArticle",MainActivity.bdd.getIdArticle(idCateguorie,position));
                startActivity(intent);
            }
        });
        return view;
        }



    private List<ArticleResume> genererArticles(){
        List<ArticleResume> articleResumes = new ArrayList<ArticleResume>();

        for(int i = 0; i < bdd.getNombreArticle(idCateguorie) ; i++){
            final String idArticle = bdd.getIdArticle(idCateguorie,i);



            imageBitmap = null;
            erreurImage = false;
            Thread T = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArticleFragment.imageBitmap = MainActivity.bdd.getImageArticle(idArticle);
                    if(ArticleFragment.imageBitmap == null){
                        ArticleFragment.erreurImage = true;
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



            articleResumes.add(
                    new ArticleResume(
                            imageBitmap,
                            bdd.getTitreArticle(idArticle),
                            bdd.getDescriptionArticle(idArticle)));
        }


        return articleResumes;
    }

    private void afficherListeArticles(){
        List<ArticleResume> articleResumes = genererArticles();
        ArticleAdapter adapter = new ArticleAdapter(getActivity(), articleResumes);
        mListView.setAdapter(adapter);
    }


}
