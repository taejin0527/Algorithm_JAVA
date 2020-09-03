package d0903;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 3 
 * @author : "AoN"

 * @Description : BOJ 10971 - 외판원 순회 2
 * @Link : https://www.acmicpc.net/problem/10971
 * 
 */

public class BOJ_10971 {
	private static int N;
	private static long minDist = Long.MAX_VALUE;
	private static int[][] W;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		W = new int[N][N];
		for(int i=0; i<N; ++i) for(int j=0; j<N; ++j) W[i][j] = sc.nextInt();
		
		visited = new boolean[N];
		visited[0] = true;
		tsp(0, 0, 1);
		
		System.out.println(minDist);
		sc.close();
	}
	
	private static void tsp(int vertex, long dist, int cnt) {
		if(cnt == N) {
			if(W[vertex][0] == 0) return;
			
			dist += W[vertex][0];
			minDist = Math.min(minDist, dist);
			return;
		}
		
		for(int i=0; i<N; ++i) {
			if(visited[i]) continue;
			if(W[vertex][i] == 0) continue;
			
			visited[i] = true;
			tsp(i, dist+W[vertex][i], cnt+1);
			visited[i] = false;
		}
	}
}
