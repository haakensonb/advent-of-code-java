import common.InputDownloader;
import common.Solution;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // TODO: move command line parsing logic to class and use tokens or use library
        if (args.length == 0 || args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println("Usage:\n");
            System.out.println("Download input files to current dir from day 1 up to and including <lastDay>:\n\tadvent-of-code-java download-input <year> <lastDay> <session>\n\t\texample year=2024, lastDay=25, session=sessionCookieFromAoC");
            System.out.println("Run all solutions for specific year:\n\tadvent-of-code-java run <year>");
            System.out.println("Run specific solution for specific year:\n\tadvent-of-code-java run <year> <day>");
        } else if (args.length == 2 && args[0].equals("run")) {
            String year = "year" + args[1];
            IntStream.rangeClosed(1, 25).forEach(i -> callSolution(year, i));
        } else if (args.length == 3 && args[0].equals("run")) {
            String year = "year" + args[1];
            int dayNum = Integer.parseInt(args[2]);
            callSolution(year, dayNum);
        } else if (args.length == 4 && args[0].equals("download-input")) {
            String year = args[1];
            int lastDayData = Integer.parseInt(args[2]);
            String sessionCookie = args[3];
            IntStream.rangeClosed(1, lastDayData).forEach(i -> {
                String location = InputDownloader.download(year, Integer.toString(i), sessionCookie);
                System.out.println("File downloaded to " + location);
            });
        }

    }

    private static void callSolution(String year, int dayNum) {
        Path path = Path.of("./inputs/", year, "input_" + dayNum + ".txt");
        try {
            String input = Files.readString(path);
            Solution daySolution = getSolution(year, dayNum);
            System.out.println(year + " Day" + dayNum + "\n\tPart1: " + daySolution.solvePart1(input) + "\n\tPart2: " + daySolution.solvePart2(input));
        } catch (ClassNotFoundException e) {
            System.out.println("No solution found for year=" + year + " day=" + dayNum);
        } catch (IOException e) {
            System.out.println("Error reading input file: " + path);
        } catch (NoSuchMethodException | SecurityException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Solution getSolution(String year, int dayNum) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String day = ".Day" + dayNum;
        Class<?> myClass = Class.forName(year + day + day);
        Constructor<?> constructor = myClass.getConstructor();
        Object instance = constructor.newInstance();
        return (Solution) instance;
    }
}

