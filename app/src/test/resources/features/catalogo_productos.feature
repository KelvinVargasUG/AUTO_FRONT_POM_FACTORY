@catalogo @crud
Feature: Catálogo de Productos
  Como administrador del sistema FoodTech
  Quiero gestionar el catálogo de productos
  Para mantener el menú del restaurante actualizado

  @crear-producto
  Scenario: Creación exitosa de un producto nuevo
    Given el administrador inicia sesión y navega al catálogo de productos
    When completa el formulario con datos válidos y crea el producto
    Then debería ver el producto en la lista del catálogo

  @listar-productos
  Scenario: Visualización del catálogo con productos existentes
    Given el administrador inicia sesión y navega al catálogo de productos
    When el catálogo contiene productos registrados
    Then debería ver los productos listados con sus botones de acción

  @editar-producto
  Scenario: Edición exitosa de un producto existente
    Given el administrador inicia sesión y navega al catálogo de productos
    When hace clic en editar el primer producto y cambia el nombre
    Then debería ver el catálogo actualizado

  @desactivar-producto
  Scenario: Desactivación de un producto existente
    Given el administrador inicia sesión y navega al catálogo de productos
    When hace clic en desactivar el primer producto de la lista
    Then el producto debería estar listado como inactivo

  @validacion-crear
  Scenario: Creación de producto con nombre vacío muestra error
    Given el administrador inicia sesión y navega al catálogo de productos
    When intenta crear un producto sin completar el campo nombre
    Then debería ver un mensaje de validación del campo requerido

  @validacion-editar
  Scenario: Edición con nombre vacío muestra error de validación
    Given el administrador inicia sesión y navega al catálogo de productos
    When intenta guardar la edición borrando el nombre del primer producto
    Then debería ver un mensaje de validación del campo requerido

  @inactivo-oculto
  Scenario: Producto desactivado no aparece en el catálogo del cliente
    Given el administrador inicia sesión y navega al catálogo de productos
    When hace clic en desactivar el primer producto de la lista
    Then el producto desactivado no debería aparecer en el catálogo público
