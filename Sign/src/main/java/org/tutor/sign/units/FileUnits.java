package org.tutor.sign.units;

import java.io.*;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
public class FileUnits {

    public static String readTextFile(File file) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line!=null){
                text.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text.toString();
    }

    public static void writeTextFile(File file, String text) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
