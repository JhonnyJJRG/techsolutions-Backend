package com.techsolutions.patterns.observer;

import org.springframework.stereotype.Component;

@Component
public class ManagerNotificationObserver implements InventoryObserver {
    @Override
    public void notificar(String producto, int stockActual) {
        System.out.println("ALERTA GERENTE: El producto '" + producto +
                "' tiene stock crítico (" + stockActual + "). Revisar proyecciones.");
    }
}