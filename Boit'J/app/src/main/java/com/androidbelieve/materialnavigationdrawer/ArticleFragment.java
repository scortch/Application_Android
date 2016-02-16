package com.androidbelieve.materialnavigationdrawer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ArticleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_layout, null);
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "arial.ttf");
        TextView myTextView = (TextView)view.findViewById(R.id.Titre);
        myTextView.setTypeface(myTypeface);

        return view;
    }


}
