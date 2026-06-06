Feature: Cálculo del Índice de Masa Corporal (BMI)
  Como usuario de la calculadora HealthCalc
  Quiero calcular el índice de masa corporal según el peso y la altura
  Para obtener mi indicador de estado nutricional

  Background:
    Given la calculadora de salud está iniciada
    And el usuario ha seleccionado la métrica BMI

  @HighPriority
  Scenario Outline: Cálculo exitoso del valor de BMI 
    Given el peso introducido es <peso>
    And la altura introducida es <altura>
    When ejecuto el cálculo de BMI
    Then el resultado numérico debe ser <resultado>

    Examples:
      | peso  | altura | resultado |
      | 62.5  | 168.0  | 22.14     |
      | 88.0  | 175.0  | 28.73     |
      | 70.0  | 175.0  | 22.86     |
      | 112.0 | 184.0  | 33.08     |
      | 85.0  | 180.0  | 26.23     |
      | 50.0  | 150.0  | 22.22     |

  @EdgeCase
  Scenario Outline: Cálculo del BMI en límites biológicos permitidos
    Given el peso introducido es <peso>
    And la altura introducida es <altura>
    When ejecuto el cálculo de BMI
    Then el resultado numérico debe ser <resultado>

    Examples:
      | peso  | altura | resultado |
      | 1.0   | 30.0   | 11.11     |
      | 700.0 | 300.0  | 77.78     |

  @ErrorHandling
  Scenario Outline: Intento de cálculo del BMI con datos biológicamente imposibles
    Given el peso introducido es <peso>
    And la altura introducida es <altura>
    When ejecuto el cálculo de BMI
    Then el sistema debe lanzar una excepción

    Examples:
      | peso  | altura |
      | -10.0 | 170.0  |
      | 70.0  | -170.0 |
      | 0.0   | 170.0  |
      | 70.0  | 0.0    |
      | 0.9   | 170.0  |
      | 700.1 | 170.0  |
      | 70.0  | 300.1  |
      | 70.0  | 29.9   |