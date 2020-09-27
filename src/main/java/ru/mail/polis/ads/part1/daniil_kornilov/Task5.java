package ru.mail.polis.ads.part1.daniil_kornilov;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7409158

public class Task5 {
    private static class Queue {
        private final int[] queueArray;
        private int front;
        private int back;
        private int currentSize;
        private final int SIZE = 100;

        Queue() {
            queueArray = new int[SIZE];
            front = 0;
            back = -1;
            currentSize = 0;
        }

        public void push(int n) {
            if (back == SIZE - 1) {
                back = -1;
            }
            ++back;
            queueArray[back] = n;
            ++currentSize;
        }

        public int pop() {
            int n = queueArray[front];
            ++front;
            if (front == SIZE) {
                front = 0;
            }
            --currentSize;
            return n;
        }

        public int front() {
            return queueArray[front];
        }

        public int size() {
            return currentSize;
        }

        public void clear() {
            front = 0;
            back = -1;
            currentSize = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        while (true) {
            String command = in.next();
            switch (command) {
                case "push":
                    int n = in.nextInt();
                    queue.push(n);
                    out.println("ok");
                    break;
                case "pop":
                    out.println(queue.pop());
                    break;
                case "front":
                    out.println(queue.front());
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
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
