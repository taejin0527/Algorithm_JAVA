package d0905;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileName : Main.java
 * Project : JAVA8_e
 *
 * Description : BOJ 5525 - IOIOI
 * Link : https://www.acmicpc.net/problem/5525
 *
 * Created by AoN on 2020. 9. 5..
 *
 * KMP 알고리즘 공부
 */

public class BOJ_5525 {
    private static int N, M;
    private static String S, pattern;
    private static ArrayList<Integer> ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        S = sc.next();

        makePattern();
        KMP();

        System.out.println(ans.size());

        sc.close();
    }

    private static void makePattern() {
        StringBuilder sb = new StringBuilder();

        sb.append("I");
        while(N-- > 0) {
            sb.append("OI");
        }

        pattern = sb.toString();
    }

    private static void KMP() {
        ans = new ArrayList<>();
        int j = 0, len = pattern.length();
        int[] pi = getPi();
        char[] s = S.toCharArray();
        char[] p = pattern.toCharArray();

        for(int i=0; i<M; ++i) {
            while(j > 0 && s[i] != p[j]) {
                j = pi[j-1];
            }
            if(s[i] == p[j]) {
                if(j == len - 1) {
                    ans.add(i - len + 1);
                    j = pi[j];
                }else {
                    ++j;
                }
            }
        }
    }

    private static int[] getPi() {
        int j = 0, len = pattern.length();
        int[] pi = new int[len];
        char[] pc = pattern.toCharArray();

        for(int i=1; i<len; ++i) {
            while(j > 0 && pc[i] != pc[j]) {
                j = pi[j-1];
            }
            if(pc[i] == pc[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}
