package stocks;

import stocks.dao.DayDao;
import stocks.model.Day;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class StockInfo {
    @Autowired
    DayDao dayDao;

    private List<Day> days;
    private List<Double> averagePrices = new ArrayList<>();

    public void loadStockInfo(String inputFile) throws FileNotFoundException {
        days = new CsvToBeanBuilder(new FileReader(inputFile))
                .withType(Day.class)
                .withSeparator(';')   // Use semicolon as the separator
                .withSkipLines(2)
                .build()
                .parse();
        for (Day day : days) {
            averagePrices.add(Double.parseDouble(day.getAveragePrice().replace(',','.')));
            dayDao.putDay(day);
        }


    }

    public List<Day> getDays() {
        return days;
    }

    public List<Double> getAveragePrices() {
        return averagePrices;
    }
}
