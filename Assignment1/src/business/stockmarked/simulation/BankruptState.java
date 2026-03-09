package business.stockmarked.simulation;

public class BankruptState implements LiveStockState {
    private int timer = 0;

    @Override
    public double calculatePriceChange(LiveStock liveStock) {

        timer++;

        if (timer > 5) {
            liveStock.setState(new ResetState());
        }
        return 0;
    }

    @Override
    public String getName() {
        return "Bankrupt";
    }
}