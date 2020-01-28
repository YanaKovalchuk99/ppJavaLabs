package ru.spbstu.telematics.java;

import java.util.Random;

import ru.spbstu.telematics.java.MatrixService;

public class Main {

    private static final MatrixService matrixService = new MatrixService();

    public static void main(String[] args) throws InterruptedException {
        int size = 1000;
        int[][] first = createMatrix(size, size);
        int[][] second = createMatrix(size, size);

        long before = System.currentTimeMillis();
        matrixService.multiply(first, second);
        System.out.println("Result single thread: " + (System.currentTimeMillis() - before));

        testPerformanceForSizeAndThreads(first, second, 2);
        testPerformanceForSizeAndThreads(first, second, 4);
        testPerformanceForSizeAndThreads(first, second, 8);
        testPerformanceForSizeAndThreads(first, second, 16);
    }

    private static void testPerformanceForSizeAndThreads(int[][] first, int[][] second, int quantityOfThreads) throws InterruptedException {
        long before = System.currentTimeMillis();
        matrixService.multiplyMultiThreaded(first, second, quantityOfThreads);
        long duration = (System.currentTimeMillis() - before);
        System.out.println("Execution time (" + quantityOfThreads + " threads): " + duration);
    }

    private static int[][] createMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = rand.nextInt();
            }
        }
        return matrix;
    }

}
