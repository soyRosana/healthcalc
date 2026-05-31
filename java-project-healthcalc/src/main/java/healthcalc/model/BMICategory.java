package healthcalc.model;

public enum BMICategory {
    SEVERE_THINNESS(0, 16, "Delgadez Severa"),
    MODERATE_THINNESS(16, 17, "Delgadez Moderada"),
    MILD_THINNESS(17, 18.5f, "Delgadez Leve"),
    NORMAL(18.5f, 25, "Normal"),
    OVERWEIGHT(25, 30, "Sobrepeso"),
    OBESE_CLASS_I(30, 35, "Obesidad I"),
    OBESE_CLASS_II(35, 40, "Obesidad II"),
    OBESE_CLASS_III(40, 1000, "Obesidad III");
    
    private final float ini;
    private final float fin;
    private final String strcat;

    BMICategory(float ini, float fin, String strcat) {
        this.ini = ini;
        this.fin = fin;
        this.strcat=strcat;
    }
    public float getIni() {
         return ini; 
        }

    public float getFin() { 
        return fin; 
    }
    public String getStrcat() { 
        return strcat; 
    }
}