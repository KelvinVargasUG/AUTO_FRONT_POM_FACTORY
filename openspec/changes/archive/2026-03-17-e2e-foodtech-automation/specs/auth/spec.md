# Auth Specification

## Purpose

Validate the login flow of the FoodTech Restaurant Management App via E2E browser automation.

## Requirements

### Requirement: Successful Login

The system MUST allow a user with valid credentials to authenticate and reach the main dashboard.

#### Scenario: User logs in with valid credentials

- GIVEN the user navigates to the login page (`/login`)
- WHEN the user enters valid credentials (`demo@foodtech.com` / `demopass`)
- AND clicks the login button
- THEN the system redirects to the main view
- AND the user session persists (no re-login required on refresh)

#### Scenario: Main view is displayed after login

- GIVEN the user has logged in successfully
- WHEN the main view loads
- THEN the waiter dashboard elements are visible

### Requirement: Failed Login

The system MUST reject invalid credentials and display a clear error message without granting access.

#### Scenario: User logs in with invalid credentials

- GIVEN the user navigates to the login page
- WHEN the user enters invalid credentials (`wrong@foodtech.com` / `wrongpass`)
- AND clicks the login button
- THEN the system displays an error message
- AND the user remains on the login page

#### Scenario: User submits empty credentials

- GIVEN the user navigates to the login page
- WHEN the user leaves email and password fields empty
- AND clicks the login button
- THEN the system SHOULD display validation errors
- AND the user remains on the login page
