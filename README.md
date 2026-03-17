# AUTO_FRONT_POM_FACTORY

Proyecto de automatización E2E del sistema **FoodTech Restaurant Management App** usando **Serenity BDD + Cucumber + Selenium** con el patrón **Page Object Model (POM)** y **Page Factory** (`@FindBy`).

## Requisitos previos

- Java 17+
- Gradle 8.5+
- Google Chrome instalado
- App FoodTech corriendo en `http://localhost:5173`

## Ejecutar los tests

```bash
./gradlew clean test -Dwebdriver.base.url="http://localhost:5173"
```

## Escenarios cubiertos

| Escenario | Feature | Tipo |
|-----------|---------|------|
| Login con credenciales válidas | login.feature | Positivo |
| Login con credenciales inválidas | login.feature | Negativo |
| Mesero agrega orden a mesa | mesa.feature | Positivo |

## Estructura del proyecto

```
app/src/test/java/com/automation/
├── pages/               # Page Objects con @FindBy (Page Factory)
│   ├── LoginPage.java
│   └── WaiterViewPage.java
├── steps/               # Actores @Steps (lógica de prueba)
│   ├── LoginSteps.java
│   └── WaiterSteps.java
├── stepdefinitions/     # Glue Cucumber (sin lógica UI)
│   ├── LoginStepDefinitions.java
│   └── WaiterStepDefinitions.java
└── runner/
    └── CucumberTestRunner.java

app/src/test/resources/
├── features/
│   ├── login.feature
│   └── mesa.feature
└── serenity.conf        # Configuración del driver
```

## Reportes Serenity

Luego de ejecutar los tests, el reporte HTML está disponible en:

```
app/target/site/serenity/index.html
```
