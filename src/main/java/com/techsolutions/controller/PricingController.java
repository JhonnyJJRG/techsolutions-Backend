package com.techsolutions.controller;

import com.techsolutions.service.impl.PricingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/precios")
public class PricingController {

    private final PricingServiceImpl pricingService;

    @Autowired
    public PricingController(PricingServiceImpl pricingService) {
        this.pricingService = pricingService;
    }

    // RF9: Calcular precio según la estrategia actual
    // Ejemplo: GET /api/precios/calcular?precioBase=100
    @GetMapping("/calcular")
    public String calcularPrecio(@RequestParam double precioBase) {
        double precioFinal = pricingService.calcularPrecioFinal(precioBase);
        return String.format("Precio Base: %.2f | Precio Final: %.2f (Estrategia activa aplicada)", precioBase, precioFinal);
    }

    // RF10: Cambiar estrategia en tiempo de ejecución
    // Ejemplo: POST /api/precios/estrategia?nombre=descuento
    @PostMapping("/estrategia")
    public String cambiarEstrategia(@RequestParam String nombre) {
        try {
            pricingService.cambiarEstrategia(nombre);
            return "Estrategia de precios cambiada exitosamente a: " + nombre.toUpperCase();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}