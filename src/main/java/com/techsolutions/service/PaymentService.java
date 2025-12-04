package com.techsolutions.service;

public interface PaymentService {
    void realizarPago(String pasarela, double monto);
    void configurarPasarela(String pasarela, boolean habilitada);
}