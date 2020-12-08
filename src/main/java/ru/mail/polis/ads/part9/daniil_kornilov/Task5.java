package ru.mail.polis.ads.part9.daniil_kornilov;

import java.io.*;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/7981909

public class Task5 {
    private static int[] parent;
    private static int[] distance;
    private static ArrayList<ArrayDeque<Integer>> graph;

    private static void bfs(int start) {
        distance[start] = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.push(start);
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            for (Integer to : graph.get(v)) {
                if (distance[to] == -1) {
                    q.addLast(to);
                    distance[to] = distance[v] + 1;
                    parent[to] = v;
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        parent = new int[n + 1];
        distance = new int[n + 1];
        Arrays.fill(distance, -1);
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            graph.get(start).push(end);
            graph.get(end).push(start);
        }
        bfs(a);
        if (parent[b] == 0) {
            out.println("-1");
        } else {
            out.println(distance[b]);
            ArrayList<Integer> path = new ArrayList<>();
            path.add(b);
            while (parent[b] != 0) {
                b = parent[b];
                path.add(b);
            }
            for (int i = path.size() - 1; i >= 0; i--) {
                out.print(path.get(i) + " ");
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
