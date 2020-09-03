package d0903;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 3 
 * @author : "AoN"

 * @Description : BOJ 1303 - 전쟁 전투
 * @Link : https://www.acmicpc.net/problem/1303
 * 
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1303 {
	static int N, M;
	static char[][] field;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		field = new char[N][M];
		for(int i=0; i<N; ++i) {
			String row = sc.next();
			for(int j=0; j<M; ++j) {
				field[i][j] = row.charAt(j);
			}
		}
		
		int white = 0, blue = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(field[i][j] == 'W') {
					white += bfs(new int[] {i, j}, 'W');
				}
				if(field[i][j] == 'B') {
					blue += bfs(new int[] {i, j}, 'B');
				}
			}
		}
		
		System.out.println(white + " " + blue);
		sc.close();
	}
	
	static int bfs(int[] pos, char side) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(pos);
		field[pos[0]][pos[1]] = 'X';
		
		int total = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			++total;
			
			for(int i=0; i<4; ++i) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(0 > nx || nx >= N || 0 > ny || ny >= M) continue;
				if(field[nx][ny] != side) continue;
				field[nx][ny] = 'X';
				q.offer(new int[] {nx, ny});
			}
		}
		return total*total;
	}
}



/*
public class Main {

	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private void solve() {
		Scanner sc = new Scanner(System.in);
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		char[][] combat = new char[n][m];
		for (int i = 0; i < n; i++) {
			combat[i] = sc.next().toCharArray();
		}

		int b = 0, w = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (combat[i][j] == 'B') b += Math.pow(find(combat, n, m, i, j, 'B'), 2);
				if (combat[i][j] == 'W') w += Math.pow(find(combat, n, m, i, j, 'W'), 2);
			}
		}

		System.out.println(w + " " + b);
	}

	private int find(char[][] combat, int n, int m, int i, int j, char c) {
		if (i < 0 || j < 0 || i >= n || j >= m) return 0;
		if (combat[i][j] != c) return 0;
		combat[i][j] = '0';

		int count = 0;
		for (int a = 0; a < 4; a++) {
			count += find(combat, n, m, i + dx[a], j + dy[a], c);
		}
		return 1 + count;
	}

	public static void main(String[] args) {
		new Main().solve();
	}
}
*/