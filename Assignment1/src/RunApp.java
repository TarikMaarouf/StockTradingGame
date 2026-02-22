import shared.logging.LogLevel;
import shared.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RunApp {
    public static void main(String[] args) {

        Logger logger = Logger.getInstance();

        logger.log(LogLevel.INFO, "Application started");
        logger.log(LogLevel.WARNING, "Stock not found in database");

        try {
            throw new Exception("Database connection failed");
        } catch (Exception e) {
            logger.log(LogLevel.ERROR, "Failed to save data: " + e.getMessage());
        }
    }
}