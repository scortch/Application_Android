package com.androidbelieve.materialnavigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class FormulaireFragment extends Fragment {
    private EditText subject;
    private EditText body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_formulaire,null);
        final Button button = (Button) view.findViewById(R.id.ButtonSendFeedback);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                subject = (EditText) view.findViewById(R.id.EditTextObjet);
                body = (EditText) view.findViewById(R.id.EditTextFeedbackBody);

                EditText txtFeedback = (EditText) view.findViewById(R.id.EditTextFeedbackBody);
                String strFeedback = txtFeedback.getText().toString();
                if (TextUtils.isEmpty(strFeedback)) {
                    txtFeedback.setError("Veuillez mettre votre Feedback");

                }
                EditText txtObjet = (EditText) view.findViewById(R.id.EditTextObjet);
                String strObjet = txtObjet.getText().toString();
                if (TextUtils.isEmpty(strObjet)) {
                    txtObjet.setError("Veuillez mettre votre objet");

                }
                if (!TextUtils.isEmpty(strObjet) || !TextUtils.isEmpty(strFeedback)) {
                    sendEmail();
                    subject.setText("");
                    body.setText("");
                }


            }
        });
        return view;
    }

    public void sendEmail()
    {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        //mettre le mail ici
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"email@host.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
