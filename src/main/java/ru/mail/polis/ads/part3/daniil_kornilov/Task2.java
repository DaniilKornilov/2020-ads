package ru.mail.polis.ads.part3.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7483896

public class Task2 {
    static class Heap {
        private final int[] heap;
        private int n;

        Heap() {
            heap = new int[1000000];
            n = 0;
            heap[n] = -1;
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && heap[j] < heap[j + 1]) {
                    ++j;
                }
                if (heap[k] >= heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && heap[k] > heap[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        void insert(int newElement) {
            ++n;
            heap[n] = newElement;
            swim(n);
        }

        int delMax() {
            int max = heap[1];
            swap(1, n);
            --n;
            sink(1);
            return max;
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < k; ++i) {
            switch (in.nextInt()) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.delMax());
                    break;
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
