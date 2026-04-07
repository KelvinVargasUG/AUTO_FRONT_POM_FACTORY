# AUTO_FRONT_POM_FACTORY

## Descripción

Proyecto de automatización de pruebas end-to-end sobre la interfaz web de **FoodTech** utilizando el patrón **Page Object Model (POM)** con **Page Factory**. Valida los flujos operativos del restaurante (autenticación, toma de pedidos, gestión de catálogo y carga masiva) interactuando directamente con el navegador.

## Enfoque de prueba

El patrón **POM con Page Factory** encapsula los elementos de cada página en clases dedicadas, separando la localización de elementos de la lógica de prueba. Esto facilita el mantenimiento cuando cambia la interfaz, ya que los cambios se aíslan en una sola clase por página.

## Escenarios cubiertos

| Feature | Escenarios | Tipo |
|---|---|---|
| **Login** | Inicio de sesión exitoso; Inicio de sesión con credenciales inválidas | Positivo / Negativo |
| **Mesa (Pedido del mesero)** | Mesero agrega orden a mesa | Positivo |
| **Catálogo de productos (Admin)** | Crear producto; Visualizar catálogo; Editar producto; Desactivar producto; Validación nombre vacío al crear y editar; Producto desactivado no visible en catálogo público | Positivo / Negativo |
| **Carga masiva de productos** | Pantalla de carga; Carga exitosa CSV; Rechazo por cabeceras incorrectas; Rechazo por tamaño excedido; Descarga de reporte de errores; Productos cargados visibles en catálogo | Positivo / Negativo |

**Total: 16 escenarios**

## Diferencia con AUTO_FRONT_SCREENPLAY

Ambos proyectos prueban la misma aplicación web pero con patrones de diseño diferentes. Este proyecto usa **POM/Page Factory** (orientado a páginas), mientras que AUTO_FRONT_SCREENPLAY usa **Screenplay** (orientado a actores). Además, este proyecto cubre los flujos de **login y toma de pedidos en mesa**, mientras que el otro cubre **registro de usuarios y facturación**.

## Requisitos previos

- Java 17 o superior
- Google Chrome instalado
- FoodTech-Kitchen-Services corriendo en `http://localhost:8080`
- FoodTech-Front corriendo en `http://localhost:5173`
- Ejecutar el script de datos iniciales: [seed_data.sh](scripts/seed_data.sh)

## Comandos disponibles

```bash
./gradlew clean test                                          # Ejecutar todos los tests
./gradlew clean test -Dwebdriver.base.url="http://localhost:5173"  # Especificar URL base
```

## Reportes

Los reportes de Serenity se generan en:

```
app/target/site/serenity/index.html
```

---

Proyecto académico — Sofka Technologies — 2026
