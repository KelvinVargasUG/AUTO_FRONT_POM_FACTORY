# Infrastructure Specification

## Purpose

Define requirements for the Serenity BDD + Cucumber + Selenium test framework setup using POM with `@FindBy` Page Factory.

## Requirements

### Requirement: Page Object Model with @FindBy

All page objects MUST extend Serenity `PageObject` and MUST use Selenium `@FindBy` annotations for element location.

#### Scenario: Page objects follow POM + @FindBy pattern

- GIVEN a page object class exists for each UI view
- WHEN the class is inspected
- THEN it extends `net.serenitybdd.core.pages.PageObject`
- AND all web elements are annotated with `@FindBy`

### Requirement: Serenity @Steps Pattern

Step definitions MUST delegate to `@Steps`-annotated action classes. Zero UI logic SHALL exist in Cucumber glue code.

#### Scenario: Step definitions use @Steps actors

- GIVEN a Cucumber step definition class
- WHEN the class is inspected
- THEN it delegates actions to fields annotated with `@Steps`
- AND does not directly instantiate WebDriver or page objects

### Requirement: Cucumber Test Runner

The project MUST have a Serenity-Cucumber runner that executes all `.feature` files.

#### Scenario: Gradle test task runs all scenarios

- GIVEN the project is built with `./gradlew test`
- WHEN the test task executes
- THEN all Cucumber scenarios run via the Serenity runner
- AND results are available for `./gradlew aggregate`

### Requirement: Externalized Configuration

The base URL and browser settings MUST be configurable via `serenity.conf` without code changes.

#### Scenario: Base URL is configurable

- GIVEN `serenity.conf` contains a `webdriver.base.url` property
- WHEN tests execute
- THEN all page navigations use the configured base URL
