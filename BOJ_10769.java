package d0905;

import java.util.Scanner;

/**
 * FileName : Main.java
 * Project : JAVA8_e
 *
 * Description : BOJ 10769 - 행복한지 슬픈지
 * Link : https://www.acmicpc.net/problem/10769
 *
 * Created by AoN on 2020. 9. 5..
 *
 * 이번에는 Z 알고리즘을 사용하여 패턴 매칭을 학습하였다
 */

public class BOJ_10769 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String msg = sc.nextLine();

        int hCnt = 0, sCnt = 0;
        String happy = ":-)", sad = ":-(";
        hCnt = search(msg, happy);
        sCnt = search(msg, sad);

        String emotion;
        if(hCnt == 0 && sCnt == 0) {
            emotion = "none";
        }else if(hCnt - sCnt == 0) {
            emotion = "unsure";
        }else if(hCnt - sCnt > 0){
            emotion = "happy";
        }else {
            emotion = "sad";
        }

        System.out.println(emotion);

        sc.close();
    }

    private static int search(String msg, String pattern) {
        int cnt = 0;
        String concat = pattern + "#" + msg;
        int len = concat.length();
        int[] Z = new int[len];

        getZArray(concat, Z);

        for(int i=0; i<len; ++i) {
            if(Z[i] == pattern.length()) ++cnt;
        }

        return cnt;
    }

    private static void getZArray(String s, int[] Z) {
        int L, R, len;
        L = R = 0;
        len = s.length();

        for(int i=1; i<len; ++i) {
            if(i > R) {
                L = R = i;
                while(R < len && s.charAt(R-L) == s.charAt(R)) {
                    ++R;
                }
                Z[i] = R - L;
                --R;
            }else {
                int k = i - L;
                if(Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                }else {
                    L = i;
                    while(R < len && s.charAt(R-L) == s.charAt(R)) {
                        ++R;
                    }
                    Z[i] = R - L;
                    --R;
                }
            }
        }

    }
}
