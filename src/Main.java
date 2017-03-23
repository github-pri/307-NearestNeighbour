import java.util.Scanner;

/**
 * Created by Pri on 23/03/2017.
 */
public class Main {

    public static final String testSet = "iris-test.txt";
    public static final String trainSet = "iris-training.txt";

    public static void main (String[] args){
        System.out.print("What is your k value?");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        System.out.print("k: "+ k);
    }

}
