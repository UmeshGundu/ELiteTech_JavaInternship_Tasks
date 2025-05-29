import java.io.*;

public class FileHandler {
    public static void main(String[] args) throws IOException {
        String filePath = "sample.txt";

        // Write to a file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("Hello, world!\nWelcome to File Handling.");
        writer.close();

        // Read from a file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        System.out.println("Reading File:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        // Append to file
        BufferedWriter appender = new BufferedWriter(new FileWriter(filePath, true));
        appender.write("\nAppended line.");
        appender.close();
    }
}
