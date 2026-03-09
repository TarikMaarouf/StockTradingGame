import business.stockmarked.simulation.MarketTicker;
import business.stockmarked.simulation.StockMarket;
import shared.logging.LogLevel;
import shared.logging.Logger;

public class RunApp {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log(LogLevel.INFO, "Application started");

        StockMarket market = StockMarket.getInstance();
        market.addStock("AAPL");
        market.addStock("MSFT");
        market.addStock("TSLA");

        MarketTicker ticker = new MarketTicker();
        Thread simulationThread = new Thread(ticker);

        simulationThread.setDaemon(true);
        simulationThread.start();

        System.out.println("Simulation kører... Tryk Enter for at stoppe.");

        new java.util.Scanner(System.in).nextLine();

        logger.log(LogLevel.INFO, "Application shutting down");
    }
}