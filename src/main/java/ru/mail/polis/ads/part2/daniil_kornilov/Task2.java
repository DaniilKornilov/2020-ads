package ru.mail.polis.ads.part2.daniil_kornilov;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7424028

public class Task2 {
    private static int[] array;

    private static void swap(int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    private static boolean compare(int j, int i) {
        return (array[j] % 10 == array[i] % 10) ? array[j] < array[i] : array[j] % 10 < array[i] % 10;
    }

    private static void sort() {
        for (int i = 0; i < array.length - 1; ++i) {
            int tempI = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (compare(j, tempI)) {
                    tempI = j;
                }
            }
            swap(i, tempI);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        sort();
        for (int i = 0; i < n; ++i) {
            out.write(array[i] + " ");
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
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
