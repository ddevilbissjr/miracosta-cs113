package Midterm.Question5;

class RecursionA {
    public static int testNum = 717;
    public static int numSevens = 0;

    public static void main(String[] args) {
        System.out.println("Your number: " + testNum);
        getNumSevens(testNum);
        System.out.println("Sevens: " + numSevens);
    }

    public static int getNumSevens(int testNum){
        if (testNum != 0){
            if(testNum % 10 ==7){
                numSevens++;
            }
            testNum = testNum / 10;
            getNumSevens(testNum);
        }
        return 0;
    }
}
