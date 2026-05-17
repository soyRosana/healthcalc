package healthcalc;

public class AmericaZonaDecorator extends ZonaBaseDecorator{
    public AmericaZonaDecorator(HealthHospital h) {
        super(h);
    }

    @Override
    public float[] indiceMasaCorporal(float pies, int libras) {
        //Asumiendo la conversión: 1pie=12pulgadas
        float pulgadas = pies* 12;
        float metros= pulgadas/39.37f;
        int pesoG= Math.round((libras / 2.20462f)*1000f);
        return super.indiceMasaCorporal(metros, pesoG);
    }

    @Override
    public int pesoCorporalIdeal(char genero, float pies) {
        float pulgadas = pies* 12;
        float metros= pulgadas/39.37f;
        int pesoIdKg = super.pesoCorporalIdeal(genero, metros);
        return Math.round(pesoIdKg * 2.20462f);
    }
}
