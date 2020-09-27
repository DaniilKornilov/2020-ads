package ru.mail.polis.ads.part1.daniil_kornilov;

import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7409143

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final char OPEN_BRACKET = '(';
        final char CLOSE_BRACKET = ')';
        String string = in.next();
        int openBracketCounter = 0;

        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == OPEN_BRACKET) {
                ++openBracketCounter;
            } else if (string.charAt(i) == CLOSE_BRACKET) {
                --openBracketCounter;
            }
            if (openBracketCounter < 0) {
                out.println("NO");
                return;
            }
        }

        if (openBracketCounter > 0) {
            out.println("NO");

        } else {
            out.println("YES");
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
