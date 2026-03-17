# Proposal: E2E FoodTech Automation — Login & Add Article to Table

## Intent

Port the FoodTech Restaurant Management App E2E tests (currently Cypress/TS) to a **Java 17 + Serenity BDD + Cucumber + Selenium** framework using the **Page Object Model** pattern with Selenium's **`@FindBy` (Page Factory)** annotation. Covers the two critical flows defined in the PDR: Login and Add Article to Table.

## Scope

### In Scope
- Gradle dependency setup (Serenity BDD, Serenity Cucumber, Selenium, WebDriverManager)
- Page Object Model with `@FindBy` annotations (Selenium Page Factory) extending Serenity `PageObject`
- Page objects: `LoginPage`, `WaiterViewPage`, `TableSelectorComponent`, `ProductGridComponent`
- Cucumber `.feature` files for 3 scenarios (login ok, login fail, add product)
- Step definitions using Serenity `@Steps` actors delegating to page objects
- Serenity WebDriver management (Chrome headless, Docker-ready)
- Cucumber test runner with `@CucumberOptions` and tag filtering
- Serenity aggregate reporting (`serenity-reports`)

### Out of Scope
- CI/CD GitHub Actions pipeline (next change)
- Docker compose setup (next change)
- Additional flows beyond Login + Add Article
- API-level testing

## Approach

1. **Infrastructure**: Add Serenity BDD, Serenity Cucumber, Selenium 4 to `build.gradle`. Apply `serenity-bdd` Gradle plugin.
2. **POM Layer**: Pages extend Serenity `PageObject`. Elements located via `@FindBy` annotations (Selenium Page Factory pattern). `PageFactory.initElements()` handled by Serenity lifecycle.
3. **BDD Layer**: `.feature` files translate PDR Gherkin verbatim. Step definitions use `@Steps`-annotated action classes that delegate to page objects — zero UI logic in steps.
4. **Execution**: Serenity manages WebDriver lifecycle. Chrome headless via `serenity.conf`. Serenity generates rich HTML reports with screenshots on failure.

## Affected Areas

| Area | Impact | Description |
|------|--------|-------------|
| `app/build.gradle` | Modified | Add Serenity BDD, Cucumber, Selenium deps + serenity plugin |
| `gradle/libs.versions.toml` | Modified | Version catalog entries |
| `app/src/test/java/com/automation/pages/` | New | Page objects with `@FindBy` extending Serenity `PageObject` |
| `app/src/test/java/com/automation/steps/` | New | Serenity `@Steps` action classes + Cucumber glue |
| `app/src/test/java/com/automation/runner/` | New | Serenity-Cucumber test runner |
| `app/src/test/resources/features/` | New | `.feature` files |
| `app/src/test/resources/serenity.conf` | New | Serenity + WebDriver configuration |

## Risks

| Risk | Likelihood | Mitigation |
|------|------------|------------|
| FoodTech app URL unavailable during tests | High | Externalize base URL via env var / properties |
| UI selectors break with FoodTech updates | Med | Use data-testid attributes where possible, centralize selectors in page objects |
| ChromeDriver version mismatch | Low | Serenity auto-manages WebDriver via config |

## Rollback Plan

Delete added test directories and revert `build.gradle` / `libs.versions.toml` changes. No production code is modified — rollback is purely subtractive.

## Dependencies

- FoodTech app running and accessible (URL configurable)
- Chrome browser available (or headless in Docker)

## Success Criteria

- [ ] `./gradlew test` executes all 3 Cucumber scenarios via Serenity runner
- [ ] Login success scenario passes with valid credentials
- [ ] Login failure scenario validates error message
- [ ] Add article scenario validates table summary update
- [ ] All page objects use `@FindBy` annotations and extend Serenity `PageObject`
- [ ] Step definitions use Serenity `@Steps` pattern
- [ ] `./gradlew aggregate` generates Serenity HTML report
- [ ] Tests run headless without manual intervention
