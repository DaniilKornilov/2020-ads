package ru.mail.polis.ads.part1.daniil_kornilov;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7409147

public class Task4 {
    private static class Stack {
        private int[] stackArray;
        private int back;
        private int currentCapacity;

        Stack() {
            int initialSize = 10;
            currentCapacity = initialSize;
            stackArray = new int[initialSize];
            back = -1;
        }

        public void push(int n) {
            if (back + 1 == currentCapacity) {
                increaseCapacity();
            }
            ++back;
            stackArray[back] = n;
        }

        private void increaseCapacity() {
            int capacityCoefficient = 2;
            currentCapacity = currentCapacity * capacityCoefficient;
            stackArray = Arrays.copyOf(stackArray, currentCapacity);
        }

        public int pop() {
            int n = stackArray[back];
            --back;
            return n;
        }

        public int back() {
            return stackArray[back];
        }

        public int size() {
            return back + 1;
        }

        public void clear() {
            back = -1;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        while (true) {
            String command = in.next();
            switch (command) {
                case "push":
                    int n = in.nextInt();
                    stack.push(n);
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.size() > 0) {
                        out.println(stack.pop());
                    } else {
                        out.println("error");
                    }
                    break;
                case "back":
                    if (stack.size() > 0) {
                        out.println(stack.back());
                    } else {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
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
