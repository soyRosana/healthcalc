package healthcalc;

import java.util.Scanner;

import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.model.HealthCalc;
import healthcalc.model.HealthCalcImpl;
public class Main {
    public static void main(String[] args) {
        HealthCalc calc = HealthCalcImpl.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.print("Bienvenido a HealthCalc. Elige una opción (1 o 2): \n");
        System.out.println("1. Realizar cálculos básicos\n");
        System.out.println("2. Ver los tests de los patrones\n");
        int n=0;
        try {
            n=sc.nextInt();
        }catch(Exception e){
            System.out.println("Input no válido");
            sc.close();
            return;
        }
        try {
            if (n == 1) {

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
            }else if (n==2){

            

            // apartado 3.a (patrón adapter)
        
            System.out.println("\n --- PRUEBA DEL PATRÓN ADAPTER (HOSPITAL) ---\n");
            
            HealthHospital hospital = new HealthHospitalAdapter();
            
            // Datos fijos para comprobar que funciona (Modo Cliente)
            float alturaHospital = 1.70f; // Metros
            int pesoHospital = 72500;     // Gramos

            float[] resHospital = hospital.indiceMasaCorporal(alturaHospital, pesoHospital);
            int pesoIdealHospital = hospital.pesoCorporalIdeal('M', alturaHospital); // Usamos 'M' por ejemplo

            System.out.println("Datos enviados por Hospital: " + alturaHospital + "m, " + pesoHospital + "g");
            System.out.println("Resultado IMC Adaptado: " + resHospital[0]);
            System.out.println("Peso Ideal Adaptado: " + pesoIdealHospital + " kg");

            //apartado 3b (patrón proxy)

            System.out.println("\n --- PRUEBA DEL PATRÓN PROXY (ESTADÍSTICAS) ---\n");
            
            
            HealthStatsProxy proxy = new HealthStatsProxy();
            
            System.out.println("Registrando datos de 3 pacientes...");
            proxy.bmi(60.0, 160.0); // Paciente 1
            proxy.bmi(80.0, 180.0); // Paciente 2
            proxy.bmi(100.0, 170.0); // Paciente 3
            
            // Mostramos los resultados que el Proxy ha ido guardando
            System.out.println("Estadísticas acumuladas:");
            System.out.println("- Total pacientes: " + proxy.numTotalPacientes());
            System.out.println("- Peso medio: " + String.format("%.2f", proxy.pesoMedio()) + " kg");
            System.out.println("- Altura media: " + String.format("%.2f", proxy.alturaMedia()) + " cm");
            System.out.println("- IMC medio: " + String.format("%.2f", proxy.imcMedio()));

            //apartado 3.c (patrón decorator)
            System.out.println("\n --- PRUEBA DEL PATRÓN DECORATOR (VERSIÓN E IDIOMA) ---\n");
            HealthHospital h= new HealthHospitalAdapter();

            System.out.println("\n- Versión europea con mensaje en español:");
            HealthHospital euroEsp = new EspanolIdiomaDecorator(new EuropaZonaDecorator(h));
            euroEsp.indiceMasaCorporal(1.6f, 85000);

            System.out.println("\n- Versión europea con mensaje en inglés:");
            HealthHospital euroEng = new InglesIdiomaDecorator(new EuropaZonaDecorator(h));
            euroEng.indiceMasaCorporal(1.6f, 85000);

            System.out.println("\n- Versión americana con mensaje en español:");
            HealthHospital americaEsp = new EspanolIdiomaDecorator(new AmericaZonaDecorator(h));
            americaEsp.indiceMasaCorporal(6.3f, 192);

            System.out.println("\n- Versión americana con mensaje en inglés:");
            HealthHospital americaEng = new InglesIdiomaDecorator(new AmericaZonaDecorator(h));
            americaEng.indiceMasaCorporal(6.3f, 192);
            }
        } catch (InvalidHealthDataException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
        
    }
}


