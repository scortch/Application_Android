package com.androidbelieve.materialnavigationdrawer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

            afficherListeTweets();
            return view;
        }



    private List<ArticleResume> genererTweets(){
        List<ArticleResume> articleResumes = new ArrayList<ArticleResume>();
        articleResumes.add(new ArticleResume(Color.BLACK, "Benjamin", "Mon premier tweet !"));
        articleResumes.add(new ArticleResume(Color.BLUE, "Jordy", "C'est ici que ça se passe !"));
        articleResumes.add(new ArticleResume(Color.GREEN, "Clément", "Que c'est beau..."));
        articleResumes.add(new ArticleResume(Color.RED, "Evan", "Il est quelle heure ??"));
        articleResumes.add(new ArticleResume(Color.GRAY, "Pierre", "On y est presque"));
        return articleResumes;
    }

    private void afficherListeTweets(){
        List<ArticleResume> articleResumes = genererTweets();
        ArticleAdapter adapter = new ArticleAdapter(getActivity(), articleResumes);
        mListView.setAdapter(adapter);
    }


}
