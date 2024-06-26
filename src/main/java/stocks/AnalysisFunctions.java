package stocks;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class AnalysisFunctions {
    private final StockInfo stockInfo;
    public AnalysisFunctions(StockInfo stockInfo){
        this.stockInfo = stockInfo;
    }

    public List<Double> simpleMovingAverage(List<Double> prices, int window) {
        List<Double> movingAverages = new ArrayList<>();
        for (int i = 0; i < window; i++) {
            movingAverages.add(0.0); // Initial values to maintain the list size
        }
        for (int i = window - 1; i < prices.size(); i++) {
            List<Double> averagesInWindow = prices.subList(i - window + 1, i + 1);
            OptionalDouble currentMovingAverage = averagesInWindow.stream().mapToDouble(Double::doubleValue).average();
            movingAverages.add(currentMovingAverage.orElse(0.0));
        }

        return movingAverages;
    }

    public List<Double> exponentialMovingAverage(List<Double> prices, int window) {
        double factor = (2.0/(window+1.0));
        List<Double> expMovingAverages = new ArrayList<>();

        for (int i = 0; i < window-1; i++) {
            expMovingAverages.add(0.0); // Initial values to maintain the list size
        }

        List<Double> averagesInWindow = prices.subList(0,window);
        OptionalDouble currentMovingAverage = averagesInWindow.stream().mapToDouble(Double::doubleValue).average();
        expMovingAverages.add(currentMovingAverage.orElse(0.0));

        for (int i = window; i < prices.size(); i++) {
            expMovingAverages.add(prices.get(i)*factor+expMovingAverages.get(i-1)*(1.0-factor));
        }

        return expMovingAverages;
    }

    public MACD estimateMACD(){
        List<Double> ema12 = exponentialMovingAverage(stockInfo.getAveragePrices(),12);
        List<Double> ema26 = exponentialMovingAverage(stockInfo.getAveragePrices(),26);

        List<Double> macdLine = new ArrayList<>();
        for (int i = 0; i < ema26.size(); i++) {
            macdLine.add(ema12.get(i)-ema26.get(i));
        }

        List<Double> signalLine = exponentialMovingAverage(macdLine,9);
        List<Double> difference = new ArrayList<>();
        for (int i = 0; i < signalLine.size(); i++) {
            difference.add(macdLine.get(i)-signalLine.get(i));
        }
        return new MACD(macdLine,signalLine,difference);
    }
}