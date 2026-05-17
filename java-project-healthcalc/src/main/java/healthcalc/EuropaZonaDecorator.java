package healthcalc;

public class EuropaZonaDecorator extends ZonaBaseDecorator{
    public EuropaZonaDecorator(HealthHospital h){
        super(h);
    }
    @Override
    public float[] indiceMasaCorporal(float a, int p){
        return super.indiceMasaCorporal(a, p);
    }
    @Override
    public int pesoCorporalIdeal(char g, float a){
        return super.pesoCorporalIdeal(g, a);
    }

}
