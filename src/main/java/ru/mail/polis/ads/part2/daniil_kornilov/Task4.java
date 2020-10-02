package ru.mail.polis.ads.part2.daniil_kornilov;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/7435405

public class Task4 {
    public static void k_stat(ArrayList<BigInteger> arr, int left, int right, int mid, int n) {
        int l = left;
        int r = right;
        BigInteger basic = arr.get(mid);

        while (l <= r) {
            while (arr.get(l).compareTo(basic) < 0) {
                ++l;
            }
            while (arr.get(r).compareTo(basic) > 0) {
                --r;
            }
            if (l <= r) {
                BigInteger b = arr.get(r);
                arr.set(r, arr.get(l));
                arr.set(l, b);
                --r;
                ++l;
            }
        }
        if (n <= r) {
            right = r;
            k_stat(arr, left, right, (right - left) / 2 + left, n);
        } else if (n >= l) {
            left = l;
            k_stat(arr, left, right, (right - left) / 2 + left, n);
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        ArrayList<BigInteger> array = new ArrayList<>();
        int k = Integer.parseInt(in.nextLine());
        String input = in.nextLine();
        String[] numbers = input.split(" ");
        for (String number : numbers) {
            array.add(new BigInteger(number));
        }
        k = array.size() - k;
        k_stat(array, 0, array.size() - 1, array.size() / 2, k);
        out.print(array.get(k));
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out); Scanner scanner = new Scanner(System.in)) {
            solve(scanner, out);
        }
    }
}
