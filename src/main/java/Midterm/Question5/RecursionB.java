package Midterm.Question5;

public class RecursionB {
    public static int difference = 0;

    public static void main(String[] args) {
        recursiveSubtract(7,4);
        System.out.println(difference);
    }

    public static int recursiveSubtract(int i, int j){
        if(i != j){
            i--;
            difference++;
            recursiveSubtract(i, j);
        }
        return 0;
    }
}
