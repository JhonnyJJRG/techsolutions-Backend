package com.techsolutions.patterns.command;

import com.techsolutions.model.Order;
import com.techsolutions.service.OrderInvoker;

public class PayOrderCommand implements OrderCommand {
    private final Order order;
    private final OrderInvoker invoker;

    public PayOrderCommand(Order order, OrderInvoker invoker) {
        this.order = order;
        this.invoker = invoker;
    }

    @Override
    public void execute() {
        invoker.guardarMemento(order);
        order.setEstado("PAGADO");
        System.out.println("COMMAND: Pedido #" + order.getId() + " pagado exitosamente.");
    }
}