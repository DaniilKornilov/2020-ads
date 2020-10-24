package ru.mail.polis.ads.part5.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7549897

public class Task3 {
    private static int[] d;
    private static int[] array;
    private static int n;

    private static int findMax() {
        int max = d[0];
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, d[i]);
        }
        return max;
    }

    private static void findD() {
        d[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (array[j] == 0) {
                    continue;
                }
                if (((array[i] % array[j] == 0) && (d[j] > d[i]))) {
                    d[i] = d[j];
                }
            }
            ++d[i];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        array = new int[n];
        d = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        findD();
        out.print(findMax());
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
