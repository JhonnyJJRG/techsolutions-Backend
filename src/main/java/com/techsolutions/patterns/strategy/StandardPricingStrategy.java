package com.techsolutions.patterns.strategy;

import org.springframework.stereotype.Component;

@Component("estandar")
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase; // Cobra el precio normal
    }
}