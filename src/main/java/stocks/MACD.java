package stocks;

import java.util.List;

public record MACD(List<Double> MACDLine, List<Double> signalLine, List<Double> difference) {

    @Override
    public String toString() {
        return "MACD{" +
                "MACDLine=" + MACDLine +
                ", signalLine=" + signalLine +
                ", difference=" + difference +
                '}';
    }
}
