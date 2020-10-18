package ru.mail.polis.ads.part4.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7517850

public class Task2 {
    private static int[][] array;
    private static char[] chars;
    private static int charIndex;

    private static void findMaxSeedsArray(int rows, int cols) {
        for (int i = 1; i <= rows; ++i) {
            for (int j = cols - 1; j >= 0; --j) {
                array[i][j] += Math.max(array[i - 1][j], array[i][j + 1]);
            }
        }
    }

    private static void findBestWay(int rows, int cols) {
        int rowIndex = rows;
        int colIndex = 0;
        while (rowIndex > 1 && colIndex < (cols - 1)) {
            if (array[rowIndex][colIndex + 1] > array[rowIndex - 1][colIndex]) {
                chars[charIndex] = 'R';
                ++charIndex;
                ++colIndex;
            } else {
                chars[charIndex] = 'F';
                ++charIndex;
                --rowIndex;
            }
        }
        while (rowIndex != 1) {
            chars[charIndex] = 'F';
            ++charIndex;
            --rowIndex;
        }
        while (colIndex != cols - 1) {
            chars[charIndex] = 'R';
            ++charIndex;
            ++colIndex;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = in.nextInt();
        int cols = in.nextInt();
        charIndex = 0;
        chars = new char[rows + cols];
        array = new int[rows + 1][cols + 1];
        for (int i = 1; i < rows + 1; ++i) {
            for (int j = 0; j < cols; ++j) {
                array[i][j] = in.nextInt();
            }
        }
        findMaxSeedsArray(rows, cols);
        findBestWay(rows, cols);
        for (int i = 0; i <= charIndex; ++i) {
            out.print(chars[i]);
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
