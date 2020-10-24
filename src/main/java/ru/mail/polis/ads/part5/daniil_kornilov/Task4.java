package ru.mail.polis.ads.part5.daniil_kornilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7572658

public class Task4 {
    private static boolean[][] d;

    private static boolean isTemplate(String template, String string) {
        d[0][0] = true;
        for (int i = 1; i <= template.length(); ++i) {
            char t = template.charAt(i - 1);
            for (int j = 1; j <= string.length(); ++j) {
                char s = string.charAt(j - 1);
                if (t == s || t == '?' || s == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (t == '*' || s == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i - 1][j] || d[i][j - 1];
                }
            }
        }
        return d[template.length()][string.length()];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String template = in.next();
        String string = in.next();
        d = new boolean[template.length() + 1][string.length() + 2];
        if (isTemplate(template, string)) {
            out.print("YES");
        } else {
            out.print("NO");
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
