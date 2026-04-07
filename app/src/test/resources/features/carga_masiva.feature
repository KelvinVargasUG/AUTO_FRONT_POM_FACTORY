@carga-masiva
Feature: Carga Masiva de Productos
  Como administrador del sistema FoodTech
  Quiero importar productos mediante un archivo CSV
  Para cargar múltiples productos de forma eficiente

  @vista-carga-masiva
  Scenario: Visualización de la pantalla de carga masiva
    Given el administrador inicia sesión y navega a la sección de carga masiva
    Then debería ver la zona de arrastre para subir el archivo CSV
    And debería ver el botón para descargar la plantilla CSV

  @subir-csv
  Scenario: Carga exitosa de productos mediante archivo CSV
    Given el administrador inicia sesión y navega a la sección de carga masiva
    When sube un archivo CSV con productos válidos
    Then debería ver el resumen de la carga con los productos procesados

  @csv-invalido
  Scenario: Rechazo de CSV con cabeceras incorrectas
    Given el administrador inicia sesión y navega a la sección de carga masiva
    When sube un archivo CSV con cabeceras incorrectas
    Then debería ver un mensaje de error indicando el problema con el formato

  @csv-tamano
  Scenario: Rechazo de archivo que supera el tamaño máximo
    Given el administrador inicia sesión y navega a la sección de carga masiva
    When intenta subir un archivo que supera el tamaño máximo permitido
    Then debería ver un mensaje indicando que el archivo es demasiado grande

  @reporte-errores
  Scenario: Enlace de descarga de reporte de errores disponible
    Given el administrador inicia sesión y navega a la sección de carga masiva
    When sube un archivo CSV con registros inválidos
    Then debería ver el enlace para descargar el reporte de errores

  @productos-visibles
  Scenario: Productos cargados quedan disponibles en el catálogo
    Given el administrador inicia sesión y navega a la sección de carga masiva
    When sube un archivo CSV con productos válidos
    Then los productos cargados deberían aparecer en el catálogo de productos
