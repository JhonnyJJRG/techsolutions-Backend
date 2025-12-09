package com.techsolutions.patterns.command;

import com.techsolutions.model.Order;
import com.techsolutions.service.OrderInvoker;

public class CancelOrderCommand implements OrderCommand {
    private final Order order;
    private final OrderInvoker invoker;

    public CancelOrderCommand(Order order, OrderInvoker invoker) {
        this.order = order;
        this.invoker = invoker;
    }

    @Override
    public void execute() {
        invoker.guardarMemento(order);
        order.setEstado("CANCELADO");
        System.out.println("COMMAND: Pedido #" + order.getId() + " cancelado.");
    }
}