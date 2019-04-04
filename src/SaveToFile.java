import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveToFile {
    public static void saveToFileInformation(String data) throws IOException {
        File destinationFile = new File("src/resources/resoults.txt");
        FileWriter fileWriter = new FileWriter(destinationFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(data);
        printWriter.close();
    }
}
