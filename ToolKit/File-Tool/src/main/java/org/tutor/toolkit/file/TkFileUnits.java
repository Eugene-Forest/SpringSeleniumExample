package org.tutor.toolkit.file;

import java.io.*;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/12/9
 */
public class TkFileUnits {

    public static String readTextFile(File file) throws IOException {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                text.append(line);
                line = br.readLine();
            }
            br.close();//关闭流
        } catch (IOException e) {
            throw new IOException(e);
        }
        return text.toString();
    }

    public static void writeTextFile(File file, String text) throws IOException {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write(text);
            bw.flush();
            bw.close();//关闭流
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}