package ru.mail.polis.ads.part2.daniil_kornilov;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7424100

public class Task3 {
    private static int[] array;
    private static int counter;

    private static void swap(int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
        ++counter;
    }

    private static void sort() {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < array.length; ++i) {
                if (array[i] < array[i - 1]) {
                    swap(i, i - 1);
                    needIteration = true;
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        sort();
        out.print(counter);
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
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
