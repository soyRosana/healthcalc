package healthcalc;

/**
 * Interfaz proporcionada por el Hospital Costa del Sol.
 * El hospital trabaja con metros (float) y gramos (int).
 */
public interface HealthHospital {
    public float[] indiceMasaCorporal(float altura, int peso);
    public int pesoCorporalIdeal(char genero, float altura);
}