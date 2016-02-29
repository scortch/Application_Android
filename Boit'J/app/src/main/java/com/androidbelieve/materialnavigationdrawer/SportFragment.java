package com.androidbelieve.materialnavigationdrawer;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/9/2015.
 */
public class SportFragment extends android.support.v4.app.Fragment {

        ListView mListView;

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
                    startActivity(intent);
                }
            });
            return view;
        }



    private List<ArticleResume> genererArticles(){
        List<ArticleResume> articleResumes = new ArrayList<ArticleResume>();
        articleResumes.add(new ArticleResume(Color.BLACK, "Benjamin", "Mon premier tweet !"));
        articleResumes.add(new ArticleResume(Color.BLUE, "Jordy", "C'est ici que ça se passe !"));
        articleResumes.add(new ArticleResume(Color.GREEN, "Clément", "Que c'est beau..."));
        articleResumes.add(new ArticleResume(Color.RED, "Evan", "Il est quelle heure ??"));
        articleResumes.add(new ArticleResume(Color.GRAY, "Pierre", "On y est presque"));
        return articleResumes;
    }

    private void afficherListeArticles(){
        List<ArticleResume> articleResumes = genererArticles();
        ArticleAdapter adapter = new ArticleAdapter(getActivity(), articleResumes);
        mListView.setAdapter(adapter);
    }


}
