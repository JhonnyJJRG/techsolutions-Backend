package com.techsolutions.patterns.observer;

public interface InventoryObserver {
    void notificar(String producto, int stockActual);
}