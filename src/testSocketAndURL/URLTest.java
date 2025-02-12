package testSocketAndURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://httpforever.com");
        InputStream inputStream = url.openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = bufferedReader.readLine()) != null) {
            System.out.println(line);

        }
    }
}
