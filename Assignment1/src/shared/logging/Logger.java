package shared.logging;

public class Logger {

    private static Logger instance;
    private LogOutput output;

    private Logger(){
        this.output = new ConsoleLogOutput();
    }
    public static synchronized  Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }

    public void setOutput(LogOutput output) {
        this.output = output;
    }
    public void log(LogLevel level, String message) {
        output.log(level, message);
    }
}
