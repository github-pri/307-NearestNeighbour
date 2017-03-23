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

                trainingSet.add(new Iris(sepalLength,sepalWidth,petalLength,petalWidth, type));
            }

            while (scanTest.hasNext()){
                double sepalLength = scanTrain.nextDouble();
                double sepalWidth = scanTrain.nextDouble();
                double petalLength = scanTrain.nextDouble();
                double petalWidth = scanTrain.nextDouble();
                String type = scanTrain.next();

                testingSet.add(new Iris(sepalLength,sepalWidth,petalLength,petalWidth,type));
            }
            scanTrain.close();
            scanTest.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    private double calculateRange(ArrayList<Iris> instances, String type){
        double min=0;
        double max=0;

        if (instances.isEmpty()){
            return 0;
        }

        if (type=="sepalLength"){
            max = instances.get(0).getSepalLength();
            min = max;
            for (Iris i:instances){
                if (i.getSepalLength()>max){
                    max = i.getSepalLength();
                }
                else if (i.getSepalLength()<min){
                    min = i.getSepalLength();
                }
            }
        }

        else if (type=="sepalWidth"){
            max = instances.get(0).getSepalWidth();
            min = max;
            for (Iris i:instances){
                if (i.getSepalWidth()>max){
                    max = i.getSepalWidth();
                }
                else if (i.getSepalWidth()<min){
                    min = i.getSepalWidth();
                }
            }
        }

        else if (type=="petalLength"){
            max = instances.get(0).getPetalLength();
            min = max;
            for (Iris i:instances){
                if (i.getPetalLength()>max){
                    max = i.getPetalLength();
                }
                else if (i.getPetalLength()<min){
                    min = i.getPetalLength();
                }
            }
        }

        else if (type=="petalWidth"){
            max = instances.get(0).getPetalWidth();
            min = max;
            for (Iris i:instances){
                if (i.getPetalWidth()>max){
                    max = i.getPetalWidth();
                }
                else if (i.getPetalWidth()<min){
                    min = i.getPetalWidth();
                }
            }
        }

        return (max - min);
    }

    public double calculateDistance(Iris i1, Iris i2){
        double sLRange = calculateRange(trainingSet,"sepalLength");
        double swRange = calculateRange(trainingSet, "sepalWidth");
        double pLRange = calculateRange(trainingSet, "petalLength");
        double pwRange = calculateRange(trainingSet, "petalWidth");

        double petalL = Math.pow((i1.getPetalLength() - i2.getPetalLength())/pLRange,2);
        double petalW = Math.pow((i1.getPetalWidth() - i2.getPetalWidth())/pwRange,2);
        double sepalL = Math.pow((i1.getSepalLength() - i2.getSepalLength())/sLRange,2);
        double sepalW = Math.pow((i1.getSepalWidth() - i2.getSepalWidth())/swRange, 2);

        return Math.sqrt(petalL + petalW + sepalL + sepalW);
    }

}
