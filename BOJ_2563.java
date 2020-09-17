package d0917;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 17 
 * @author : "AoN"

 * @Description : BOJ 2563 - 색종이
 * @Link : https://www.acmicpc.net/problem/2563
 * 
 */


public class BOJ_2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[100][100];
        int cnt = 0;
    	
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            for(int j = x; j < x + 10; j++) {
                for(int k = y; k < y + 10; k++) {
                    if(map[j][k] == 1)
                        continue;
                    map[j][k] = 1;
                    ++cnt;
                }
            }
        }
        System.out.println(cnt);
    }
}
