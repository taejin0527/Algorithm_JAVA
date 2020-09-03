package d0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 9. 3 
 * @author : "AoN"

 * @Description : 1251. [S/W 문제해결 응용] 4일차 - 하나로
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE
 */

public class SWEA_1251 {
	static int N;
	static long ans;
	static double E;
	static int[][] nodes;
	static long[][] graph;
	
	static BufferedReader br;
	static StringBuilder output = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; ++tc) {
			getInput();
			initGraph();
			findMST();
			output.append("#").append(tc).append(" ").append(Math.round(ans*E)).append("\n");
		}
		System.out.println(output);
		br.close();
	}
	
	static void getInput() throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());									// 1. 섬의 개수
		nodes = new int[N][2];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i) nodes[i][0] = Integer.parseInt(st.nextToken());	// 2. 각 섬들의 x 좌표
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i) nodes[i][1] = Integer.parseInt(st.nextToken());	// 3. 각 섬들의 y 좌표
		E = Double.parseDouble(br.readLine());									// 4. 해저터널 건설의 환경 부담 세율
	}
	
	static void initGraph() {
		graph = new long[N][N];
		
		for(int i=0; i<N-1; ++i) {
			for(int j=i+1; j<N; ++j) {
				long dist = getDistance(nodes[i][0], nodes[j][0], nodes[i][1], nodes[j][1]);
				graph[i][j] = graph[j][i] = dist;
			}
		}
	}
	
	static void findMST() {
		boolean[] visited = new boolean[N];
		long[] dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[0] = 0;
		ans = 0;

		int cnt=0;
		while(true) {
			// 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 선택
			long min = Long.MAX_VALUE;
			int minNo = 0;
			
			for(int i=0; i<N;++i) {
				if(!visited[i] && min > dist[i]) {
					min = dist[i];
					minNo = i;
				}
			}
			
			visited[minNo] = true;
			ans += min;
			if(++cnt == N) break;
			
			// 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선 비용을 고려하여 dist 업데이트
			for(int i=0; i<N; ++i) {
				if(!visited[i] && graph[minNo][i] > 0 && dist[i] > graph[minNo][i])
					dist[i] = graph[minNo][i];
			}
		}
	}
	
	static long getDistance(int x1, int x2, int y1, int y2) {
		return (long) (Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}
