package com.techsolutions.patterns.adapter;

// Esta es la interfaz "Target" que nuestro sistema espera usar.
public interface PaymentProcessor {
    void procesarPago(double cantidad);
}