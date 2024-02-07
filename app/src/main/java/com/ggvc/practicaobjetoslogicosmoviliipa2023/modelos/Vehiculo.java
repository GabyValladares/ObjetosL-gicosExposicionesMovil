package com.ggvc.practicaobjetoslogicosmoviliipa2023.modelos;

public class Vehiculo {
    private String placa;
    private String marca;
    private String color;
    private double valor;
    private String tipo;

    private int anio;
    public Vehiculo(String placa, String marca, String color, double valor, String tipo,int anio) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.valor = valor;
        this.tipo = tipo;
        this.anio=anio;
    }

    public Vehiculo() {

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color, int lengthLong) {
        this.color = color;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
