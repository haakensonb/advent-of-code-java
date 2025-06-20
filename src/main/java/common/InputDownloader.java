package common;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputDownloader {
    public static String download(String year, String day, String sessionCookie){
        String fullUrl = "https://adventofcode.com/" + year + "/day/" + day + "/input";
        String inputFilename = "input_" + day + ".txt";

        try {
            Path downloadPath = InputDownloader.createDirs(year).resolve(inputFilename);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(new URI(fullUrl))
                    .GET()
                    .header("Cookie", "session=" + sessionCookie + "; HttpOnly; Secure")
                    .build();
            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(downloadPath));
            return response.body().toString();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createDirs(String year){
        try {
            Path currentPath = Paths.get("").toAbsolutePath();
            Path inputsForYear = currentPath.resolve("inputs").resolve(year);
            Files.createDirectories(inputsForYear);
            return inputsForYear;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
