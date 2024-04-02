package for_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ForStream {

    static int SIZE = 50_000_000;
    static int BOUND = 1_000;
    static Random random = new Random();

    public static void test() {
        forArray();
        streamArray();
        parallelStreamArray();
        forArrayList();
        streamArrayList();
        parallelStreamArrayList();
    }

    public static void forArray() {
        int[] array = new int[SIZE];
        for (int i=0;i < SIZE;i++) {
            array[i] = random.nextInt(BOUND);
        }

        long start = System.currentTimeMillis();

        long sum = 0;
        for (int n: array) {
            if (n % 3 == 0) {
                sum += n;
            }
        }

        long finish = System.currentTimeMillis();
        System.out.println("for int[]: " + (finish - start) + "ms");
    }

    public static void streamArray() {
        int[] array = new int[SIZE];
        for (int i=0;i < SIZE;i++) {
            array[i] = random.nextInt(BOUND);
        }

        long start = System.currentTimeMillis();

        long sum = Arrays.stream(array).filter(x -> x % 3 == 0).sum();

        long finish = System.currentTimeMillis();
        System.out.println("stream int[]: " + (finish - start) + "ms");
    }

    public static void parallelStreamArray() {
        int[] array = new int[SIZE];
        for (int i=0;i < SIZE;i++) {
            array[i] = random.nextInt(BOUND);
        }

        long start = System.currentTimeMillis();

        long sum = Arrays.stream(array).parallel().filter(x -> x % 3 == 0).sum();

        long finish = System.currentTimeMillis();
        System.out.println("parallel stream int[]: " + (finish - start) + "ms");
    }

    public static void forArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i < SIZE;i++) {
            list.add(random.nextInt(BOUND));
        }

        long start = System.currentTimeMillis();

        long sum = 0;
        for (Integer n: list) {
            if (n % 3 == 0) {
                sum += n;
            }
        }

        long finish = System.currentTimeMillis();
        System.out.println("for ArrayList<Integer>: " + (finish - start) + "ms");
    }

    public static void streamArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i < SIZE;i++) {
            list.add(random.nextInt(BOUND));
        }

        long start = System.currentTimeMillis();

        long sum = list.stream().filter(x -> x % 3 == 0).mapToInt(x -> x).sum();

        long finish = System.currentTimeMillis();
        System.out.println("stream ArrayList<Integer>: " + (finish - start) + "ms");
    }

    public static void parallelStreamArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i < SIZE;i++) {
            list.add(random.nextInt(BOUND));
        }

        long start = System.currentTimeMillis();

        long sum = list.stream().parallel().filter(x -> x % 3 == 0).mapToInt(x -> x).sum();

        long finish = System.currentTimeMillis();
        System.out.println("parallel stream ArrayList<Integer>: " + (finish - start) + "ms");
    }
}
