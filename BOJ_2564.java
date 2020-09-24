package IM;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 18 
 * @author : "AoN"

 * @Description : BOJ 2564 - 경비원
 * @Link : https://www.acmicpc.net/problem/2564
 * 
 */

public class BOJ_2564 {
	
	static int R, C, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt(); C = sc.nextInt();
		N = sc.nextInt();
		
		ArrayList<Point> store = new ArrayList<>();
		for(int i=0; i<N; ++i) {
			Point s = new Point(sc.nextInt(), sc.nextInt());
			store.add(s);
		}
		Point security = new Point(sc.nextInt(), sc.nextInt());
		
		int minDist = 0;
		for(Point cur : store) {
			if(cur.dir != security.dir && cur.dir%2 == security.dir%2) {
				if(cur.dir%2 == 1) {
					minDist += (cur.x + security.x > R) ? (C + 2*R - cur.x - security.x) : (C + cur.x + security.x); 
				}else {
					minDist += (cur.y + security.y > C) ? (R + 2*C - cur.y - security.y) : (R + cur.y + security.y); 
				}
			}else {
				minDist += Math.abs(cur.x - security.x) + Math.abs(cur.y - security.y);
			}
		}
		System.out.println(minDist);
		
		sc.close();
	}
	
	static class Point{
		int dir, dist;
		int x, y;
		
		public Point(int dir, int dist) {		
			if(dir==2) this.dir = 3;
			else if(dir==3) this.dir = 2;
			else this.dir = dir;
			
			switch(this.dir) {
			case 1:
				this.x = dist; this.y = C;
				break;
			case 2:
				this.x = 0; this.y = C-dist;
				break;
			case 3:
				this.x = dist; this.y = 0;
				break;
			case 4:
				this.x = R; this.y = C-dist;
				break;
			}
		}
	}
}
