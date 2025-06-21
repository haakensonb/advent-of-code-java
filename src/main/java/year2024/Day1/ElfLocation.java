package year2024.Day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElfLocation {
    private final List<Integer> list1;
    private final List<Integer> list2;

    public ElfLocation(String input) {
        this.list1 = new ArrayList<>();
        this.list2 = new ArrayList<>();

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] splitStr = line.split("\\s+");
            var leftNum = Integer.parseInt(splitStr[0]);
            var rightNum = Integer.parseInt(splitStr[1]);
            list1.add(leftNum);
            list2.add(rightNum);
        }
    }

    private int calcDistance(int leftNum, int rightNum) {
        return Math.abs(leftNum - rightNum);
    }

    public List<Integer> getDistances(){
        Collections.sort(list1);
        Collections.sort(list2);
        List<Integer> distances = new ArrayList<>();

        // Assumes that lists are the same length
        for (int i = 0; i < list1.size(); i++){
            var dist = calcDistance(list1.get(i), list2.get(i));
            distances.add(dist);
        }

        return distances;
    }

    public List<Integer> getSimilarityScores(){
        List<Integer> similarityScores = new ArrayList<>();
        for (Integer leftNum : list1) {
            var count = Collections.frequency(list2, leftNum);
            var score = leftNum * count;
            similarityScores.add(score);
        }
        return similarityScores;
    }
}