package business.stockmarked.simulation;

import shared.configuration.AppConfig;

public class MarketTicker implements Runnable {
    public void run() {
        StockMarket market = StockMarket.getInstance();

        int frequency = AppConfig.getInstance().getUpdateFrequencyInMs();

        while (true) {
            market.updateAllStocks();
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}