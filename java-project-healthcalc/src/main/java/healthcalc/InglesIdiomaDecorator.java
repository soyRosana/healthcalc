package healthcalc;

public class InglesIdiomaDecorator extends IdiomaBaseDecorator{
    public InglesIdiomaDecorator(HealthHospital h) {
        super(h);
    }

    @Override
    public float[] indiceMasaCorporal(float a, int p) {
        float[] r = super.indiceMasaCorporal(a, p);
        float imc = r[0];
        float pesoKg = p / 1000f;
        String aStr = String.format("%.2f", a);
        String pStr = String.format("%.2f", pesoKg);
        String imcStr = String.format("%.2f", imc);
        System.out.println("The person with height " + aStr + " metres and " + pStr + " Kg has a BMI of " + imcStr);
        
        return r;
    }
}
