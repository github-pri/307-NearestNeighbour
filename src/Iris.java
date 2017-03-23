/**
 * Created by Pri on 23/03/2017.
 */
public class Iris {

    private double sepalLength, sepalWidth, petalLength, petalWidth;
    private String type;

    public Iris (double sepalLength, double sepalWidth, double petalLength, double petalWidth,String type){
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.type = type;
    }

    public double getSepalLength(){
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() { return petalWidth; }

    public String getType(){ return type; }
}

