package ru.mail.polis.ads.part4.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7536283

public class Task3 {
    private static int[] array1;
    private static int[] array2;
    private static int[][] result;

    private static int findMaxSubsequence(int n1, int n2) {
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                result[i][j] = (array1[i - 1] == array2[j - 1]) ? 1 + result[i - 1][j - 1] :
                        Math.max(result[i - 1][j], result[i][j - 1]);
            }
        }
        return result[n1][n2];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        array1 = new int[n1];
        for (int i = 0; i < n1; ++i) {
            array1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        array2 = new int[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = in.nextInt();
        }
        result = new int[n1 + 1][n2 + 1];
        for (int[] row : result) {
            Arrays.fill(row, 0);
        }
        out.print(findMaxSubsequence(n1, n2));
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
