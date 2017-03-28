import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Pri on 23/03/2017.
 */
public class Main {

    private ArrayList<Iris> trainingSet = new ArrayList<Iris>();
    private ArrayList<Iris> testingSet = new ArrayList<Iris>();

    public void loadData (String train, String test){
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
                double sepalLength = scanTest.nextDouble();
                double sepalWidth = scanTest.nextDouble();
                double petalLength = scanTest.nextDouble();
                double petalWidth = scanTest.nextDouble();
                String type = scanTest.next();

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

        if (type.equals("sepalLength")){
            max = instances.get(0).getSepalLength();
            min = instances.get(0).getSepalLength();
            for (Iris i:instances){
                if (i.getSepalLength()>max){
                    max = i.getSepalLength();
                }
                else if (i.getSepalLength()<min){
                    min = i.getSepalLength();
                }
            }
        }

        else if (type.equals("sepalWidth")){
            max = instances.get(0).getSepalWidth();
            min = instances.get(0).getSepalWidth();
            for (Iris i:instances){
                if (i.getSepalWidth()>max){
                    max = i.getSepalWidth();
                }
                else if (i.getSepalWidth()<min){
                    min = i.getSepalWidth();
                }
            }
        }

        else if (type.equals("petalLength")){
            max = instances.get(0).getPetalLength();
            min = instances.get(0).getPetalLength();
            for (Iris i:instances){
                if (i.getPetalLength()>max){
                    max = i.getPetalLength();
                }
                else if (i.getPetalLength()<min){
                    min = i.getPetalLength();
                }
            }
        }

        else if (type.equals("petalWidth")){
            max = instances.get(0).getPetalWidth();
            min = instances.get(0).getPetalWidth();
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

        double sepalL = Math.pow((i1.getSepalLength() - i2.getSepalLength())/sLRange,2);
        double sepalW = Math.pow((i1.getSepalWidth() - i2.getSepalWidth())/swRange, 2);
        double petalL = Math.pow((i1.getPetalLength() - i2.getPetalLength())/pLRange,2);
        double petalW = Math.pow((i1.getPetalWidth() - i2.getPetalWidth())/pwRange,2);


        return Math.sqrt(sepalL+sepalW+petalL+petalW);
    }

    public HashMap<Double,String> getDistances(ArrayList<Iris> instances, Iris test){
        HashMap<Double,String> dists = new HashMap<Double, String>();
        for (Iris i: instances){
            double distance = calculateDistance(test,i);
            dists.put(distance,i.getType());
        }

        return dists;
    }

    public HashMap<Double,String> closest (int k, ArrayList<Double> distanceList, HashMap<Double, String> distances){
        HashMap<Double,String> result = new HashMap<Double,String>();
        int i = 0;
        while (i<k){
            result.put(distanceList.get(i), distances.get(i));
            i++;
        }
        return result;
    }

    public String sortClosest (int k, HashMap<Double,String> neighbours){
        HashMap<String,Integer> closeNeighbours = new HashMap<String,Integer>();
        String i;
        int j=0;
        for (Map.Entry<Double,String> mapEntry: neighbours.entrySet()){
            while(j<k){
                i = mapEntry.getValue();
                if(closeNeighbours.containsKey(i)){
                    int sort = closeNeighbours.get(i);
                    sort++;
                    closeNeighbours.put(mapEntry.getValue(),sort);
                }
                else{
                    closeNeighbours.put(mapEntry.getValue(),1);
                }
                j++;
                break;
            }
        }
        return (String) closeNeighbours.keySet().toArray()[0];
    }

    public String knnAlg (ArrayList<Iris> trainingList, Iris test, int k){
        HashMap<Double,String> distances = getDistances(trainingList,test);
        ArrayList<Double> distanceList = new ArrayList<Double>(distances.keySet());
        Collections.sort(distanceList);

        HashMap<Double,String> neighbours = closest(k,distanceList,distances);
        String result = sortClosest(k,neighbours);
        return result;
    }

    public double getAccuracy(ArrayList<Iris> instances, ArrayList<String> predictions){
        double correctPred = 0;
        for(int i=0;i<=instances.size()-1;i++){
            if(instances.get(i).getType().equals(predictions.get(i))){
                correctPred++;
            }
        }
        correctPred = (correctPred/instances.size())*100.00;
        return correctPred;
    }

    public static void main (String[] args){
        Main main  = new Main();
        main.loadData("iris-training.txt","iris-test.txt");
        ArrayList<String> predictions = new ArrayList<String>();
        for(Iris i: main.testingSet){
            String result = main.knnAlg(main.trainingSet,i,3);
            predictions.add(result);
        }
        double accuracy =  main.getAccuracy(main.testingSet,predictions);
        System.out.print("The algorithm is "+accuracy+"% accurate");

    }

}
