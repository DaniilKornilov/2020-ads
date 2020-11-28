package ru.mail.polis.ads.part9.daniil_kornilov;

import java.util.*;

//https://www.e-olymp.com/ru/submissions/7862152

public class Task5 {
    private static int[] parent;
    private static int[] dist;
    private static ArrayList<ArrayDeque<Integer>> graph;

    private static void bfs(int start) {
        dist[start] = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.push(start);
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            for (Integer to : graph.get(v)) {
                if (dist[to] == -1) {
                    q.addLast(to);
                    dist[to] = dist[v] + 1;
                    parent[to] = v;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        parent = new int[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph.get(start).push(end);
            graph.get(end).push(start);
        }
        bfs(a);
        if (parent[b] == 0)
            System.out.println("-1");
        else {
            System.out.println(dist[b]);
            ArrayList<Integer> path = new ArrayList<>();
            path.add(b);
            while (parent[b] != 0) {
                b = parent[b];
                path.add(b);
            }
            for (int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " ");
        }
    }
}
