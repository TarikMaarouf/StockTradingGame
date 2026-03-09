package business.stockmarked.simulation;

public interface LiveStockState {

    double calculatePriceChange(LiveStock liveStock);

    String getName();

}
