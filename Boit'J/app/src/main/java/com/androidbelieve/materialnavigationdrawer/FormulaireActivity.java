package com.androidbelieve.materialnavigationdrawer;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormulaireActivity extends FragmentActivity {
    private EditText subject;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        final Button button = (Button) findViewById(R.id.ButtonSendFeedback);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                subject = (EditText) findViewById(R.id.EditTextObjet);
                body = (EditText) findViewById(R.id.EditTextFeedbackBody);

                EditText txtFeedback = (EditText) findViewById(R.id.EditTextFeedbackBody);
                String strFeedback = txtFeedback.getText().toString();
                if (TextUtils.isEmpty(strFeedback)) {
                    txtFeedback.setError("Veuillez mettre votre Feedback");

                }
                EditText txtObjet = (EditText) findViewById(R.id.EditTextObjet);
                String strObjet = txtObjet.getText().toString();
                if (TextUtils.isEmpty(strObjet)) {
                    txtObjet.setError("Veuillez mettre votre objet");

                }
                if (!TextUtils.isEmpty(strObjet) && !TextUtils.isEmpty(strFeedback)) {
                    sendEmail();
                    subject.setText("");
                    body.setText("");
                }


            }
        });
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
            Toast.makeText(this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
