package HW6_RecursionTrees;

public class Coin {
    int pennies = 0;
    int dimes = 0;
    int nickles = 0;
    int quarters = 0;

    public Coin(Coin coin) {
        this.pennies = coin.pennies;
        this.dimes = coin.dimes;
        this.nickles = coin.nickles;
        this.quarters = coin.quarters;
    }

    public Coin() {

    }

    public void addPenny() {
        pennies++;
    }

    public void addQuarter() {
        quarters++;
    }

    public void addNickle() {
        nickles++;
    }

    public void addDime() {
        dimes++;
    }

    public String toString() {
        return pennies + "P " + dimes + "D " + nickles + "N " + quarters + "Q\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Coin)) {
            return false;
        }

        Coin c = (Coin) o;

        return pennies == c.pennies
                && dimes == c.dimes
                && nickles == c.nickles
                && quarters == c.quarters;
    }
}
