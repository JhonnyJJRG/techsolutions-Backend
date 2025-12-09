package com.techsolutions.patterns.adapter;

import org.springframework.stereotype.Component;

@Component("yape")
public class YapeAdapter implements PaymentProcessor {

    @Override
    public void procesarPago(double cantidad) {

        System.out.println("LOG: Validando código QR de Yape...");
        System.out.println("PAGO EXITOSO: Yapeo realizado por S/ " + cantidad);
    }
}