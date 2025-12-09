package com.techsolutions.service;

import com.techsolutions.model.Order;
import com.techsolutions.patterns.command.OrderCommand;
import com.techsolutions.patterns.memento.OrderMemento;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class OrderInvoker {
    private final Stack<OrderMemento> historial = new Stack<>();
    private Order pedidoActual;

    public void guardarMemento(Order order) {
        this.pedidoActual = order;
        historial.push(order.guardar());
    }

    public void ejecutarComando(OrderCommand command) {
        command.execute();
    }

    public void deshacer() {
        if (!historial.isEmpty() && pedidoActual != null) {
            OrderMemento mementoAnterior = historial.pop();
            pedidoActual.restaurar(mementoAnterior);
            System.out.println("DESHACER: Pedido #" + pedidoActual.getId() + " restaurado.");
        }
    }
}