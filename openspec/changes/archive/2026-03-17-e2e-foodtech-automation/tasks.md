# Tasks: E2E FoodTech Automation — Login & Add Article to Table

## Phase 1: Infrastructure

- [x] 1.1 Update `gradle/libs.versions.toml` — add versions: serenity-core, serenity-cucumber, selenium-java, junit-vintage-engine, webdrivermanager
- [x] 1.2 Update `app/build.gradle` — apply `net.serenity-bdd.serenity-gradle-plugin`, add test dependencies from version catalog, configure `test` task for Cucumber, add `aggregate` task
- [x] 1.3 Create `app/src/test/resources/serenity.conf` — configure `webdriver.driver = chrome`, `webdriver.base.url`, `headless.mode = true`, default timeout
- [x] 1.4 Verify build compiles: `./gradlew clean build` passes with new dependencies

## Phase 2: Page Objects (@FindBy)

- [x] 2.1 Create `app/src/test/java/com/automation/pages/LoginPage.java` — extend `PageObject`, `@FindBy` for email, password, login button, error message. Methods: `enterCredentials()`, `clickLogin()`, `getErrorMessage()`, `isOnLoginPage()`
- [x] 2.2 Create `app/src/test/java/com/automation/pages/WaiterViewPage.java` — extend `PageObject`, `@FindBy` for table selector, product grid, add button, order summary. Methods: `selectTable()`, `selectProduct()`, `clickAddProduct()`, `getOrderSummary()`, `isConfirmationVisible()`

## Phase 3: @Steps Actors

- [x] 3.1 Create `app/src/test/java/com/automation/steps/LoginSteps.java` — inject `LoginPage`, `@Step` methods: `navigateToLogin()`, `enterCredentials(email, pass)`, `clickLogin()`, `verifyMainView()`, `verifyErrorMessage()`, `verifyStillOnLoginPage()`
- [x] 3.2 Create `app/src/test/java/com/automation/steps/WaiterSteps.java` — inject `WaiterViewPage`, `@Step` methods: `selectTable(name)`, `selectProduct(name)`, `addProduct()`, `verifyOrderSummaryUpdated()`, `verifyConfirmation()`

## Phase 4: BDD Layer (Features + Glue)

- [x] 4.1 Create `app/src/test/resources/features/login.feature` — Feature: Login. Two scenarios: valid credentials (Given/When/Then), invalid credentials (Given/When/Then). Use `@login` tag
- [x] 4.2 Create `app/src/test/resources/features/mesa.feature` — Feature: Mesa. One scenario: waiter adds product to table. Use `@mesa` tag
- [x] 4.3 Create `app/src/test/java/com/automation/stepdefinitions/LoginStepDefinitions.java` — `@Steps LoginSteps`, wire Cucumber `@Given/@When/@Then` annotations to actor methods
- [x] 4.4 Create `app/src/test/java/com/automation/stepdefinitions/WaiterStepDefinitions.java` — `@Steps WaiterSteps`, wire Cucumber annotations to actor methods

## Phase 5: Runner + Verification

- [x] 5.1 Create `app/src/test/java/com/automation/runner/CucumberTestRunner.java` — `@RunWith(CucumberWithSerenity.class)`, `@CucumberOptions(features, glue, plugin, tags)`
- [x] 5.2 Run `./gradlew clean test` — verify all 3 scenarios execute (pass or fail due to selectors — validates wiring)
- [x] 5.3 Run `./gradlew aggregate` — verify Serenity HTML report generates in `target/site/serenity/`
- [x] 5.4 Review: all pages extend `PageObject` + use `@FindBy`, all glue uses `@Steps`, zero UI logic in step definitions
