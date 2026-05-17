package healthcalc;



public abstract class ZonaBaseDecorator implements HealthHospital {
    private HealthHospital wHealthHospital;

    public ZonaBaseDecorator(HealthHospital h){
        this.wHealthHospital=h;

    }
    @Override
    public float[] indiceMasaCorporal(float altura, int peso){
        return wHealthHospital.indiceMasaCorporal(altura, peso);
    }
    @Override
    public int pesoCorporalIdeal(char genero, float altura){
        return wHealthHospital.pesoCorporalIdeal(genero, altura);
    }
}
