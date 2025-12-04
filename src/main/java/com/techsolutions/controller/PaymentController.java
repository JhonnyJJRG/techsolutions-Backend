package com.techsolutions.controller;

import com.techsolutions.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Endpoint para realizar un pago (RF1)
    // Ejemplo de uso: POST /api/pagos/procesar?pasarela=yape&monto=50.00
    @PostMapping("/procesar")
    public ResponseEntity<String> procesarPago(@RequestParam String pasarela, @RequestParam double monto) {
        try {
            paymentService.realizarPago(pasarela, monto);
            return ResponseEntity.ok("Pago procesado correctamente con " + pasarela.toUpperCase());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para configurar pasarelas (RF2)
    // Ejemplo de uso: POST /api/pagos/configurar?pasarela=paypal&habilitada=false
    @PostMapping("/configurar")
    public ResponseEntity<String> configurarPasarela(@RequestParam String pasarela, @RequestParam boolean habilitada) {
        paymentService.configurarPasarela(pasarela, habilitada);
        String estado = habilitada ? "HABILITADA" : "DESHABILITADA";
        return ResponseEntity.ok("La pasarela " + pasarela.toUpperCase() + " ha sido " + estado);
    }
}