package testSocketAndURL;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                //.version(HttpClient.Version.HTTP_2)
                //.followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://javarush.com/"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.headers());
        System.out.println(response.body());
    }
}
