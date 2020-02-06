/*Mirko Pili 65584*/
package com.example.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class ModificaPasswordActivity extends AppCompatActivity {

    TextView username,password;
    EditText modificaPass,confermaPass;
    Button home,aggiorna;
    Persona person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_password);


        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        aggiorna= findViewById(R.id.aggiorna);
        modificaPass= findViewById(R.id.modPass);
        confermaPass= findViewById(R.id.confermaPass);
        home = findViewById(R.id.home);

        // recupero l'intent mandato dal FormActivity
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra("persona");

        if(obj instanceof Persona){
            person = (Persona) obj;
        }
        // se non ho compilato i campi creo un nuovo oggetto Persona vuoto
        else {
            person = new Persona();
        }

        // associazioni tra gli elementi del file xml con gli attributi di persona

        username.setText(person.getNome());
        password.setText(person.getPassword());

        //Quando premo il bottone conferma voglio tornare indietro

        aggiorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quando clicco il bottone ok la result activity finisce e torno alla principale,
                if(modificaPass.getText().length() != 0){
                    if(modificaPass.getText().toString().equals(password.getText().toString())  ) {
                        modificaPass.setError("La password è uguale alla precendete, digitare una password diversa");
                    }else {
                        if (modificaPass.getText().toString().equals(confermaPass.getText().toString())) {
                            finish();
                            Intent intent = new Intent(ModificaPasswordActivity.this, ModificaPasswordActivity.class);
                            person.setPassword(modificaPass.getText().toString());
                            intent.putExtra("persona", person);
                            PersonaFactory.getInstance().getUser(username.getText().toString(), password.getText().toString()).setPassword(modificaPass.getText().toString());
                            startActivity(intent);
                        } else {
                            confermaPass.setError("Password diversa");
                        }
                    }
                }else{
                    modificaPass.setError("Non avete inserito nessuna password");
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(ModificaPasswordActivity.this, HomeActivity.class);
                intent.putExtra("persona",person);
                startActivity(intent);

            }
        });

    }
    
}
