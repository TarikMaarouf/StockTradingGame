package business.stockmarked.simulation;
import shared.configuration.AppConfig;

public class ResetState implements LiveStockState {

    @Override
    public double calculatePriceChange(LiveStock liveStock) {
        double resetValue = AppConfig.getInstance().getStockResetValue();

        liveStock.setState(new SteadyState());
        return resetValue - liveStock.getCurrentPrice();
    }

    @Override
    public String getName() {
        return "Reset";
    }
}