package com.techsolutions.service.impl;

import com.techsolutions.patterns.adapter.PaymentProcessor;
import com.techsolutions.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    // Aquí guardamos los adaptadores reales (Key = nombre del bean, Value = instancia)
    private final Map<String, PaymentProcessor> pasarelasDisponibles;

    // Aquí guardamos la configuración (si está activa o no)
    private Map<String, Boolean> estadoPasarelas = new HashMap<>();

    @Autowired
    public PaymentServiceImpl(Map<String, PaymentProcessor> pasarelasDisponibles) {
        this.pasarelasDisponibles = pasarelasDisponibles;

        // Inicializamos todas las pasarelas como "habilitadas" (true) por defecto
        this.pasarelasDisponibles.keySet().forEach(key -> estadoPasarelas.put(key, true));
    }

    @Override
    public void realizarPago(String nombrePasarela, double monto) {
        // 1. Normalizamos el nombre a minúsculas
        String key = nombrePasarela.toLowerCase();

        // 2. Verificamos si existe la pasarela
        if (!pasarelasDisponibles.containsKey(key)) {
            throw new RuntimeException("Error: La pasarela '" + nombrePasarela + "' no existe en el sistema.");
        }

        // 3. Verificamos si está habilitada por el administrador (RF2)
        if (!estadoPasarelas.get(key)) {
            throw new RuntimeException("Error: La pasarela '" + nombrePasarela + "' está deshabilitada temporalmente.");
        }

        // 4. Delegamos el pago al adaptador correspondiente (RF1)
        pasarelasDisponibles.get(key).procesarPago(monto);
    }

    @Override
    public void configurarPasarela(String nombrePasarela, boolean habilitada) {
        String key = nombrePasarela.toLowerCase();
        if (estadoPasarelas.containsKey(key)) {
            estadoPasarelas.put(key, habilitada);
            System.out.println("CONFIG: La pasarela " + key + " ha sido " + (habilitada ? "HABILITADA" : "DESHABILITADA"));
        } else {
            System.out.println("ERROR: No se encontró la pasarela " + key);
        }
    }
}