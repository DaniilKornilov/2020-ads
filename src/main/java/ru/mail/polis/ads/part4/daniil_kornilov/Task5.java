package ru.mail.polis.ads.part4.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7535588

public class Task5 {
    private static int counter;

    public static void merge(int[] a, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                a[k] = leftArray[i];
                ++k;
                ++i;
            } else {
                a[k] = rightArray[j];
                ++k;
                ++j;
                counter += (left - i);
            }
        }
        while (i < left) {
            a[k] = leftArray[i];
            ++k;
            ++i;
        }
        while (j < right) {
            a[k] = rightArray[j];
            ++k;
            ++j;
        }
    }

    public static void mergeSort(int[] array, int n) {
        if (n <= 1) {
            return;
        }
        int mid = n / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[n - mid];

        System.arraycopy(array, 0, leftArray, 0, mid);
        if (n - mid >= 0) {
            System.arraycopy(array, mid, rightArray, 0, n - mid);
        }
        mergeSort(leftArray, mid);
        mergeSort(rightArray, n - mid);
        merge(array, leftArray, rightArray, mid, n - mid);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }
        counter = 0;
        mergeSort(array, n);
        out.print(counter);
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
