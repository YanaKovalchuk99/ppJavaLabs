package ru.spbstu.telematics.java;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import ru.spbstu.telematics.java.MatrixService;

public class MatrixServiceTest {

    private MatrixService matrixService = new MatrixService();

    private static final int[][] FIRST = new int[][] {{1, 2}, {3, 4}, {5, 6}};
    private static final int[][] SECOND = new int[][] {{10, 11, 12}, {14, 15, 16}};
    private static final int[][] RESULT = new int[][] {{38, 41, 44}, {86, 93, 100}, {134, 145, 156}};

    @Test
    public void testMultiplicationInvalidSizes() {
        final int[][] first = new int[3][2];
        final int[][] second = new int[3][2];

        Assert.assertThrows(IllegalArgumentException.class, () -> matrixService.multiply(first, second));
    }

    @Test
    public void testMultiplication() {
        int[][] result = matrixService.multiply(FIRST, SECOND);

        Assert.assertEquals(RESULT.length, result.length);
        Assert.assertEquals(RESULT[0].length, result[0].length);

        for (int i = 0; i < result.length; ++i) {
            Assert.assertArrayEquals(RESULT[i], result[i]);
        }
    }

    @Test
    public void testMultithreadedMultiplication() throws InterruptedException {
        int[][] result = matrixService.multiplyMultiThreaded(FIRST, SECOND, 3);

        Assert.assertEquals(RESULT.length, result.length);
        Assert.assertEquals(RESULT[0].length, result[0].length);

        for (int i = 0; i < result.length; ++i) {
            Assert.assertArrayEquals(RESULT[i], result[i]);
        }
    }

}

