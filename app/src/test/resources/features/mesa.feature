@mesa
Feature: Mesa
  Como mesero del sistema FoodTech
  Quiero poder agregar una orden a una mesa
  Para gestionar los pedidos de los clientes

  @agregar-articulo
  Scenario: Mesero agrega orden a mesa
    Given el usuario inicia sesión y accede a la vista de mesas
    When selecciona la primera mesa disponible
    And agrega una nueva orden
    And ingresa el nombre del cliente "Cliente Demo"
    And ingresa el email del cliente "cliente@demo.com"
    And envía la orden a la cocina
    Then el sistema procesa la orden correctamente
