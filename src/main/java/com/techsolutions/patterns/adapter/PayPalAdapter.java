package com.techsolutions.patterns.adapter;

import org.springframework.stereotype.Component;

@Component("paypal")
public class PayPalAdapter implements PaymentProcessor {

    @Override
    public void procesarPago(double cantidad) {

        System.out.println("LOG: Conectando con API de PayPal...");
        System.out.println("PAGO EXITOSO: Se ha cargado $" + cantidad + " vía PayPal.");
    }
}