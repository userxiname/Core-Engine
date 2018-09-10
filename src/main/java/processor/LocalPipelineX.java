/**
 * Created by luxin on 6/22/17.
 */
package processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by luxin on 6/22/17.
 * Email: luxin0311@live.com
 */
public class LocalPipelineX {
    void save(String path, String fileName, String txt) {
        try {
            File file = new File(path + fileName);
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(txt);
            printWriter.flush();
            fileWriter.flush();
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
