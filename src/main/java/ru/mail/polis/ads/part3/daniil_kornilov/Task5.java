package ru.mail.polis.ads.part3.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7472841

public class Task5 {
    private static int[] array;
    private static int k;
    private static int n;

    private static boolean isCorrect(int x) {
        int cowCounter = 1;
        int lastCow = array[0];
        for (int i = 0; i < n; i++) {
            int currentCow = array[i];
            if (currentCow - lastCow >= x) {
                cowCounter += 1;
                lastCow = currentCow;
            }
        }
        return cowCounter >= k;
    }

    private static int findMaxDistance(int min, int max) {
        int left = 0;
        int right = max - min + 1;
        while (right - left != 1) {
            int middle = (left + right) / 2;
            if (isCorrect(middle)) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return left;
    }

    private static int findMin() {
        int min = array[0];
        for (int i = 1; i < n; ++i) {
            min = Math.min(array[i], min);
        }
        return min;
    }

    private static int findMax() {
        int max = array[0];
        for (int i = 1; i < n; ++i) {
            max = Math.max(array[i], max);
        }
        return max;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        out.println(findMaxDistance(findMin(), findMax()));
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
