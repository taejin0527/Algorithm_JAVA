package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 23 
 * @author : "AoN"

 * @Description : BOJ 1244 - 스위치 켜고 끄기
 * @Link : https://www.acmicpc.net/problem/1244
 * 
 */

public class BOJ_1244 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int state[] = new int[N+1];
		for(int i=1; i<=N; ++i) state[i] = sc.nextInt();
		int S = sc.nextInt();
		for(int i=0; i<S; ++i) {
			int g = sc.nextInt();
			int n = sc.nextInt();
			
			if(g==1) {
				for (int j = n; j <= N; j += n) state[j] ^= 1;
			}else {
				state[n] ^= 1;
				
				int s = n-1, e = n+1;
				while(true) {
					if(s < 1 || e > N) break;
					if(state[s] != state[e]) break;
					
					state[s] ^= 1;
					state[e] ^= 1;	
					--s; ++e;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
	        System.out.print(state[i] + " ");
	        if (i % 20 == 0) {
	            System.out.println();
	        }
	    }
		
		sc.close();
	}
}