package business.stockmarked.simulation;

import shared.configuration.AppConfig;

public class LiveStock {
    private final String symbol;
    private double currentPrice;
    private LiveStockState currentState;

    public LiveStock(String symbol) {
        this.symbol = symbol;
        this.currentPrice = AppConfig.getInstance().getStockResetValue();
        this.currentState = new SteadyState();
    }

    public void updatePrice() {
        double priceChange = currentState.calculatePriceChange(this);
        currentPrice += priceChange;

        if (currentPrice <= 0) {
            currentPrice = 0;
            setState(new BankruptState());
        }
    }

    public void setState(LiveStockState newState) {
        this.currentState = newState;
    }

    public String getSymbol() { return symbol; }
    public double getCurrentPrice() { return currentPrice; }
    public String getName() { return currentState.getName(); }
}