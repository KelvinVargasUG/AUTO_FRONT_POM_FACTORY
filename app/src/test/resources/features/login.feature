@login
Feature: Login
  Como usuario del sistema FoodTech
  Quiero poder iniciar sesión
  Para acceder a las funcionalidades del sistema

  @login-exitoso
  Scenario: Usuario inicia sesión correctamente
    Given el usuario navega a la página de login
    When ingresa credenciales válidas
    And pulsa el botón de login
    Then accede al sistema y es redirigido a la vista principal

  @login-invalido
  Scenario: Usuario ingresa credenciales inválidas
    Given el usuario navega a la página de login
    When ingresa credenciales inválidas
    And pulsa el botón de login
    Then se muestra un mensaje de error
    And el usuario permanece en la página de login
