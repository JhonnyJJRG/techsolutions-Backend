package com.techsolutions.patterns.proxy;

import org.springframework.stereotype.Component;

@Component("servicioReal")
public class FinancialReportService implements ReportService {
    @Override
    public String generarReporteFinanciero(String usuario, String rol) {

        System.out.println("LOG: Generando reporte para " + usuario);
        return "REPORTE FINANCIERO CONFIDENCIAL\n" +
                "--------------------------------\n" +
                "Ingresos: $500,000\n" +
                "Egresos: $120,000\n" +
                "Utilidad Neta: $380,000\n" +
                "Firma Digital: " + usuario.hashCode();
    }
}