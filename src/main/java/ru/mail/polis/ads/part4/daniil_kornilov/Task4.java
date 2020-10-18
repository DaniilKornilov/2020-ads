package ru.mail.polis.ads.part4.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7535220

public class Task4 {
    private static int[] array;

    private static int findBestLadderWay(int n, int k) {
        array[0] = 0;
        array[n + 1] = 0;

        int j = 0;
        for (int i = 1; i <= n + 1; ++i) {
            int max = array[j];
            for (j = i - k; j < i; ++j) {
                if (j < 0) {
                    j = 0;
                }
                max = Math.max(array[j], max);
            }
            array[i] = max + array[i];
        }
        return array[n + 1];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        array = new int[n + 2];
        for (int i = 1; i <= n; ++i) {
            array[i] = in.nextInt();
        }
        int k = in.nextInt();
        out.print(findBestLadderWay(n, k));
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
