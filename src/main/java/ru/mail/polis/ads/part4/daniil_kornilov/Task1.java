package ru.mail.polis.ads.part4.daniil_kornilov;

import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/7537457

public class Task1 {
    private static int[][] d;
    private static int[][] s;

    private static void split(String string, int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = i; j >= 0; --j) {
                if (i == j) {
                    d[i][j] = 1;
                } else {
                    int minD = Integer.MAX_VALUE;
                    int minSplit = Integer.MAX_VALUE;
                    if ((string.charAt(j) == '(' && string.charAt(i) == ')') ||
                            (string.charAt(j) == '[' && string.charAt(i) == ']')) {
                        minD = d[j + 1][i - 1];
                    }
                    for (int k = j; k < i; ++k) {
                        if ((d[j][k] + d[k + 1][i]) < minD) {
                            minSplit = k;
                            minD = d[j][k] + d[k + 1][i];
                        }
                    }
                    d[j][i] = minD;
                    s[j][i] = minSplit;
                }
            }
        }
    }

    private static String forSingleBracket(char bracket) {
        if ((bracket == '(') || (bracket == ')')) {
            return ("()");
        } else {
            return ("[]");
        }
    }

    private static String forRightSequence(String s, int i, int j) {
        return s.substring(i, j + 1);
    }

    private static void forWrongSequence(String s, int i, int j) {
        System.out.print(s.charAt(i));
        restore(s, i + 1, j - 1);
        System.out.print(s.charAt(j));
    }

    private static void restore(String string, int i, int j) {
        if (i == j) {
            System.out.print(forSingleBracket(string.charAt(i)));
            return;
        }
        if (d[i][j] == 0) {
            System.out.print(forRightSequence(string, i, j));
            return;
        }
        if (s[i][j] == Integer.MAX_VALUE) {
            forWrongSequence(string, i, j);
            return;
        }
        restore(string, i, s[i][j]);
        restore(string, s[i][j] + 1, j);
    }

    private static void solve() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                return;
            }
            d = new int[input.length()][input.length()];
            s = new int[input.length()][input.length()];
            split(input, input.length());
            restore(input, 0, input.length() - 1);
        }
    }

    public static void main(final String[] arg) {
        solve();
    }
}
