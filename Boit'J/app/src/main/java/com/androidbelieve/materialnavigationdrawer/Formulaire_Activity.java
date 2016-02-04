package com.androidbelieve.materialnavigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Formulaire_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        final Button button = (Button) findViewById(R.id.ButtonSendFeedback);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

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
                if (!TextUtils.isEmpty(strObjet) || !TextUtils.isEmpty(strFeedback)) {
                    Context context = getApplicationContext();
                    Toast toast= Toast.makeText(context,"Email envoyé",Toast.LENGTH_LONG);
                    toast.show();
                    startActivity(new Intent(Formulaire_Activity.this, MainActivity.class));
                }


            }
        });
    }
}
