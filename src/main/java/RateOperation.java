import java.util.List;

public class RateOperation {

    public static Double averageRate(List<Double> rateDouble, int size) {
        double sumOfRates = 0;
        double averageRate;
        for (double rate : rateDouble) {
            sumOfRates += rate;
        }
        averageRate = sumOfRates / size;
        return averageRate;
    }

    public static Double maximumRate(List<Double> rateDoble, int size){
        double maximum = 0;
        for (double rate : rateDoble) {
            if (rate > maximum)
                maximum = rate;
        }
        return maximum;
    }

    public static Double minimumRate(List<Double> rateDoble, int size){
        double minimum = 0;
        for (double rate : rateDoble) {
            if (rate > minimum)
                minimum = rate;
        }
        return minimum;
    }

    public static Double amplitudeRate(List<Double> rateDoble, int size){
        double amplitude = maximumRate(rateDoble, size) - minimumRate(rateDoble,size);
        return amplitude;
    }

}
