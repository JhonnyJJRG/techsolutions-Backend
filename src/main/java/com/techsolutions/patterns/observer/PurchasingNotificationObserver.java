package com.techsolutions.patterns.observer;

import org.springframework.stereotype.Component;

@Component
public class PurchasingNotificationObserver implements InventoryObserver {
    @Override
    public void notificar(String producto, int stockActual) {
        System.out.println("ALERTA COMPRAS: Generar orden de compra para '" + producto +
                "'. Stock actual: " + stockActual);
    }
}