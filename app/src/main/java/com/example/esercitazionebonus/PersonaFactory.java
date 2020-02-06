/*Mirko Pili 65584*/
package com.example.esercitazionebonus;


import java.util.ArrayList;
import java.util.List;

public class PersonaFactory {
    private static PersonaFactory singleton;
    List<Persona> persone = new ArrayList<>();

    private PersonaFactory() {

    }

    public static PersonaFactory getInstance() {
        if (singleton == null) {
            singleton = new PersonaFactory();
        }
        return singleton;
    }

    public void setPersona(Persona user) {
        persone.add(user);

    }

    public Persona getUser(String username, String password) {

        if (username != null && password != null) {
            for (Persona i : persone) {
                if (i.getNome().equals(username) && i.getPassword().equals(password)) {
                    return i;
                }
            }
        }
            return null;
    }
}