/*Mirko Pili 65584*/
package com.example.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity{
    public EditText username,pass;
    public Button accedi;
    public TextView registrati;
    TextView errorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        errorText = findViewById(R.id.errorText);
        accedi= findViewById(R.id.Accedi);
        registrati = findViewById(R.id.registrati);


        // di default la visibilità dell'error text è gone
        errorText.setVisibility(View.GONE);

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    if( PersonaFactory.getInstance().getUser(username.getText().toString() ,pass.getText().toString()) != null) {
                        finish();
                        Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("persona",PersonaFactory.getInstance().getUser(username.getText().toString() ,pass.getText().toString()));
                        startActivity(intent);

                    }else {

                            username.setError("Username o Password sbagliata");
                            pass.setError("Username o Password sbagliata");


                    }
                }
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, RegistrazioneActivity.class));
            }
        });


    }

    private boolean checkInput(){
        int errors = 0;

        if(username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire l'username");
            errors++;
        }
        else {
            username.setError(null);
        }

        if(pass.getText() == null || pass.getText().length() == 0){
            pass.setError("Inserire la password");
            errors++;
        }
        else {
            pass.setError(null);
        }

        //se non ci sono errori ritorna true
        return errors == 0;
    }



}

