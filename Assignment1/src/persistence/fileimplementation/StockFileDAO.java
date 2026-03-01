package persistence.fileimplementation;
import entities.Stock;
import persistence.interfaces.StockDAO;
import java.util.List;

public class StockFileDAO implements StockDAO {
    private final FileUnitOfWork uow;

    public StockFileDAO(FileUnitOfWork uow) {
        this.uow = uow;
    }

    public void create(Stock s) {
        // Ingen nextId her, vi bruger bare det symbol aktien allerede har
        uow.getStocks().add(new Stock(s.getSymbol(), s.getName(), s.getCurrentPrice(), s.getCurrentState()));
    }

    public void update(Stock s) {
        List<Stock> list = uow.getStocks();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSymbol().equals(s.getSymbol())) {
                list.set(i, s);
                return;
            }
        }
    }

    public void delete(String symbol) {
        uow.getStocks().removeIf(s -> s.getSymbol().equals(symbol));
    }

    public Stock getById(String symbol) {
        return uow.getStocks().stream().filter(s -> s.getSymbol().equals(symbol)).findFirst().orElse(null);
    }

    public List<Stock> getAll() {
        return uow.getStocks();
    }
}