package stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws FileNotFoundException {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		// Retrieve the StockInfo bean from the Spring context
		StockInfo stockInfo = context.getBean(StockInfo.class);
		stockInfo.loadStockInfo("C:\\Users\\arxer\\Desktop\\stocks\\stocks\\src\\main\\java\\stocks\\FSKRS-2022-07-19-2023-07-19.csv");
		AnalysisFunctions analysisFunctions = new AnalysisFunctions(stockInfo);
		List<Double> expMovingAverages = analysisFunctions.exponentialMovingAverage(stockInfo.getAveragePrices(), 10);
		// expMovingAverages.forEach(System.out::println);

	}
}
