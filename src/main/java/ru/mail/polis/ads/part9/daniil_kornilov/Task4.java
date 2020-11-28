package ru.mail.polis.ads.part9.daniil_kornilov;

import java.io.InputStreamReader;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/7862132

public class Task4 {
    private static int[] dist;
    private static final PriorityQueue<Edge> EDGES = new PriorityQueue<>(Comparator.comparing(x -> dist[x.vertex]));

    private static class Edge {
        int vertex;
        int w;

        private Edge(int vertex, int w) {
            this.vertex = vertex;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int f = sc.nextInt();
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
            int in = sc.nextInt();
            int out = sc.nextInt();
            int weight = sc.nextInt();

            graph.get(in).push(new Edge(out, weight));
            graph.get(out).push(new Edge(in, weight));
        }
        EDGES.add(new Edge(s, 0));
        while (!EDGES.isEmpty()) {
            int v = EDGES.poll().vertex;
            for (Edge edge : graph.get(v)) {
                int newDist = dist[v] + edge.w;
                if (!used[edge.vertex] && newDist < dist[edge.vertex]) {
                    dist[edge.vertex] = newDist;
                    EDGES.add(edge);
                    parent[edge.vertex] = v;
                }
                used[v] = true;
            }
        }
        if (dist[f] != Integer.MAX_VALUE) {
            System.out.println(dist[f]);
            ArrayDeque<Integer> res = new ArrayDeque<>();
            int prev = parent[f];
            while (prev != 0) {
                res.addFirst(prev);
                prev = parent[prev];
            }
            for (Integer i : res) System.out.print(i + " ");
            System.out.print(f);
        } else {
            System.out.println(-1);
        }
    }
}
