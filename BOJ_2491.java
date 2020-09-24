package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 19 
 * @author : "AoN"

 * @Description : BOJ 2491 - 수열
 * @Link : https://www.acmicpc.net/problem/2491
 * 
 */

public class BOJ_2491 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int num[] = new int[N];
		for(int i=0; i<N; ++i) num[i] = sc.nextInt();
		
		int lenASC = 1, lenDESC = 1;
		int maxLen = 1;
		for(int i=1; i<N; ++i) {
			if(num[i-1] < num[i]) {
				++lenASC; lenDESC = 1;
			}else if(num[i-1] > num[i]) {
				++lenDESC; lenASC = 1;
			}else {
				++lenASC; ++lenDESC;
			}
			
			maxLen = Math.max(maxLen, lenASC);
			maxLen = Math.max(maxLen, lenDESC);
		}
		
		System.out.println(maxLen);
		sc.close();
	}
}

