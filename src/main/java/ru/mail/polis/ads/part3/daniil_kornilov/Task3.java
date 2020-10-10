package ru.mail.polis.ads.part3.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7484172

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(Integer.MIN_VALUE);
        minHeap.offer(Integer.MAX_VALUE);
        int median = -1;
        int newElement;
        while (true) {
            try {
                newElement = in.nextInt();
            } catch (Exception ex) {
                break;
            }
            if (median == -1 && maxHeap.size() != 0 && minHeap.size() != 0) {
                if (newElement < maxHeap.peek()) {
                    median = maxHeap.poll();
                    maxHeap.offer(newElement);
                } else if (newElement > minHeap.peek()) {
                    median = minHeap.poll();
                    minHeap.offer(newElement);
                } else {
                    median = newElement;
                }
            } else {
                if (newElement < median) {
                    minHeap.offer(median);
                    maxHeap.offer(newElement);
                } else {
                    maxHeap.offer(median);
                    minHeap.offer(newElement);
                }
                median = -1;
            }
            if (maxHeap.size() != 0 && minHeap.size() != 0) {
                out.println(median == -1 ? maxHeap.peek() + (minHeap.peek() - maxHeap.peek()) / 2 : median);
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
