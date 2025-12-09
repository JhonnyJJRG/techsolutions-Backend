package com.techsolutions.patterns.memento;

public class OrderMemento {
    private final String estado;

    public OrderMemento(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
