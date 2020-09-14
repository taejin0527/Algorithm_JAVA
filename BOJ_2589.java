package d0913;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 13 
 * @author : "AoN"

 * @Description : BOJ 2589 - 보물섬
 * @Link : https://www.acmicpc.net/problem/2589
 * 
 */

public class BOJ_2589 {
	static int L, W, maxStep;
	static char map[][];
	static boolean v[][];
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		W = sc.nextInt();
		
		map = new char[L][W];
		for(int i=0; i<L; ++i) {
			String row = sc.next();
			for(int j=0; j<W; ++j) {
				map[i][j] = row.charAt(j);
			}
		}
		
		travel();
		
		System.out.println(maxStep);
		sc.close();
	}
	
	static void travel() {
	
		for(int i=0; i<L; ++i) {
			for(int j=0; j<W; ++j) {
				if(map[i][j] == 'L') {
					v = new boolean[L][W];
					BFS(i, j);
				}
			}
		}
	}
	
	static void BFS(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y, 0));
		v[x][y] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			maxStep = Math.max(maxStep, cur.step);
			
			for(int i=0; i<4; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(0 > nx || nx > L-1 || 0 > ny || ny > W-1) continue;
				if(v[nx][ny] || map[nx][ny] == 'W') continue;
				
				v[nx][ny] = true;
				q.offer(new Point(nx, ny, cur.step+1));
			}
		}
	}
	
	static class Point {
		int x, y, step;

		public Point(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
}
