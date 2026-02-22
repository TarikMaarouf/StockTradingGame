package shared.configuration;

public class AppConfig {

    private static AppConfig instance;

    private final int startingBalance;
    private final double transactionFee;
    private final int updateFrequencyInMs;
    private final double stockResetValue;



    private AppConfig() {
        this.startingBalance = 1000;
        this.transactionFee = 2.0;
        this.updateFrequencyInMs = 100;
        this.stockResetValue = 50.0;
    }
    public static synchronized  AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public int getStartingBalance() {
        return startingBalance;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public int getUpdateFrequencyInMs() {
        return updateFrequencyInMs;
    }

    public double getStockResetValue() {
        return stockResetValue;
    }
}
//hejhfdsgsg