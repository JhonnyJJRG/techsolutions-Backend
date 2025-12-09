package com.techsolutions.model;

public class Product {
    private String nombre;
    private String categoria;
    private double precio;

    public Product(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ") - S/" + precio;
    }
}