package com.techsolutions.patterns.strategy;

import org.springframework.stereotype.Component;

@Component("dinamico")
public class DynamicPricingStrategy implements PricingStrategy {
    @Override
    public double calcularPrecio(double precioBase) {
        System.out.println("LOG: Precio dinámico por temporada (+10%)");
        return precioBase * 1.10;
    }
}