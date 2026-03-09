package business.stockmarked.simulation;
import java.util.Random;

public class DecliningState implements LiveStockState {
    private static final Random random = new Random();

    @Override
    public double calculatePriceChange(LiveStock liveStock) {
        double change = (random.nextDouble() * -2.5) + 0.5;

        if (random.nextDouble() < 0.15) {
            liveStock.setState(new SteadyState());
        }
        return change;
    }

    @Override
    public String getName() {
        return "Declining";
    }
}