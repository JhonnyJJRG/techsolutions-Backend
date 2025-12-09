package com.techsolutions.patterns.iterator;

import com.techsolutions.model.Product;
import java.util.List;

public class TechProductIterator implements ProductIterator {
    private List<Product> productos;
    private int posicion = 0;

    public TechProductIterator(List<Product> productos) {
        this.productos = productos;
    }

    @Override
    public boolean hasNext() {
        return posicion < productos.size();
    }

    @Override
    public Product next() {
        if (!hasNext()) return null;
        Product producto = productos.get(posicion);
        posicion++;
        return producto;
    }
}