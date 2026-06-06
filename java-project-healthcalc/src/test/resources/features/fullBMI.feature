Feature: Clasificación  completa del Índice de Masa Corporal (Full BMI)
Como usuario de la calculadora HealthCalc
Quiero obtener mi categoría de peso según el valor de mi índice de Masa Corporal
Para conocer mi estado nutricional

    Background:
        Given la calculadora de salud está iniciada
        And el usuario ha seleccionado la métrica Full BMI

    @HighPriority 
    Scenario Outline: Categorización exitosa en todos los rangos
        Given un valor de BMI es <valor_bmi>
        When ejecuto el cálculo de Full BMI
        Then el resultado debe ser "<categoria>"

        Examples:
            | valor_bmi | categoria          |
            | 15.0      | Delgadez Severa    |
            | 16.5      | Delgadez Moderada  |
            | 18.0      | Delgadez Leve      |
            | 22.0      | Normal             |
            | 27.5      | Sobrepeso          |
            | 32.5      | Obesidad I         |
            | 37.5      | Obesidad II        |
            | 45.0      | Obesidad III       |

    @EdgeCase
    Scenario Outline: Verificación de los límites de las categorías
        Given un valor de BMI es <valor_bmi>
        When ejecuto el cálculo de Full BMI
        Then el resultado debe ser "<categoria>"

        Examples:
            | valor_bmi | categoria          |
            | 15.9      | Delgadez Severa    |
            | 16.0      | Delgadez Moderada  |
            | 18.4      | Delgadez Leve      |
            | 18.5      | Normal             |
            | 24.9      | Normal             |
            | 25.0      | Sobrepeso          |
            | 29.9      | Sobrepeso          |
            | 30.0      | Obesidad I         |
            | 39.9      | Obesidad II        |
            | 40.0      | Obesidad III       |

    @ErrorHandling
    Scenario Outline: Intento de clasificación con valores para bmi fuera de rango biológico (0,150)
        Given un valor de BMI es <valor_bmi>
        When ejecuto el cálculo de Full BMI
        Then el sistema debe lanzar una excepción

        Examples:
            | valor_bmi |
            | -1.0      |
            | 150.1     |
            | 200.0     |
