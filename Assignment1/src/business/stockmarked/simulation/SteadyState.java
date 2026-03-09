package business.stockmarked.simulation;
import java.util.Random;

public class SteadyState implements LiveStockState {
    private static final Random random = new Random(); // Statisk Random felt

    public double calculatePriceChange(LiveStock liveStock) {
        double change = random.nextDouble() - 0.5;

        double rand = random.nextDouble();
        if (rand < 0.05) {
            liveStock.setState(new GrowingState());
        } else if (rand < 0.10) {
            liveStock.setState(new DecliningState());
        }
        return change;
    }

    public String getName() { return "Steady"; }
}