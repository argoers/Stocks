package stocks.model;

import com.opencsv.bean.CsvBindByPosition;

public class Day {

    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private String bid;

    @CsvBindByPosition(position = 2)
    private String ask;

    @CsvBindByPosition(position = 3)
    private String openingPrice;

    @CsvBindByPosition(position = 4)
    private String highPrice;

    @CsvBindByPosition(position = 5)
    private String lowPrice;

    @CsvBindByPosition(position = 6)
    private String closingPrice;

    @CsvBindByPosition(position = 7)
    private String averagePrice;

    @CsvBindByPosition(position = 8)
    private String totalVolume;

    @CsvBindByPosition(position = 9)
    private String turnover;

    @CsvBindByPosition(position = 10)
    private String trades;

    //  getters, setters, toString


    public String getDate() {
        return date;
    }

    public String getBid() {
        return bid;
    }

    public String getAsk() {
        return ask;
    }

    public String getOpeningPrice() {
        return openingPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public String getClosingPrice() {
        return closingPrice;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public String getTurnover() {
        return turnover;
    }

    public String getTrades() {
        return trades;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public void setOpeningPrice(String openingPrice) {
        this.openingPrice = openingPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setClosingPrice(String closingPrice) {
        this.closingPrice = closingPrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public void setTrades(String trades) {
        this.trades = trades;
    }

    @Override
    public String toString() {
        return "Day{" +
                "date='" + date + '\'' +
                ", bid='" + bid + '\'' +
                ", ask='" + ask + '\'' +
                ", openingPrice='" + openingPrice + '\'' +
                ", highPrice='" + highPrice + '\'' +
                ", lowPrice='" + lowPrice + '\'' +
                ", closingPrice='" + closingPrice + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", totalVolume='" + totalVolume + '\'' +
                ", turnover='" + turnover + '\'' +
                ", trades='" + trades + '\'' +
                '}';
    }
}
