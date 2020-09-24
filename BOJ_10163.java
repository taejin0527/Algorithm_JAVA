package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 24 
 * @author : "AoN"

 * @Description : BOJ 10163 - 색종이
 * @Link : https://www.acmicpc.net/problem/10163
 * 
 */

public class BOJ_10163 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int paper[][] = new int[101][101];
		int maxR=0, maxC=0;
		int N = sc.nextInt();
		for(int i=1; i<=N; ++i) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			for(int a=x; a<x+r; ++a) {
				for(int b=y; b<y+c; ++b) {
					paper[a][b] = i;
				}
			}
			
			maxR = Math.max(maxR, x+r);
			maxC = Math.max(maxC, y+c);
		}
		
		for(int i=0; i<11; ++i) {
			for(int j=0; j<11; ++j) {
				System.out.print(paper[i][j] + " ");
			}
			System.out.println();
		}
		
		int sum[] = new int[N+1];
		for(int i=0; i<maxR; ++i) {
			for(int j=0; j<maxC; ++j) {
				int num = paper[i][j];
				
				if(num == 0) continue;
				sum[num] += 1;
			}
		}
		
		for(int i=1; i<=N; ++i) {
			System.out.println(sum[i]);
		}
		
		sc.close();
	}
}

