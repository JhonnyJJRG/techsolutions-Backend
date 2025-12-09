package com.techsolutions.patterns.strategy;

import org.springframework.stereotype.Component;

@Component("descuento")
public class DiscountPricingStrategy implements PricingStrategy {
    @Override
    public double calcularPrecio(double precioBase) {
        System.out.println("LOG: Aplicando descuento del 20%");
        return precioBase * 0.80;
    }
}
