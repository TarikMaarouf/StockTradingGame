package persistence.fileimplementation;

import entities.Portfolio;
import persistence.interfaces.PortfolioDAO;
import java.util.List;

public class PortfolioFileDAO implements PortfolioDAO {
    private final FileUnitOfWork uow;
    private static int nextId = 1;

    public PortfolioFileDAO(FileUnitOfWork uow) {
        this.uow = uow;
        for (Portfolio p : uow.getPortfolios()) {
            if (p.getId() >= nextId) nextId = p.getId() + 1;
        }
    }

    public void create(Portfolio p) {
        uow.getPortfolios().add(new Portfolio(nextId++, p.getCurrentBalance()));
    }

    public void update(Portfolio p) {
        List<Portfolio> list = uow.getPortfolios();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == p.getId()) {
                list.set(i, p);
                return;
            }
        }
    }

    public void delete(int id) {
        uow.getPortfolios().removeIf(p -> p.getId() == id);
    }

    public Portfolio getById(int id) {
        return uow.getPortfolios().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Portfolio> getAll() {
        return uow.getPortfolios();
    }
}