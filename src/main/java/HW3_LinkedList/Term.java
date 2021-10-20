package HW3_LinkedList;

public class Term implements Comparable {

    private int coefficient, exponent;

    public Term(Term original) {
        this.coefficient = original.getCoefficient();
        this.exponent = original.getExponent();
    }

    public Term(String t) {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Term () {

    }

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public void addCoefficient(int coefficient) {
        this.coefficient += coefficient;
    }

    public void setAll(int c, int e) {
        this.coefficient = c;
        this.exponent = e;
    }

    public Term clone () {
        return new Term(this.getCoefficient(), this.getExponent());
    }

    @Override
    public String toString () {
        return coefficient + "x^" + exponent;
    }
}
