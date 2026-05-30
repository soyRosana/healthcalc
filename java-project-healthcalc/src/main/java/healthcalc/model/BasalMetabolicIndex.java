package healthcalc.model;

public interface BasalMetabolicIndex {
    float basalMetabolicIndex(Person person);
    BMICategory category(Person person);
}