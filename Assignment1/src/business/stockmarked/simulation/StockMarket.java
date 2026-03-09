package business.stockmarked.simulation;

import shared.logging.Logger;
import shared.logging.LogLevel;
import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private static StockMarket instance;
    private List<LiveStock> stocks = new ArrayList<>();

    private StockMarket() {}

    public static synchronized StockMarket getInstance() {
        if (instance == null) instance = new StockMarket();
        return instance;
    }

    public void addStock(String symbol) {
        stocks.add(new LiveStock(symbol));
    }

    public void updateAllStocks() {
        for (LiveStock s : stocks) {
            s.updatePrice();
            Logger.getInstance().log(LogLevel.INFO, "Ticker: " + s.getSymbol() + " | Pris: " + s.getCurrentPrice() + " | Stat: " + s.getName());
        }
    }
}