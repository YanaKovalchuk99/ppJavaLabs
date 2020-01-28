package ru.spbstu.telematics.java;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MatrixService {

    public int[][] multiply(int[][] first, int[][] second) {
        validateSizes(first, second);

        int x = first.length;
        int y = second[0].length;
        int z = second.length;

        int[][] result = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    result[i][j] += first[i][k] * second[k][j];
                }
            }
        }

        return result;
    }

    public int[][] multiplyMultiThreaded(int[][] first, int[][] second, int quantityOfThreads) throws InterruptedException {
        validateSizes(first, second);
        if (first.length < quantityOfThreads) {
            throw new IllegalArgumentException("Quantity of threads can not be more than quantity of rows");
        }

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        int x = first.length;
        int y = second[0].length;
        int z = second.length;

        int[][] result = new int[x][y];

        int currentLowerBound = 0;
        int step = x / quantityOfThreads;
        for (int th = 0; th < quantityOfThreads; ++th) {
            int currentUpperBound = currentLowerBound + 2 * step > x ? x : currentLowerBound + step;
            threadPoolExecutor.execute(new MultiplierThread(first, second, result, currentLowerBound, currentUpperBound));
            currentLowerBound += step;
        }

        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(10, TimeUnit.MINUTES);
        return result;
    }

    private void validateSizes(int[][] first, int[][] second) {
        if (first[0].length != second.length) {
            throw new IllegalArgumentException("Invalid sizes");
        }
    }

    class MultiplierThread implements Runnable {

        MultiplierThread(int[][] first, int[][] second, int[][] result, int lowerBound, int upperBound) {
            super();
            this.first = first;
            this.second = second;
            this.result = result;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        private int[][] first;
        private int[][] second;
        private int[][] result;

        private int lowerBound;
        private int upperBound;

        @Override
        public void run() {
            int y = second[0].length;
            int z = second.length;
            for (int i = lowerBound; i < upperBound; i++) {
                for (int j = 0; j < y; j++) {
                    for (int k = 0; k < z; k++) {
                        result[i][j] += first[i][k] * second[k][j];
                    }
                }
            }
        }

    }

}
