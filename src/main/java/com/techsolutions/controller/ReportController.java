package com.techsolutions.controller;

import com.techsolutions.patterns.proxy.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Endpoint seguro
    // Ejemplo: GET /api/reportes/financiero?usuario=Carlos&rol=GERENTE
    @GetMapping("/financiero")
    public ResponseEntity<String> obtenerReporte(@RequestParam String usuario, @RequestParam String rol) {
        try {
            String reporte = reportService.generarReporteFinanciero(usuario, rol);
            return ResponseEntity.ok(reporte);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ALERTA DE SEGURIDAD: " + e.getMessage());
        }
    }
}