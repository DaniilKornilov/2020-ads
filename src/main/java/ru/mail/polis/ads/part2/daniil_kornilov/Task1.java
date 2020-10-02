package ru.mail.polis.ads.part2.daniil_kornilov;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7435814

public class Task1 {
    private static int[] array;

    private static void sort(int begin, int end) {
        if (end <= begin) {
            return;
        }
        int middle = begin + (end - begin) / 2;
        sort(begin, middle);
        sort(middle + 1, end);

        int n = end - begin;
        int[] tmp = new int[n + 1];
        System.arraycopy(array, begin, tmp, 0, n + 1);

        int left = 0;
        int right = n / 2 + 1;

        for (int i = begin; i <= end; ++i) {
            if (left > n / 2) {
                array[i] = tmp[right];
                ++right;
            } else if (right > n) {
                array[i] = tmp[left];
                ++left;
            } else if (tmp[left] > tmp[right]) {
                array[i] = tmp[right];
                ++right;
            } else {
                array[i] = tmp[left];
                ++left;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        sort(0, array.length - 1);
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
