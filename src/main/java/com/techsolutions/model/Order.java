package com.techsolutions.model;

import com.techsolutions.patterns.memento.OrderMemento;

public class Order {
    private Long id;
    private String cliente;
    private double total;
    private String estado;

    public Order(Long id, String cliente, double total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getTotal() { return total; }


    public OrderMemento guardar() {
        return new OrderMemento(this.estado);
    }

    public void restaurar(OrderMemento memento) {
        this.estado = memento.getEstado();
    }
}
