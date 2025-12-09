package com.techsolutions.controller;

import com.techsolutions.model.Order;
import com.techsolutions.patterns.command.CancelOrderCommand;
import com.techsolutions.patterns.command.PayOrderCommand;
import com.techsolutions.service.OrderInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {

    private final OrderInvoker invoker;
    // Simulación de Base de Datos en memoria
    private final Map<Long, Order> pedidosDB = new HashMap<>();

    @Autowired
    public OrderController(OrderInvoker invoker) {
        this.invoker = invoker;
    }

    // 1. Crear un pedido nuevo (Estado inicial: PENDIENTE)
    @PostMapping("/crear")
    public Order crearPedido(@RequestParam Long id, @RequestParam String cliente, @RequestParam double total) {
        Order nuevoPedido = new Order(id, cliente, total, "PENDIENTE");
        pedidosDB.put(id, nuevoPedido);
        return nuevoPedido;
    }

    // 2. Pagar pedido (Usa el Patrón COMMAND)
    @PostMapping("/{id}/pagar")
    public String pagarPedido(@PathVariable Long id) {
        Order pedido = pedidosDB.get(id);
        if (pedido == null) return "Pedido no encontrado";

        // Creamos el comando y le decimos al invoker que lo ejecute
        PayOrderCommand command = new PayOrderCommand(pedido, invoker);
        invoker.ejecutarComando(command);

        return "Pedido " + id + " pagado. Estado actual: " + pedido.getEstado();
    }

    // 3. Cancelar pedido (Usa el Patrón COMMAND)
    @PostMapping("/{id}/cancelar")
    public String cancelarPedido(@PathVariable Long id) {
        Order pedido = pedidosDB.get(id);
        if (pedido == null) return "Pedido no encontrado";

        CancelOrderCommand command = new CancelOrderCommand(pedido, invoker);
        invoker.ejecutarComando(command);

        return "Pedido " + id + " cancelado. Estado actual: " + pedido.getEstado();
    }

    // 4. Deshacer última acción (Usa el Patrón MEMENTO - RF8)
    @PostMapping("/deshacer")
    public String deshacerCambio() {
        invoker.deshacer();
        return "Último cambio deshecho.";
    }

    // Ver estado actual
    @GetMapping("/{id}")
    public Order verPedido(@PathVariable Long id) {
        return pedidosDB.get(id);
    }
}