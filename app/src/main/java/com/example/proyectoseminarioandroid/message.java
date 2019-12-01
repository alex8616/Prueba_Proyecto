package com.example.proyectoseminarioandroid;

public class message {
    private String name;
    private String mensaje;
    message(String a,String b){
        name =a;
        mensaje=b;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getName() {
        return name;
    }
}
