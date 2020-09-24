package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 23 
 * @author : "AoN"

 * @Description : BOJ 2628 - 종이자르기
 * @Link : https://www.acmicpc.net/problem/2628
 * 
 */

public class BOJ_2628 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		int R = sc.nextInt();
		int N = sc.nextInt();
		
		boolean col[] = new boolean[C];
		boolean row[] = new boolean[R];
		for(int i=0; i<N; ++i) {
			int d = sc.nextInt();
			int idx = sc.nextInt();
			
			if(d==0) {	// 가로
				row[idx] = true;
			}else {	// 세로
				col[idx] = true;
			}
		}
		
		int maxC = 0, cnt = 0;
		for(int i=0; i<C; ++i) {
			if(col[i]) {
				maxC = Math.max(maxC, cnt);
				cnt = 0;
			}
			cnt++;
		}
		maxC = Math.max(maxC, cnt);
		
		int maxR = 0;
		cnt = 0;
		for(int i=0; i<R; ++i) {
			if(row[i]) {
				maxR = Math.max(maxR, cnt);
				cnt = 0;
			}
			cnt++;
		}
		maxR = Math.max(maxR, cnt);

		System.out.println(maxC * maxR);
		
		sc.close();
	}
}
