package healthcalc;

import java.util.Scanner;
import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.model.HealthCalc;
import healthcalc.model.HealthCalcImpl;
public class Main {
    public static void main(String[] args) {
        HealthCalc calc = new HealthCalcImpl();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter weight (kg): ");
            double p = sc.nextDouble();
            System.out.print("Enter height (cm): ");
            double a = sc.nextDouble();
            double imc = calc.bmi(p, a);
            System.out.println("Your BMI is: " + String.format("%.3f", imc) + " (" + calc.bmiClassification(imc) + ")");
            System.out.print("Enter your gender (H = male, M = female): ");
            char gender = sc.next().toUpperCase().charAt(0);
            double ibw = calc.idealBodyWeight(a, gender);
            System.out.println("Your IBW is: " + String.format("%.3f", ibw));
            System.out.print("Enter your waist circumference (cm): ");
            double waist = sc.nextDouble();
            String wcRisk = calc.wcClassification(waist, gender);
            System.out.println("Your Cardiovascular Risk is: " + wcRisk);
        } catch (InvalidHealthDataException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
        
    }
}


