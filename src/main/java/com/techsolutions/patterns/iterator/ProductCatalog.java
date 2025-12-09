package com.techsolutions.patterns.iterator;

import com.techsolutions.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> productos = new ArrayList<>();

    public ProductCatalog() {
        // Simulamos datos iniciales
        productos.add(new Product("Laptop HP", "Tecnologia", 2500.00));
        productos.add(new Product("Mouse Gamer", "Tecnologia", 50.00));
        productos.add(new Product("Escritorio", "Muebles", 300.00));
        productos.add(new Product("Silla Ergonómica", "Muebles", 450.00));
        productos.add(new Product("Monitor Dell", "Tecnologia", 800.00));
    }

    public void agregarProducto(String nombre, String categoria, double precio) {
        productos.add(new Product(nombre, categoria, precio));
    }

    public ProductIterator getIterator() {
        return new TechProductIterator(productos);
    }
}