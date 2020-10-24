package ru.mail.polis.ads.part5.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7571486

public class Task2 {
    private static long findRight(long w, long h, long n) {
        long right = 1;
        while ((right / w) * (right / h) < n) {
            right *= 2;
        }
        return right;
    }

    private static long findMaxDiplomas(long w, long h, long n) {
        long right = findRight(w, h, n);
        long left = right / 2;
        while (right - left > 1) {
            long middle = (left + right) / 2;
            if ((middle / w) * (middle / h) < n) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        long w = in.nextLong();
        long h = in.nextLong();
        long n = in.nextLong();
        out.print(findMaxDiplomas(w, h, n));
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
