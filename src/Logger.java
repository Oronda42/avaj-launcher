package src;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Logger {
    private static Logger instance = null;
    BufferedWriter writer = null;
 
    private Logger() {

        if(instance != null)
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        else
        {
            try {
               writer = new BufferedWriter(new FileWriter("simulation.txt"));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            instance = this;
        }
    }

    public static Logger getInstance() {
        if(instance == null)
            instance = new Logger();
        return instance;
    }

    public void log(String message) {
        try {
            writer.write(message);
            writer.newLine();
            //System.out.println(message);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}

