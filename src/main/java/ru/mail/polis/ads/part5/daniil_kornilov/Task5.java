package ru.mail.polis.ads.part5.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7549554

public class Task5 {
    private static int[] array;
    private static int n;

    private static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void printArray() {
        for (int i = 0; i < n - 1; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array[array.length - 1]);
    }

    private static void generateArray() {
        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }
    }

    private static void makeCombination() {
        if (n == 1) {
            return;
        }
        int pos1;
        for (pos1 = n - 2; pos1 >= 0; --pos1) {
            if (array[pos1] < array[pos1 + 1]) {
                break;
            }
        }
        if (pos1 == -1) {
            return;
        }
        for (int i = n - 1; i >= 0; --i) {
            if (array[i] >= array[pos1]) {
                swap(i, pos1);
                break;
            }
        }
        Arrays.sort(array, pos1 + 1, n);
        printArray();
    }

    private static void solve(final FastScanner in) {
        n = in.nextInt();
        array = new int[n];
        generateArray();
        printArray();
        int fact = 1;
        for (int i = 2; i <= n; ++i) {
            fact *= i;
        }
        for (int i = 0; i < fact; ++i) {
            makeCombination();
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter ignored = new PrintWriter(System.out)) {
            solve(in);
        }
    }
}
