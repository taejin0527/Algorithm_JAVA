package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 19 
 * @author : "AoN"

 * @Description : BOJ 2527 - 직사각형
 * @Link : https://www.acmicpc.net/problem/2527
 * 
 */

public class BOJ_2527 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = 4;
		while(T-- > 0) {
			int[] r1 = {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};
			int[] r2 = {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};
			
			// 공통부분이 없음 d
			if(r1[0] > r2[2] || r1[2] < r2[0] || r1[1] > r2[3] || r1[3] < r2[1]) {
				System.out.println("d"); continue;
			}
			
			// 점 c
			if( r1[0] == r2[2] && (r1[1] == r2[3] || r1[3] == r2[1])) {	// 왼쪽 위, 아래
				System.out.println("c"); continue;
			}
			if( r1[2] == r2[0] && (r1[1] == r2[3] || r1[3] == r2[1])) { // 오른쪽 위, 아래
				System.out.println("c"); continue;
			}
			
			// 선분 b
			if( r1[0] == r2[2] || r1[2] == r2[0] || r1[1] == r2[3] || r1[3] == r2[1]) {
				System.out.println("b"); continue;
			}
			
			// 직사각형 a
			System.out.println("a");
		}
		
		sc.close();
	}
}
