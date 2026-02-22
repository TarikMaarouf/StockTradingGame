package entities;
import shared.TransactionType;

public class Transaction {

    private final int id;
    private final int portfolioId;
    private final String stockSymbol;
    private final TransactionType type;
    private final int quantity;
    private final double pricePerShare;
    private final double totalAmount;
    private final double fee;
    private final String timestamp;

    public Transaction(int id, int portfolioId, String stockSymbol, TransactionType type, int quantity, double pricePerShare, double totalAmount, double fee, String timestamp) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.stockSymbol = stockSymbol;
        this.type = type;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.totalAmount = totalAmount;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public TransactionType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getFee() {
        return fee;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
