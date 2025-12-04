package com.techsolutions.service;

import com.techsolutions.patterns.observer.InventoryObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private final List<InventoryObserver> observadores = new ArrayList<>();

    // Inyectamos todos los observadores que encuentre Spring (Gerente y Compras)
    @Autowired
    public InventoryService(List<InventoryObserver> observadoresExistentes) {
        this.observadores.addAll(observadoresExistentes);
    }

    // Método principal: Modificar stock y notificar si es necesario (RF5)
    public void modificarStock(String producto, int cantidadActual, int stockMinimo) {
        System.out.println("INVENTARIO: Actualizando stock de " + producto + " a " + cantidadActual + " unidades.");

        if (cantidadActual < stockMinimo) {
            notificarObservadores(producto, cantidadActual);
        }
    }

    private void notificarObservadores(String producto, int stock) {
        for (InventoryObserver obs : observadores) {
            obs.notificar(producto, stock);
        }
    }

    // Métodos para agregar/quitar observadores manualmente si fuera necesario
    public void agregarObservador(InventoryObserver obs) {
        observadores.add(obs);
    }
}