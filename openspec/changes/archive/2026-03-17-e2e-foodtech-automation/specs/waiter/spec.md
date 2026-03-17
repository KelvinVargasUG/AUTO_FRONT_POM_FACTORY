# Waiter Specification

## Purpose

Validate the "Add article to table" flow for the Waiter role in the FoodTech Restaurant Management App.

## Requirements

### Requirement: Add Product to Table

The system MUST allow a logged-in waiter to select a table, choose a product, and add it to the table order.

#### Scenario: Waiter adds a product to an available table

- GIVEN the user is logged in and on the waiter dashboard
- WHEN the user selects an available table ("Mesa Demo")
- AND selects a product from the menu ("Producto Demo")
- AND clicks the add-product button
- THEN the table order summary updates to include the product
- AND a visual confirmation is displayed

#### Scenario: Table summary reflects added product details

- GIVEN the waiter has added "Producto Demo" to "Mesa Demo"
- WHEN the table summary is displayed
- THEN the product name and quantity MUST appear in the summary

### Requirement: Waiter Navigation

The system MUST provide access to the table view from the main dashboard after login.

#### Scenario: Waiter accesses table view

- GIVEN the user has logged in successfully
- WHEN the waiter dashboard loads
- THEN the table selector component is visible
- AND at least one table is available for selection
