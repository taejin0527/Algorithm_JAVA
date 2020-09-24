package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 20 
 * @author : "AoN"

 * @Description : BOJ 10158 - 개미
 * @Link : https://www.acmicpc.net/problem/10158
 * 
 */

public class BOJ_10158 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int w = sc.nextInt();
		int h = sc.nextInt();
		int p = sc.nextInt();
		int q = sc.nextInt();
		int t = sc.nextInt();

		int dp = (p + t) % w;
		int dq = (q + t) % h;
		
		if (((p + t)/w) % 2 == 1)
			dp = w - dp;
		if (((q + t)/h) % 2 == 1)
			dq = h - dq;
		
		System.out.println(dp + " " + dq);
		sc.close();
	}
}
