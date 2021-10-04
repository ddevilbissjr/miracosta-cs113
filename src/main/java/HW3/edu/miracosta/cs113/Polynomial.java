package HW3.edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.Optional;

public class Polynomial {

    private LinkedList<Term> terms = new LinkedList<Term>();

    public Polynomial () {

    }

    public Polynomial(Polynomial poly) {
        for(Term t : poly.getTerms()) {
            terms.add(new Term(t.getCoefficient(), t.getExponent()));
        }
    }

    public void add(Polynomial poly) {
        Polynomial copy = new Polynomial(this);

        for(Term t : poly.getTerms()) {
            copy.addTerm(t);
        }

        System.out.println(this + " + " + poly + " = " + copy);
    }

    public void addTerm(Term secondTerm) {
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
                    int min = terms.getLast().getExponent();

                    for (int i = terms.size(); i > terms.size(); i--) { // checking min
                        int currentTermExp = terms.get(i).getExponent();

                        if (secondTermExp > currentTermExp) { // if the term's exp were adding is greater than our current min, set that as our new min.
                            min = currentTermExp;
                        }
                    }

                    if (min > secondTermExp) { // if the term's exp were adding is greater than all current terms, add to list.
                        terms.add(min, secondTerm);
                        break;
                    }
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

    public void clear () {
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
    public String toString () {
        String output = "";
        for(Term t : terms) {
            output += (t.getCoefficient() > 0 && terms.getFirst() != t ? "+" : "") + t.getCoefficient() + "x^" + t.getExponent();
        }
        return output;
    }
}
