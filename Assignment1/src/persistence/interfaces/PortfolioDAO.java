package persistence.interfaces;

import entities.Portfolio;

import java.util.List;

public interface PortfolioDAO {
    void create(Portfolio portfolio);
    void update(Portfolio portfolio);
    void delete(int id);
    Portfolio getById(int id);
    List<Portfolio> getAll();
}
