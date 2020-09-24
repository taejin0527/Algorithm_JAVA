package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 18 
 * @author : "AoN"

 * @Description : BOJ 2605 - 줄 세우기
 * @Link : https://www.acmicpc.net/problem/2605
 * 
 */

public class BOJ_2605 {
	
	static int N;
	static int std[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		std = new int[N];
		for(int i=0; i<N; ++i) {
			int num = sc.nextInt();
			
			for(int j=N-1; j>i-num; --j) {
				std[j] = std[j-1];
			}
			std[i-num] = i+1;
		}
		
		for(int i=0; i<N; ++i) System.out.print(std[i] + " ");
		
		sc.close();
	}
}
