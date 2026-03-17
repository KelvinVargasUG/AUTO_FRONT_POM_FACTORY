# Design: E2E FoodTech Automation — Login & Add Article to Table

## Technical Approach

Build a Serenity BDD + Cucumber + Selenium test framework on the existing Gradle 8.5 / Java 17 scaffold. Pages use `@FindBy` (Selenium Page Factory) extending Serenity `PageObject`. Step definitions use `@Steps` actors. Serenity manages the WebDriver lifecycle and generates aggregate HTML reports.

## Architecture Decisions

### Decision: Serenity BDD as Test Orchestrator

| Option | Tradeoff | Decision |
|--------|----------|----------|
| Serenity BDD | Rich reports, built-in WebDriver mgmt, @Steps pattern | **Selected** — aligns with requirement |
| Plain Cucumber-JVM + Selenium | Lighter, more manual setup | Rejected — no reporting out of box |
| TestNG + Selenium | No BDD layer | Rejected — PDR requires Gherkin |

### Decision: @FindBy Page Factory over manual By locators

| Option | Tradeoff | Decision |
|--------|----------|----------|
| `@FindBy` annotations | Declarative, auto-initialized by Serenity | **Selected** — explicit requirement |
| Manual `driver.findElement(By.*)` | Flexible but verbose | Rejected — doesn't meet POM+PageFactory constraint |

### Decision: Serenity Cucumber 7 plugin

| Option | Tradeoff | Decision |
|--------|----------|----------|
| `serenity-cucumber` | Seamless Cucumber + Serenity integration | **Selected** |
| Separate Cucumber + Serenity wiring | Complex manual glue | Rejected |

### Decision: HOCON `serenity.conf` for configuration

| Option | Tradeoff | Decision |
|--------|----------|----------|  
| `serenity.conf` (HOCON) | Typed config, environment overrides | **Selected** — Serenity standard |
| `serenity.properties` | Flat key-value, limited structure | Rejected |

## Data Flow

```
Cucumber Runner (@CucumberOptions)
        │
        ▼
  .feature files (Gherkin scenarios)
        │
        ▼
  Step Definitions (Cucumber glue)
        │  uses @Steps
        ▼
  Action Classes (@Steps actors)
        │  delegates to
        ▼
  Page Objects (@FindBy + Serenity PageObject)
        │  Serenity manages
        ▼
  Selenium WebDriver (Chrome headless)
        │
        ▼
  FoodTech Web App (base URL from serenity.conf)
```

## File Changes

| File | Action | Description |
|------|--------|-------------|
| `gradle/libs.versions.toml` | Modify | Add serenity-bdd, serenity-cucumber, selenium versions |
| `app/build.gradle` | Modify | Add serenity plugin, deps, test config, aggregate task |
| `app/src/test/resources/serenity.conf` | Create | WebDriver config, base URL, headless Chrome |
| `app/src/test/resources/features/login.feature` | Create | Login success + failure Gherkin scenarios |
| `app/src/test/resources/features/mesa.feature` | Create | Add product to table Gherkin scenario |
| `app/src/test/java/com/automation/pages/LoginPage.java` | Create | `@FindBy` for email, password, login button, error msg |
| `app/src/test/java/com/automation/pages/WaiterViewPage.java` | Create | `@FindBy` for table selector, product grid, add button |
| `app/src/test/java/com/automation/steps/LoginSteps.java` | Create | `@Steps` actor for login actions |
| `app/src/test/java/com/automation/steps/WaiterSteps.java` | Create | `@Steps` actor for waiter actions |
| `app/src/test/java/com/automation/steps/LoginStepDefinitions.java` | Create | Cucumber glue for login.feature |
| `app/src/test/java/com/automation/steps/WaiterStepDefinitions.java` | Create | Cucumber glue for mesa.feature |
| `app/src/test/java/com/automation/runner/CucumberTestRunner.java` | Create | `@RunWith(CucumberWithSerenity.class)` runner |

## Interfaces / Contracts

```java
// Base pattern for all page objects
public class LoginPage extends PageObject {
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    public void enterCredentials(String email, String password) { ... }
    public void clickLogin() { ... }
    public String getErrorMessage() { ... }
    public boolean isOnLoginPage() { ... }
}
```

```java
// @Steps actor pattern
public class LoginSteps {
    LoginPage loginPage;  // Serenity auto-injects

    @Step("User enters credentials {0} / {1}")
    public void enterCredentials(String email, String password) {
        loginPage.enterCredentials(email, password);
    }
}
```

```java
// Cucumber glue
public class LoginStepDefinitions {
    @Steps
    LoginSteps loginSteps;

    @When("ingresa credenciales válidas")
    public void enterValidCredentials() {
        loginSteps.enterCredentials("demo@foodtech.com", "demopass");
    }
}
```

## Testing Strategy

| Layer | What to Test | Approach |
|-------|-------------|----------|
| E2E (primary) | Login success/failure, add product to table | Cucumber scenarios via Serenity runner |
| Reporting | Pass/fail evidence + screenshots | Serenity aggregate reports (`./gradlew aggregate`) |

No unit/integration layer needed — this project IS the test framework.

## Migration / Rollout

No migration required. Greenfield test project on an empty Gradle scaffold.

## Open Questions

- [ ] Confirm actual CSS/ID selectors for FoodTech UI elements (placeholders used in `@FindBy`)
- [ ] Confirm FoodTech base URL for `serenity.conf`
