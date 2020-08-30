package algo;

import java.util.Scanner;

public class BOJ_14889 {
    static int N, minDiff;
    static int[][] S;
    static boolean[] selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        S = new int[N][N];
        for(int i=0; i<N; ++i) {
            for(int j=0; j<N; ++j) {
                S[i][j] = sc.nextInt();
            }
        }

        selected = new boolean[N];
        minDiff = Integer.MAX_VALUE;
        comb(0, 0);

        System.out.println(minDiff);
        sc.close();
    }

    static void comb(int cnt, int cur) {
        if(cnt == N/2) {
            minDiff = Math.min(minDiff, statsDiff());
            return;
        }

        for(int i=cur; i<N; ++i) {
            if(selected[i]) continue;
            selected[i] = true;
            comb(cnt+1, i+1);
            selected[i] = false;
        }
    }

    static int statsDiff() {
        int teamStart = 0, teamLink = 0;
        for(int i=0; i<N; ++i) {
            for(int j=0; j<N; ++j) {
                if(selected[i] && selected[j]) {
                    teamStart += S[i][j];
                }
                if(!selected[i] && !selected[j]) {
                    teamLink += S[i][j];
                }
            }
        }

        return Math.abs(teamStart - teamLink);
    }
}
