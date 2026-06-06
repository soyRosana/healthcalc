Feature: Cálculo del Ideal Body Weight (IBW)
Como usuario de la calculadora HealthCalc

Quiero calcular mi peso ideal según mi altura y género

Para tener una referencia de mi peso saludable

Background:
Given la calculadora de salud está iniciada
And el usuario ha seleccionado la métrica IBW

@HighPriority
Scenario Outline: Cálculo exitoso del peso ideal para hombres y mujeres
Given la altura introducida es <altura>
And el género es "<genero>"
When ejecuto el cálculo de IBW
Then el resultado numérico debe ser <resultado>

Examples:
| altura | genero | resultado |
| 150.0  | H      | 50.0      |
| 150.0  | M      | 50.0      |
| 195.0  | H      | 83.75     |
| 195.0  | M      | 72.5      |
| 145.0  | H      | 46.25     |
| 145.0  | M      | 47.5      |

@EdgeCase
Scenario Outline: Cálculo del IBW en límites biológicos permitidos
Given la altura introducida es <altura>
And el género es "<genero>"
When ejecuto el cálculo de IBW
Then el resultado numérico debe ser <resultado>

Examples:

| altura | genero | resultado |
| 30.0   | H      | -40.0     |
| 30.0   | M      | -10.0     | 
| 300.0  | H      | 162.5     |
| 300.0  | M      | 125.0     |

@ErrorHandling
Scenario Outline: Intento de cálculo con datos inválidos

@InvalidHeight
Scenario Outline: Cálculo con altura fuera de rango
Given la altura introducida es <altura>
And el género es "<genero>"
When ejecuto el cálculo de IBW
Then el sistema debe lanzar una excepción
Examples:
  | altura | genero | 
  | 29.9   | H      | 
  | 300.1  | M      |
  | 0.0    | H      | 
  | -150.0 | M      |
  | 500.0  | H      | 

@InvalidGender
Scenario Outline: Cálculo con género inválido
Given la altura introducida es <altura>
And el género es "<genero>"
When ejecuto el cálculo de IBW
Then el sistema debe lanzar una excepción

Examples:
  | altura | genero |
  | 170.0  | X      | 
  | 170.0  | Varon  | 
  | 170.0  | q      | 
  | 170.0  | 1      | 
  | 170.0  | l      | 