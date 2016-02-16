package com.androidbelieve.materialnavigationdrawer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<ArticleResume> {

    public ArticleAdapter(Context context, List<ArticleResume> articleResumes) {
        super(context, 0, articleResumes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_resume_layout,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }


        ArticleResume article = getItem(position);
        viewHolder.pseudo.setText(article.getPseudo());
        viewHolder.text.setText(article.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(article.getColor()));


//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                Fragment articleFragment = new ArticleFragment();
//                fragmentTransaction.replace(R.id.containerView,articleFragment);
//                fragmentTransaction.commit();
//            }
//        });
        return convertView;
    }


    private class TweetViewHolder{
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;

    }
}
