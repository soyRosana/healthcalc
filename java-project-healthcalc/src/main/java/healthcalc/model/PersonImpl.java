package healthcalc.model;

public class PersonImpl implements Person {
    private float weight;
    private float height;
    private Gender gender;
    private int age;
    private float waist;

    public PersonImpl(float weight, float height, Gender gender, int age, float waist) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
        this.waist = waist;
    }

    @Override public float weight() { return weight; }
    @Override public float height() { return height; }
    @Override public Gender gender() { return gender; }
    @Override public int age() { return age; }
    @Override public float waist() { return waist; }
}