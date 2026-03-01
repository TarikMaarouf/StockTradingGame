package entities;

public class StockPurchase {
    private final int id;
    private final int portfolioId;
    private final String stockSymbol;
    private int numbersOfShares;

    public StockPurchase(int id, int portfolioId, String stockSymbol, int numbersOfShares) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.stockSymbol = stockSymbol;
        this.numbersOfShares = numbersOfShares;
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

    public int getNumbersOfShares() {
        return numbersOfShares;
    }

    public void setNumbersOfShares(int numbersOfShares) {
        this.numbersOfShares = numbersOfShares;
    }
}
