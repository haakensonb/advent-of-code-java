package year2024.Day1;

import common.Solution;

public class Day1  implements Solution {
    @Override
    public String solvePart1(String input) {
        ElfLocation elfLocation = new ElfLocation(input);
        return Integer.toString(
                elfLocation.getDistances()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum());
    }

    @Override
    public String solvePart2(String input) {
        ElfLocation elfLocation = new ElfLocation(input);
        return Integer.toString(
                elfLocation.getSimilarityScores()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum());
    }
}
