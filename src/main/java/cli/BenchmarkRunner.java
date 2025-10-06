package cli;

import algorithms.MaxHeap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 100000};
        String outputFile = "benchmark_maxheap.csv";

        System.out.println("Running MaxHeap performance benchmark...\n");
        System.out.printf("%-10s %-15s %-15s%n", "N", "Insert (ms)", "Extract (ms)");
        System.out.println("--------------------------------------------");

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("N,Insert(ms),Extract(ms)\n");

            for (int n : sizes) {
                long insertTime = testInsertPerformance(n);
                long extractTime = testExtractPerformance(n);

                System.out.printf("%-10d %-15d %-15d%n", n, insertTime, extractTime);
                writer.write(String.format("%d,%d,%d\n", n, insertTime, extractTime));
            }

            System.out.println("\n Results saved to: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Тест вставки
    private static long testInsertPerformance(int n) {
        Random random = new Random();
        MaxHeap heap = new MaxHeap(n);

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            heap.insert(random.nextInt());
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    //  Тест извлечения
    private static long testExtractPerformance(int n) {
        Random random = new Random();
        int[] arr = random.ints(n, 0, Integer.MAX_VALUE).toArray();
        MaxHeap heap = new MaxHeap(arr);

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            heap.extractMax();
        }
        long end = System.currentTimeMillis();

        return end - start;
    }
}
