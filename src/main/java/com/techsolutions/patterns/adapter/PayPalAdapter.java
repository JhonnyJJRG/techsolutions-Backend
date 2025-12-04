package com.techsolutions.patterns.adapter;

import org.springframework.stereotype.Component;

@Component("paypal") // Le damos un nombre para identificarlo luego
public class PayPalAdapter implements PaymentProcessor {

    @Override
    public void procesarPago(double cantidad) {
        // Aquí simulamos la conexión con la API real de PayPal
        System.out.println("LOG: Conectando con API de PayPal...");
        System.out.println("PAGO EXITOSO: Se ha cargado $" + cantidad + " vía PayPal.");
    }
}