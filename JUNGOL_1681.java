package d0903;

import java.util.Scanner;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 9. 3 
 * @author : "AoN"

 * @Description : JUNGOL 1681 : 해밀턴 순환회로
 * @Link : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954
 * 
 */

public class JUNGOL_1681 {
	private static int N, minDist = 1200;
	private static int[][] graph;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		graph = new int[N][N];
		for(int i=0; i<N; ++i) for(int j=0; j<N; ++j) graph[i][j] = sc.nextInt();

		
		visited = new boolean[N];
		visited[0] = true;
		findHamiltonCycle(0, 0, 1);
				
		System.out.println(minDist);
		sc.close();
	}
	
	private static void findHamiltonCycle(int curNode, int dist, int cnt) {
		if(cnt == N) {
			if(graph[curNode][0] == 0) return;
			
			dist += graph[curNode][0];
			minDist = Math.min(minDist, dist);
			return;
		}
		
		for(int i=0; i<N; ++i) {
			if(visited[i] || graph[curNode][i]==0) continue;
			visited[i] = true;
			findHamiltonCycle(i, dist + graph[curNode][i], cnt+1);
			visited[i] = false;
		}
	}
}
