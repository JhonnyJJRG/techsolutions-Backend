package com.techsolutions.service.impl;

import com.techsolutions.patterns.strategy.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PricingServiceImpl {

    private final Map<String, PricingStrategy> estrategias;
    private PricingStrategy estrategiaActual;

    @Autowired
    public PricingServiceImpl(Map<String, PricingStrategy> estrategias) {
        this.estrategias = estrategias;

        this.estrategiaActual = estrategias.getOrDefault("estandar", estrategias.values().iterator().next());
    }

    public double calcularPrecioFinal(double precioBase) {
        if (estrategiaActual == null) return precioBase;
        return estrategiaActual.calcularPrecio(precioBase);
    }

    public void cambiarEstrategia(String nombreEstrategia) {
        PricingStrategy nueva = estrategias.get(nombreEstrategia.toLowerCase());
        if (nueva != null) {
            this.estrategiaActual = nueva;
            System.out.println("CONFIG: Estrategia cambiada a: " + nombreEstrategia);
        } else {
            throw new RuntimeException("Estrategia no encontrada: " + nombreEstrategia);
        }
    }
}