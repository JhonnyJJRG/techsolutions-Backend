# Plataforma de Gestión Empresarial TechSolutions S.A.

## 📋 Descripción del Proyecto
Sistema integral de backend desarrollado en Java con Spring Boot para la gestión de PYMES. La plataforma centraliza operaciones de ventas, inventario y reportes financieros, resolviendo problemas de integración de pagos, seguridad y cálculo de precios mediante la aplicación estricta de **Patrones de Diseño de Software** (GoF) y principios **SOLID/GRASP**.

Este proyecto ha sido desarrollado como parte de la Evaluación Final del curso de Patrones de Diseño de Software.

## 👥 Integrantes del Equipo
* Rondon Gonzalez Jhonny Jesus
* Suyon Lescano Pablo
* Godoy Palacios Joaquin
* Pulache Arevalo Erick
* Ponce Huamali Ronaldo

## 🛠️ Stack Tecnológico
* **Lenguaje:** Java 17/21
* **Framework:** Spring Boot 3.4.x (Web, Data JPA)
* **Base de Datos:** H2 Database (En memoria para desarrollo/pruebas)
* **Herramientas:** Maven, Lombok, IntelliJ IDEA

## 🏗️ Arquitectura y Patrones de Diseño Aplicados

El proyecto implementa una arquitectura en capas siguiendo los principios GRASP (Alta Cohesión, Bajo Acoplamiento y Controlador). Se han aplicado los siguientes patrones para resolver los Requerimientos Funcionales (RF):

### 1. Patrón Adapter (Estructural)
* **Problema:** Incompatibilidad entre diferentes pasarelas de pago (PayPal, Yape, Plin).
* **Solución:** Se implementó una interfaz común `PaymentProcessor`. Los adaptadores encapsulan la lógica específica de cada proveedor.
* **Ubicación:** `src/main/java/com/techsolutions/patterns/adapter`

### 2. Patrón Strategy (Comportamiento)
* **Problema:** Necesidad de cambiar políticas de precios (Estándar, Descuento, Dinámico) en tiempo de ejecución sin modificar el código base.
* **Solución:** Se encapsularon los algoritmos de precios en clases separadas que implementan `PricingStrategy`. El servicio inyecta la estrategia seleccionada.
* **Ubicación:** `src/main/java/com/techsolutions/patterns/strategy`

### 3. Patrones Command y Memento (Comportamiento)
* **Problema:** Requerimiento de procesar pedidos y permitir "deshacer" cambios de estado (Undo).
* **Solución:**
    * **Command:** Encapsula las solicitudes (`PayOrderCommand`, `CancelOrderCommand`) como objetos.
    * **Memento:** Guarda el estado interno del pedido (`OrderMemento`) antes de ejecutar un comando, permitiendo restaurarlo.
* **Ubicación:** `src/main/java/com/techsolutions/patterns/command` y `memento`

### 4. Patrón Observer (Comportamiento)
* **Problema:** Notificar automáticamente a Gerencia y Compras cuando el stock es bajo.
* **Solución:** El `InventoryService` actúa como "Sujeto" y notifica a los suscriptores (`ManagerNotificationObserver`, `PurchasingNotificationObserver`) cuando el stock cae bajo el mínimo.
* **Ubicación:** `src/main/java/com/techsolutions/patterns/observer`

### 5. Patrón Proxy (Estructural - Seguridad)
* **Problema:** Proteger el acceso a reportes financieros sensibles.
* **Solución:** `ReportServiceProxy` intercepta las llamadas al servicio real, verificando si el usuario tiene el rol "GERENTE" o "CONTADOR" antes de permitir el acceso.
* **Ubicación:** `src/main/java/com/techsolutions/patterns/proxy`

---

## 🚀 Guía de Instalación y Ejecución

1.  **Clonar/Descargar:** Descargue el código fuente o clone el repositorio.
2.  **Abrir en IDE:** Abra la carpeta del proyecto en IntelliJ IDEA.
3.  **Cargar Dependencias:** Permita que Maven descargue las dependencias (Spring Web, JPA, Lombok).
4.  **Ejecutar:** Busque la clase principal `TechsolutionsApplication.java` y haga clic en `Run` (▶️).
5.  **Verificación:** La aplicación iniciará en el puerto `8080`.

---

## 🧪 Guía de Pruebas (Endpoints)

Puede probar la funcionalidad utilizando `curl` en la terminal o Postman importando las siguientes rutas:

### 1. Pagos (Adapter)

# Pagar con Yape
curl.exe -X POST "http://localhost:8080/api/pagos/procesar?pasarela=yape&monto=50.00"

# Deshabilitar PayPal (Admin)
curl.exe -X POST "http://localhost:8080/api/pagos/configurar?pasarela=paypal&habilitada=false"


### 2. Precios (Strategy)

# Calcular precio normal
curl.exe "http://localhost:8080/api/precios/calcular?precioBase=100"

# Cambiar a estrategia de Descuento
curl.exe -X POST "http://localhost:8080/api/precios/estrategia?nombre=descuento"

# Resultado del descuento
curl.exe "http://localhost:8080/api/precios/calcular?precioBase=100"

### 3. Pedidos y Deshacer (Command + Memento)

# 1. Crear Pedido
curl.exe -X POST "http://localhost:8080/api/pedidos/crear?id=1&cliente=Juan&total=100"
# 2. Pagar
curl.exe -X POST "http://localhost:8080/api/pedidos/1/pagar"
# 3. Deshacer (Vuelve a PENDIENTE)
curl.exe -X POST "http://localhost:8080/api/pedidos/deshacer"

### 4. Inventario (Observer)

# Actualizar stock bajo (Genera alertas en consola)
curl.exe -X POST "http://localhost:8080/api/inventario/actualizar?producto=Laptop&stock=2&minimo=5"

### 5. Reportes Seguros (Proxy)

# Acceso Denegado
curl.exe "http://localhost:8080/api/reportes/financiero?usuario=Pepe&rol=PRACTICANTE"

# Acceso Permitido
curl.exe "http://localhost:8080/api/reportes/financiero?usuario=Maria&rol=GERENTE"
``