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
import java.text.SimpleDateFormat;

public class HomeActivity extends AppCompatActivity {

    TextView a,username,password,citta,data,modificaPass;
    Button logout;
    Persona person;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        a= findViewById(R.id.welcome);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        citta= findViewById(R.id.citta);
        data= findViewById(R.id.data);
        logout= findViewById(R.id.logout);
        modificaPass= findViewById(R.id.modPass);

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
        a.setText(String.format("Benvenuto %s!", person.getNome()));
        username.setText(person.getNome());
        password.setText(person.getPassword());
        citta.setText(person.getCitta());
        data.setText(format.format(person.getData().getTime()));//METODO della classe Persona

        //Quando premo il bottone conferma voglio tornare indietro
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quando clicco il bottone ok la result activity finisce e torno alla principale,

                finish();
                Intent intent= new Intent(HomeActivity.this, LoginActivity.class);
                intent.putExtra("persona",person);
                startActivity(intent);

            }
        });

        modificaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(HomeActivity.this, ModificaPasswordActivity.class);
                intent.putExtra("persona",person);
                startActivity(intent);
            }
        });

    }

}
