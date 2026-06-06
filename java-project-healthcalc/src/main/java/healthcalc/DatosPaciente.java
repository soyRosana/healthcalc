package healthcalc;

public class DatosPaciente {
    private float peso;
    private float altura;
    private char genero;
    private float imc;

    public DatosPaciente(float peso, float altura, char genero, float imc) {
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
        this.imc = imc;
    }

    // Getters para que el Proxy pueda calcular las medias después
    public float getPeso() { return peso; }
    public float getAltura() { return altura; }
    public char getGenero() { return genero; }
    public float getImc() { return imc; }
}