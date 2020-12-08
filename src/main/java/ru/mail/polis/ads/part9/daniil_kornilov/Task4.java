package ru.mail.polis.ads.part9.daniil_kornilov;

import java.io.*;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/7981977

public class Task4 {
    private static int[] dist;
    private static final PriorityQueue<Edge> EDGES = new PriorityQueue<>(Comparator.comparing(x -> dist[x.vertex]));

    private static class Edge {
        int vertex;
        int weight;

        private Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        dist = new int[n + 1];
        int[] parent = new int[n + 1];
        boolean[] used = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        ArrayList<ArrayDeque<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int inside = in.nextInt();
            int outside = in.nextInt();
            int weight = in.nextInt();

            graph.get(inside).push(new Edge(outside, weight));
            graph.get(outside).push(new Edge(inside, weight));
        }
        EDGES.add(new Edge(s, 0));
        while (!EDGES.isEmpty()) {
            int v = EDGES.poll().vertex;
            for (Edge edge : graph.get(v)) {
                int newDist = dist[v] + edge.weight;
                if (!used[edge.vertex] && newDist < dist[edge.vertex]) {
                    dist[edge.vertex] = newDist;
                    EDGES.add(edge);
                    parent[edge.vertex] = v;
                }
                used[v] = true;
            }
        }
        if (dist[f] != Integer.MAX_VALUE) {
            out.println(dist[f]);
            ArrayDeque<Integer> res = new ArrayDeque<>();
            int prev = parent[f];
            while (prev != 0) {
                res.addFirst(prev);
                prev = parent[prev];
            }
            for (Integer i : res) {
                out.print(i + " ");
            }
            out.print(f);
        } else {
            out.println(-1);
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
