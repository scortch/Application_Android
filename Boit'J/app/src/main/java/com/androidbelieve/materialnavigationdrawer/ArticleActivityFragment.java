package com.androidbelieve.materialnavigationdrawer;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleActivityFragment extends Fragment
{

    public ArticleActivityFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "arial.ttf");
        TextView myTextView = (TextView)view.findViewById(R.id.Titre);
        myTextView.setTypeface(myTypeface);
        return view;
    }
}
