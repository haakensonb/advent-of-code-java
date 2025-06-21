package year2024.Day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    private final String input1 = "3   4\n4   3\n2   5\n1   3\n3   9\n3   3";

    @Test
    void solvePart1() {
        Day1 d = new Day1();
        var expected = "11";
        assertEquals(expected, d.solvePart1(this.input1));
    }

    @Test
    void solvePart2() {
        Day1 d = new Day1();
        var expected = "31";
        assertEquals(expected, d.solvePart2(this.input1));
    }
}