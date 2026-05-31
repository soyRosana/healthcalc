package healthcalc.model;

public enum BMICategory {
    SEVERE_THINNESS(0, 16),
    MODERATE_THINNESS(16, 17),
    MILD_THINNESS(17, 18.5f),
    NORMAL(18.5f, 25),
    OVERWEIGHT(25, 30),
    OBESE_CLASS_I(30, 35),
    OBESE_CLASS_II(35, 40),
    OBESE_CLASS_III(40, 1000);
    
    private final float ini;
    private final float fin;

    BMICategory(float ini, float fin) {
        this.ini = ini;
        this.fin = fin;
    }
    public float getIni() {
         return ini; 
        }

    public float getFin() { 
        return fin; 
    }
}