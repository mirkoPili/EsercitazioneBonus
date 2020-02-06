/*Mirko Pili 65584*/
package com.example.esercitazionebonus;

import java.io.Serializable;
import java.util.Calendar;

public class Persona implements Serializable {
    // nota: sono gli stessi dati che chiediamo nel form
    private String nome, password,citta;
    private Calendar data;

    public Persona(String nome, String password, String data){
        this.setNome(nome);
        this.setPassword(password);
        //this.setData(data);
    }

    public Persona(){
        this.setNome("");
        this.setPassword("");
        this.setCitta("");

    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setBirthDate(Calendar birthDate) {
        this.data = birthDate;
    }

    public Calendar getData(){
        return data;
    }


}
