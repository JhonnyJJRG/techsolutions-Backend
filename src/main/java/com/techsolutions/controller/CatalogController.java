package com.techsolutions.controller;

import com.techsolutions.model.Product;
import com.techsolutions.patterns.iterator.ProductCatalog;
import com.techsolutions.patterns.iterator.ProductIterator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogController {

    private ProductCatalog catalogo = new ProductCatalog();

    @GetMapping("/listar")
    public List<String> listarProductos() {
        List<String> resultado = new ArrayList<>();
        ProductIterator iterator = catalogo.getIterator();

        while (iterator.hasNext()) {
            Product p = iterator.next();
            resultado.add(p.toString());
        }
        return resultado;
    }
}