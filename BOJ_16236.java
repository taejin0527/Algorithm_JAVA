package d0904;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 4 
 * @author : "AoN"

 * @Description : BOJ 16236 - 아기상어
 * @Link : https://www.acmicpc.net/problem/16236
 * 
 */

public class BOJ_16236 {
	
	static class Fish implements Comparable<Fish>{
		int x, y, size, exp;
		public Fish(int x, int y, int size){
			this.x = x; this.y = y; this.size = size;
		}
		@Override
		public int compareTo(Fish o) {
			if(this.x > o.x) return 1;
			else if(this.x < o.x) return -1;
			else {
				if(this.y < o.y) return -1;
				else return 1;
			}
		}
	}
	
	static class Point {
		int x, y, distance;
		public Point(int x, int y, int distance) {
			this.x = x; this.y = y; this.distance = distance;
		}
	}
	
	private static int N, ans = 0;
	private static int[][] map;
	private static Fish babyShark;
	private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==9) { 
					babyShark = new Fish(i, j, 2);
					map[i][j] = 0;
				}
			}
		}
		
		hunting();
		
		System.out.println(ans);
		sc.close();
	}
	
	private static void hunting() {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		
		int minDist = Integer.MAX_VALUE;
		q.add(new Point(babyShark.x, babyShark.y, 0));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i=0; i<4; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(0 > nx || nx >= N || 0 > ny || ny >= N) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] > babyShark.size) continue;
				if(minDist < cur.distance+1) continue;
				
				visited[nx][ny] = true;
				
				if(map[nx][ny] > 0 && map[nx][ny] < babyShark.size) {
					minDist = cur.distance+1;
					pq.add(new Fish(nx, ny, map[nx][ny]));
					continue;
				}
				q.add(new Point(nx, ny, cur.distance+1));
			}
		}
		
		if(!pq.isEmpty()) {
			Fish target = pq.poll();
			
			babyShark.x = target.x; babyShark.y = target.y;
			map[target.x][target.y] = 0; 
			++babyShark.exp;
			
			if(babyShark.exp == babyShark.size) {
				++babyShark.size;
				babyShark.exp = 0;
			}
			ans += minDist;
			hunting();
		}	
	}
}
