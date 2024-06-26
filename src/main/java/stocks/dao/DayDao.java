package stocks.dao;

import stocks.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DayDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void putDay(Day day) {
        String sql = "INSERT INTO stockInfo (date, bid, ask, openingPrice, highPrice, lowPrice, closingPrice, averagePrice, totalVolume," +
                " turnover, trades) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, day.getDate(), day.getBid().replace(',','.'), day.getAsk().replace(',','.'),
                day.getOpeningPrice().replace(',','.'), day.getHighPrice().replace(',','.'),
                day.getLowPrice().replace(',','.'), day.getClosingPrice().replace(',','.'),
                day.getAveragePrice().replace(',','.'), day.getTotalVolume(), day.getTurnover().replace(',','.'),
                day.getTrades());
    }

    public List<Day> getAllDays() {

        return jdbcTemplate.query("SELECT * FROM stockInfo", (resultSet, _) -> {
            Day day = new Day();
            day.setDate(resultSet.getString("date"));
            day.setBid(resultSet.getBigDecimal("bid").toString());
            day.setAsk(resultSet.getBigDecimal("ask").toString());
            day.setOpeningPrice(resultSet.getBigDecimal("openingPrice").toString());
            day.setHighPrice(resultSet.getBigDecimal("highPrice").toString());
            day.setLowPrice(resultSet.getBigDecimal("lowPrice").toString());
            day.setClosingPrice(resultSet.getBigDecimal("closingPrice").toString());
            day.setAveragePrice(resultSet.getBigDecimal("averagePrice").toString());
            day.setTotalVolume(String.valueOf(resultSet.getInt("totalVolume")));
            day.setTurnover(resultSet.getBigDecimal("turnover").toString());
            day.setTrades(String.valueOf(resultSet.getInt("trades")));
            return day;
        });

    }
}
