import common.InputDownloader;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hwllo world");

        String command = args[0];
        if (command.equals("download")) {
            String year = args[1];
            int lastDayData = Integer.parseInt(args[2]);
            String sessionCookie = args[3];
            IntStream.rangeClosed(1, lastDayData).forEach(i -> {
                String location = InputDownloader.download(year, Integer.toString(i), sessionCookie);
                System.out.println("File downloaded to " + location);
            });

        }
    }
}
