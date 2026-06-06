package healthcalc;

public class EspanolIdiomaDecorator extends IdiomaBaseDecorator{
    public EspanolIdiomaDecorator(HealthHospital h) {
        super(h);
    }

    @Override
    public float[] indiceMasaCorporal(float a, int p) {
        float[] r = super.indiceMasaCorporal(a, p);

        float imc = r[0];
        float aMetros=r[1];
        float pesoKg = r[2];
        String aStr = String.format("%.2f", aMetros);
        String pStr = String.format("%.2f", pesoKg);
        String imcStr = String.format("%.2f", imc);
        System.out.println("La persona con altura " + aStr + " metros y " + pStr + " Kg tiene un IMC de " + imcStr + ".");
        
        return r;
    }
}
