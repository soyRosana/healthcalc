
Feature: Clasificación del Perímetro Abdominal (WC)
  Como usuario de la calculadora HealthCalc
  Quiero clasificar el perímetro abdominal según el género
  Para obtener información sobre mi riesgo cardiovascular

  Background:
    Given la calculadora de salud está iniciada
    And el usuario ha seleccionado la métrica WC

  @EdgeCase
  Scenario: Perímetro en el límite inferior para hombres
    Given el perímetro abdominal es 94.0
    And el género es "H"
    When ejecuto la clasificación de WC
    Then el resultado debe ser "Riesgo Elevado"

  @EdgeCase
  Scenario: Perímetro en el límite inferior para mujeres
    Given el perímetro abdominal es 80.0
    And el género es "M"
    When ejecuto la clasificación de WC
    Then el resultado debe ser "Riesgo Elevado"

  @ErrorHandling
  Scenario Outline: Intento de clasificación con datos biológicamente imposibles
    Given el perímetro abdominal es <wc>
    And el género es "<genero>"
    When ejecuto la clasificación de WC
    Then el sistema debe lanzar una excepción

    Examples:
      | wc    | genero |
      | -10.0 | H      |
      | 0.0   | H      |
      | 29.9  | H      |
      | 250.1 | M      |
      | 500.0 | M      |
      | 90.0  | X      |

  
  @HighPriority
  Scenario Outline: Verificación de clasificaciones exitosas 
    Given el perímetro abdominal es <wc>
    And el género es "<genero>"
    When ejecuto la clasificación de WC
    Then el resultado debe ser "<resultado>"

    Examples:
      | wc    | genero | resultado          |
      | 93.9  | H      | Riesgo Bajo        |
      | 101.9 | H      | Riesgo Elevado     |
      | 102.0 | H      | Riesgo Muy Elevado |
      | 79.9  | M      | Riesgo Bajo        |
      | 87.9  | M      | Riesgo Elevado     |
      | 88.0  | M      | Riesgo Muy Elevado |