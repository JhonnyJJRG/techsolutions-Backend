package com.techsolutions.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Primary
public class ReportServiceProxy implements ReportService {

    private final ReportService servicioReal;
    private final List<String> rolesPermitidos = Arrays.asList("GERENTE", "CONTADOR");

    @Autowired
    public ReportServiceProxy(@Qualifier("servicioReal") ReportService servicioReal) {
        this.servicioReal = servicioReal;
    }

    @Override
    public String generarReporteFinanciero(String usuario, String rol) {
        System.out.println("PROXY: Verificando acceso para usuario: " + usuario + " con rol: " + rol);

        // RF4: Validación de seguridad
        if (rolesPermitidos.contains(rol.toUpperCase())) {
            System.out.println("PROXY: Acceso CONCEDIDO.");
            return servicioReal.generarReporteFinanciero(usuario, rol);
        } else {
            System.out.println("PROXY: Acceso DENEGADO.");
            throw new SecurityException("Acceso denegado: El rol " + rol + " no tiene permisos para ver reportes financieros.");
        }
    }
}