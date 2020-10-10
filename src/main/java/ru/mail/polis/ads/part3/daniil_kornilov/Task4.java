package ru.mail.polis.ads.part3.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7472965

public class Task4 {
    private static int[] array;

    private static boolean isInArray(int num) {
        int left = 1;
        int right = array.length - 1;
        while (left != right) {
            int center = (left + right) / 2;
            if (array[center] == num) {
                return true;
            }
            if (array[center] > num) {
                right = center;
            } else {
                left = center + 1;
            }
        }
        return array[left] == num;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        array = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            array[i] = in.nextInt();
        }
        for (int i = 0; i < q; ++i) {
            if (isInArray(in.nextInt())) {
                out.println("YES");
            } else {
                out.println("NO");
            }
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
