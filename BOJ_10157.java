package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 19 
 * @author : "AoN"

 * @Description : BOJ 10157 - 자리배정
 * @Link : https://www.acmicpc.net/problem/10157
 * 
 */

public class BOJ_10157 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int R = sc.nextInt(); int C = sc.nextInt();
		int K = sc.nextInt();
		
		int snail[][] = new int[R][C];
		
		int sizeR = C, sizeC = R-1, cnt = 0;
		int i = 0, j = -1, d = 1;
		while(true) {
			for(int c=0; c<sizeR; ++c) {
				j += d;
				snail[i][j] = ++cnt;
			}
			
			--sizeR;
			if(sizeC == 0) break;
			
			for(int c=0; c<sizeC; ++c) {
				i += d;
				snail[i][j] = ++cnt;
			}
			--sizeC;
			if(sizeR == 0) break;
			
			d *= -1;	
		}
		
		//for(int[] row : snail) System.out.println(Arrays.toString(row));
		
		StringBuilder ans = new StringBuilder();
		for(int x=0; x<R; ++x) {
			for(int y=0; y<C; ++y) {
				if(snail[x][y] == K) {
					ans.append(x+1).append(" ").append(y+1);
				}
			}
		}
		
		if(ans.length() == 0) System.out.println(0);
		else System.out.println(ans);
		
		sc.close();
	}
}
