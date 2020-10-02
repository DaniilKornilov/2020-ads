package ru.mail.polis.ads.part2.daniil_kornilov;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7437387

public class Task5 {
    private static class Robot {
        private final int first;
        private final int second;

        Robot(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static Robot[] mergeSort(Robot[] robots, int size) {
        if (size <= 1) {
            return robots;
        }

        int middle = size / 2;
        Robot[] robots1 = Arrays.copyOfRange(robots, 0, middle);
        Robot[] robots2 = Arrays.copyOfRange(robots, middle, size);

        robots1 = mergeSort(robots1, middle);
        robots2 = mergeSort(robots2, size - middle);
        robots = merge(robots1, robots2, middle, size - middle);

        return robots;
    }

    private static Robot[] merge(Robot[] robots1, Robot[] robots2, int n, int m) {
        Robot[] arr = new Robot[n + m];

        int i = 0;
        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < n && secondIndex < m) {
            if (robots1[firstIndex].first <= robots2[secondIndex].first) {
                arr[i++] = robots1[firstIndex++];
            } else {
                arr[i++] = robots2[secondIndex++];
            }
        }
        while (firstIndex < n) {
            arr[i++] = robots1[firstIndex++];
        }
        while (secondIndex < m) {
            arr[i++] = robots2[secondIndex++];
        }
        return arr;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Robot[] a = new Robot[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Robot(in.nextInt(), in.nextInt());
        }
        a = mergeSort(a, n);
        for (int i = 0; i < n; i++) {
            out.println(a[i].first + " " + a[i].second);
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
