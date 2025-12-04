package com.techsolutions.controller;

import com.techsolutions.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Ejemplo: POST /api/inventario/actualizar?producto=Laptop&stock=5&minimo=10
    @PostMapping("/actualizar")
    public String actualizarStock(@RequestParam String producto,
                                  @RequestParam int stock,
                                  @RequestParam int minimo) {
        inventoryService.modificarStock(producto, stock, minimo);
        return "Stock actualizado. Revise la consola para ver alertas si el stock es bajo.";
    }
}