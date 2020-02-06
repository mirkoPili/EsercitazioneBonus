/*Mirko Pili 65584*/
package com.example.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistrazioneActivity extends AppCompatActivity {

    EditText username,password,ripPass,citta,data;
    Button inserisci;
    TextView errorText;
    Persona person;
    public static final String PERSON_EXTRA="package com.example.esercitazionebonus";
    DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        ripPass = findViewById(R.id.ripPass);
        data = findViewById(R.id.data);
        citta= findViewById(R.id.citta);
        inserisci= findViewById(R.id.registrati);
        datePickerFragment = new DatePickerFragment();
        person= new Persona();
        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quando premo INVIO la prima cosa che controllo è che la funzione abbia restituito
                // true, quindi non ci siano errori
                if(checkInput() ) {
                    if ( password.getText().toString().equals(ripPass.getText().toString())) {
                        // aggiorno il contenuto di persona
                        UpdatePerson();

                        PersonaFactory.getInstance().setPersona(person);
                        // crea l'oggetto di tipo Intent, ci serve per far comunicare le due activity

                        Intent showResult = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                        // inserisci l'oggetto persona dentro l'Intent
                        showResult.putExtra("persona",person);
                        // richiama l'activity ResultActivity
                        startActivity(showResult);
                    }else{
                        ripPass.setError("Password diversa");

                    }
                }
            }
        });

        //Configurazione Eventi Dialog Calendar, data è ancora l'edit text che esiste, stiamo
        // infatti settando il listener dell'edit text, vogliamo che quando si clicchi compaia il
        // fragment
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //il fragment manager è colui che dirige tutti i fragment
                datePickerFragment.show(getSupportFragmentManager(),"date picker");
            }
        });

        //questa funzione permette di controllare che l'utente non scriva nella textview
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //metodo chiamato quando lo stato della view cambia
                if(hasFocus){
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        //bottoni OK e ANNULLA
        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });
    }

    private void UpdatePerson(){
        // aggiorna il contenuto di persona usando i dati inseriti dall'utente
        this.person.setNome(this.username.getText().toString());
        this.person.setPassword(this.password.getText().toString());
        this.person.setCitta(this.citta.getText().toString());
        this.person.setBirthDate(this.datePickerFragment.getDate());
    }

    //Controlla l'input dell'utente sui campi,
    // return true se è andato a buon fine, false altrimenti
    private boolean checkInput(){
        int errors = 0;

        if(username.getText() == null || username.getText().length() == 0){
            username.setError("Inserire l'username");
            errors++;
        }
        else {
            username.setError(null);
        }

        if(password.getText() == null || password.getText().length() == 0){
            password.setError("Inserire la password");
            errors++;
        }
        else {
            password.setError(null);
        }
        if(ripPass.getText() == null || ripPass.getText().length() == 0){
            ripPass.setError("Inserire la conferma della password");
            errors++;
        }
        else{
            ripPass.setError(null);
        }
        if(citta.getText() == null || citta.getText().length() == 0){
            citta.setError("Inserire la città");
            errors++;
        }
        else{
            citta.setError(null);
        }
        if(data.getText() == null || data.getText().length() == 0){
            data.setError("Inserire la data di nascita");
            errors++;
        }
        else{
            data.setError(null);
        }

        //se non ci sono errori ritorna true
        return errors == 0;
    }
}
