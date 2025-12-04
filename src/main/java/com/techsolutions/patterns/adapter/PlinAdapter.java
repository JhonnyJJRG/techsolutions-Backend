package com.techsolutions.patterns.adapter;

import org.springframework.stereotype.Component;

@Component("plin")
public class PlinAdapter implements PaymentProcessor {

    @Override
    public void procesarPago(double cantidad) {
        // Lógica específica de Plin
        System.out.println("LOG: Verificando número telefónico en Plin...");
        System.out.println("PAGO EXITOSO: Transferencia Plin recibida por S/ " + cantidad);
    }
}