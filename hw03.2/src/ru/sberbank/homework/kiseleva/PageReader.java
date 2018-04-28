package ru.sberbank.homework.kiseleva;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class PageReader {
    public void readPage(String urlAddress) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(new URL(urlAddress).openStream()))) {
            String string = reader.readLine();
            while (string != null) {
                System.out.println(string);
                string = reader.readLine();
            }
        }
    }
}
