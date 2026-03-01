package persistence.fileimplementation;

import entities.*;
import persistence.interfaces.UnitOfWork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUnitOfWork implements UnitOfWork {
    private final String directoryPath;

    // Object field variable til brug som lock ved multithreading
    private static final Object FILE_WRITE_LOCK = new Object();

    // Vores lister over data
    private List<Stock> stocks = null;
    private List<Portfolio> portfolios = null;
    private List<StockPurchase> stockPurchases = null;

    // Constructor der modtager sti og sikrer at filerne findes
    public FileUnitOfWork(String directoryPath) {
        this.directoryPath = directoryPath;
        try {
            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            new File(dir, "stocks.txt").createNewFile();
            new File(dir, "portfolios.txt").createNewFile();
            new File(dir, "stock_purchases.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- LÆS FRA FILER (GET METODER) ---

    public List<Stock> getStocks() {
        if (stocks == null) {
            stocks = new ArrayList<>();
            try {
                for (String line : Files.readAllLines(Path.of(directoryPath + "/stocks.txt"))) {
                    String[] p = line.split("\\|");
                    stocks.add(new Stock(p[0], p[1], Double.parseDouble(p[2]), p[3]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stocks;
    }

    public List<Portfolio> getPortfolios() {
        if (portfolios == null) {
            portfolios = new ArrayList<>();
            try {
                for (String line : Files.readAllLines(Path.of(directoryPath + "/portfolios.txt"))) {
                    String[] p = line.split("\\|");
                    portfolios.add(new Portfolio(Integer.parseInt(p[0]), Double.parseDouble(p[1])));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return portfolios;
    }

    public List<StockPurchase> getStockPurchases() {
        if (stockPurchases == null) {
            stockPurchases = new ArrayList<>();
            try {
                for (String line : Files.readAllLines(Path.of(directoryPath + "/stock_purchases.txt"))) {
                    String[] p = line.split("\\|");
                    stockPurchases.add(new StockPurchase(Integer.parseInt(p[0]), Integer.parseInt(p[1]), p[2], Integer.parseInt(p[3])));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stockPurchases;
    }

    // --- UNIT OF WORK METODER ---

    @Override
    public void beginTransaction() {
        clearData();
    }

    @Override
    public void rollback() {
        clearData();
    }

    @Override
    public void commit() {
        // Vi synkroniserer på vores lock for at undgå race conditions
        synchronized (FILE_WRITE_LOCK) {
            try {
                if (stocks != null) {
                    List<String> lines = stocks.stream()
                            .map(s -> s.getSymbol() + "|" + s.getName() + "|" + s.getCurrentPrice() + "|" + s.getCurrentState())
                            .collect(Collectors.toList());
                    Files.write(Path.of(directoryPath + "/stocks.txt"), lines, StandardOpenOption.TRUNCATE_EXISTING);
                }

                if (portfolios != null) {
                    List<String> lines = portfolios.stream()
                            .map(p -> p.getId() + "|" + p.getCurrentBalance())
                            .collect(Collectors.toList());
                    Files.write(Path.of(directoryPath + "/portfolios.txt"), lines, StandardOpenOption.TRUNCATE_EXISTING);
                }

                if (stockPurchases != null) {
                    List<String> lines = stockPurchases.stream()
                            .map(sp -> sp.getId() + "|" + sp.getPortfolioId() + "|" + sp.getStockSymbol() + "|" + sp.getNumbersOfShares())
                            .collect(Collectors.toList());
                    Files.write(Path.of(directoryPath + "/stock_purchases.txt"), lines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Clear the data, to reset the Unit of Work
            clearData();
        }
    }

    @Override
    public void clearData() {
        stocks = null;
        portfolios = null;
        stockPurchases = null;
    }
}