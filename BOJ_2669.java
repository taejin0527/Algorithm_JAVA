package IM;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 22 
 * @author : "AoN"

 * @Description : BOJ 2669 - 직사각형 네개의 합집합의 면적 구하기
 * @Link : https://www.acmicpc.net/problem/2669
 * 
 */

public class BOJ_2669 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int mX = 0, mY = 0;
		Rect rect[] = new Rect[4];
		for(int i=0; i<4; ++i) {
			int x1 = sc.nextInt(), y1 = sc.nextInt();
			int x2 = sc.nextInt(), y2 = sc.nextInt();
			rect[i] = new Rect(x1, y1, x2, y2);
			
			mX = Math.max(mX, x2);
			mY = Math.max(mY, y2);
		}
		
		int board[][] = new int[mX][mY];
		
		int cnt=0;
		for(int i=0; i<4; ++i) {
			for(int x = rect[i].x1; x < rect[i].x2; ++x) {
				for(int y = rect[i].y1; y < rect[i].y2; ++y) {
					if(board[x][y] == 0) {
						board[x][y] = 1;
						++cnt;
					}
				}
			}
		}
		
		System.out.println(cnt);
		sc.close();
	}
	
	static class Rect {
		int x1, y1, x2, y2;

		public Rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
}
