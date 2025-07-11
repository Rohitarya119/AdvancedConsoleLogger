import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdvancedConsoleLogger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create daily log file name
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String logFileName = "log-" + today + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName, true))) {
            System.out.println("🟡 Advanced Console Logger Started (type 'exit' to quit).");
            System.out.println("💾 Logs will be written to: " + logFileName);
            System.out.println("📌 Use format: info: msg, error: msg, warn: msg");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("🟢 Logging ended.");
                    break;
                }

                // Detect log level
                String level = "INFO";
                String message = input;

                if (input.contains(":")) {
                    String[] parts = input.split(":", 2);
                    level = parts[0].trim().toUpperCase();
                    message = parts[1].trim();
                }

                // Add timestamp
                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                writer.printf("[%s] [%s] %s%n", timestamp, level, message);
                writer.flush(); // Ensure immediate write
            }

        } catch (IOException e) {
            System.out.println("❌ Error writing logs: " + e.getMessage());
        }

        scanner.close();
    }
}
