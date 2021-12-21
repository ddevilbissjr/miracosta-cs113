package HW3_LinkedList;

public class Term implements Comparable {

    private int coefficient, exponent;

    public Term () {
        coefficient = 0;
        exponent = 0;
    }

    public Term(String t) {
        if (t == null || t == "0") {
            return;
        }
        if (t.contains("x^")) {
            String[] str = t.split("x^");
            this.coefficient = Integer.parseInt(str[0]);
            this.exponent = Integer.parseInt(str[1]);
            return;
        }

        if(t.contains("+x")) {
            this.coefficient = 1;
        } else if (t.contains("+")) {
            t = t.substring(1);
        }
        if (t.contains("-x")) {
            this.coefficient = -1;
        }

        if (!t.contains("x^")) {
            this.coefficient = Integer.parseInt(t);
        }
        this.exponent = 1;
    }

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(Term original) {
        this.coefficient = original.getCoefficient();
        this.exponent = original.getExponent();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
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
        String str = "";
        if (coefficient == 0) {
            return str;
        }

        if (coefficient > 0) {
            str += "+";

            if(coefficient != 1) {
                str += coefficient;
            }
        } else if (coefficient < 0) {
            str += (coefficient == -1) ? "-" : coefficient;
        }

        if (exponent != 0) {
            str += "x";
            if (exponent != 1) {
                str += "^" + exponent;
            }
        }

        return str;
    }
}
