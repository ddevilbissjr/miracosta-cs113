package HW3_LinkedList;

import java.util.LinkedList;
import java.util.Optional;

public class Polynomial {

    private LinkedList<Term> terms = new LinkedList<Term>();

    public Polynomial() {

    }

    public Polynomial(Polynomial poly) {
        for (Term t : poly.getTerms()) {
            terms.add(new Term(t.getCoefficient(), t.getExponent()));
        }
    }

    public void add(Polynomial poly) {
        Polynomial original = new Polynomial(this);

        for (Term t : poly.getTerms()) {
            this.addTerm(t);
        }

        System.out.println(original + " + " + poly + " = " + this);
    }

    public void addTerm(Term secondTerm) {
        if(secondTerm.getCoefficient() == 0) {
            return;
        }

        if (terms.size() == 0) {
            terms.add(secondTerm);
        } else {
            int secondTermExp = secondTerm.getExponent();
            while (true) {
                Optional<Term> equalToSecond = terms.stream().filter(o -> o.getExponent() == secondTermExp).findFirst();
                if (equalToSecond.isPresent()) {
                    equalToSecond.get().addCoefficient(secondTerm.getCoefficient());
                    break;
                }

                if (secondTermExp > terms.getLast().getExponent() && secondTermExp < terms.getFirst().getExponent()) {
                    Term minimum = terms.getLast();

                    for (int i = terms.size(); i > terms.size(); i--) { // checking min
                        Term currentTermCheck = terms.get(i);

                        if (secondTermExp > currentTermCheck.getExponent()) { // if the term's exp were adding is greater than our current min, set that as our new min.
                            minimum = currentTermCheck;
                        }
                    }

                    terms.add(terms.indexOf(minimum), secondTerm);
                    break;
                }

                if (secondTermExp < terms.getLast().getExponent()) {
                    terms.add(terms.size(), secondTerm);
                    break;
                }

                if (secondTermExp > terms.getFirst().getExponent()) {
                    terms.add(0, secondTerm);
                    break;
                }
            }
        }
    }

    public void clear() {
        terms.clear();
    }

    public Term getTerm(int i) {
        return terms.get(i);
    }

    public int getNumTerms() {
        return terms.size();
    }

    public LinkedList<Term> getTerms() {
        return terms;
    }

    public void setTerms(LinkedList<Term> terms) {
        this.terms = terms;
    }

    @Override
    public String toString() {
        String output = "";
        for (Term t : terms) {
            output += (t.getCoefficient() > 0 && terms.getFirst() != t ? "+" : "") + t.getCoefficient() + "x^" + t.getExponent();
        }
        return (output.equals("")) ? "0" : output;
    }
}
