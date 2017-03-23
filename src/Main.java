import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pri on 23/03/2017.
 */
public class Main {

    private ArrayList<Iris> trainingSet = new ArrayList<Iris>();
    private ArrayList<Iris> testingSet = new ArrayList<Iris>();

    private void loadData (String train, String test){
        try {
            Scanner scanTrain = new Scanner(new File(train));
            Scanner scanTest = new Scanner(new File(test));

            while (scanTrain.hasNext()){
                double sepalLength = scanTrain.nextDouble();
                double sepalWidth = scanTrain.nextDouble();
                double petalLength = scanTrain.nextDouble();
                double petalWidth = scanTrain.nextDouble();
                String type = scanTrain.next();

                trainingSet.add(new Iris(sepalLength,sepalWidth,petalLength,petalWidth));
            }

            while (scanTest.hasNext()){
                double sepalLength = scanTrain.nextDouble();
                double sepalWidth = scanTrain.nextDouble();
                double petalLength = scanTrain.nextDouble();
                double petalWidth = scanTrain.nextDouble();
                String type = scanTrain.next();

                testingSet.add(new Iris(sepalLength,sepalWidth,petalLength,petalWidth));
            }
            scanTrain.close();
            scanTest.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }

}
