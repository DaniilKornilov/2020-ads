package ru.mail.polis.ads.part5.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7549697

public class Task1 {
    private static double binarySearch(double c) {
        double right = 1e10;
        double left = 0;
        double middle;
        while (Math.abs(right - left) > 1e-10) {
            middle = (left + right) / 2;
            if (Math.pow(middle, 2) + Math.sqrt(middle) - c < 0) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        out.println(binarySearch(c));
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
