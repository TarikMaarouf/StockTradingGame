package entities;

import java.time.LocalDateTime;

public class StockPriceHistory {

    private final int id;
    private final String stockSymbol;
    private double price;
    private LocalDateTime timestamp;

    public StockPriceHistory(int id, String stockSymbol, double price, LocalDateTime timestamp) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
