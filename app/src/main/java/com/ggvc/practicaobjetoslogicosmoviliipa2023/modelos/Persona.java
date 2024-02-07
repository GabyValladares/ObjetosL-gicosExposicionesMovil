package com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos;

public class Persona {
    private String nombres;
    private String cedula;

    public Persona(String nombres,String cedula) {
        this.nombres = nombres;
        this.cedula=cedula;
    }

    public Persona() {

    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
