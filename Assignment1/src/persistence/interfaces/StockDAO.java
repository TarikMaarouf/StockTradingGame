package persistence.interfaces;

import entities.Stock;

import java.util.List;

public interface StockDAO {
    void create(Stock stock);
    void update(Stock stock);
    void delete(String symbol);
    Stock getById(String symbol);
    List<Stock> getAll();
}
