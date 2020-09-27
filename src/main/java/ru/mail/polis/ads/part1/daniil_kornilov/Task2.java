package ru.mail.polis.ads.part1.daniil_kornilov;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7409131

public class Task2 {
    private static class Node {
        char data;
        Node left;
        Node right;

        Node(char c) {
            this.data = c;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; ++i) {
            String input = in.next();
            StringBuilder result = new StringBuilder();
            ArrayList<Node> arrayList = new ArrayList<>();
            for (char c : input.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    arrayList.add(0, new Node(c));
                } else {
                    Node node = new Node(c);
                    node.right = arrayList.remove(0);
                    node.left = arrayList.remove(0);
                    arrayList.add(0, node);
                }
            }
            while (arrayList.size() != 0) {
                Node node = arrayList.remove(0);
                result.insert(0, node.data);
                if (node.right != null) {
                    arrayList.add(arrayList.size(), node.left);
                    arrayList.add(arrayList.size(), node.right);
                }
            }
            out.println(result.toString());
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
