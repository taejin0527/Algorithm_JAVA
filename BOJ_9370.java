package d0904;

import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 4 
 * @author : "AoN"

 * @Description : BOJ 9370 - 미확인 도착지
 * @Link : https://www.acmicpc.net/problem/9370
 * 
 * 그래프의 가중치를 짝수로 만들어 한 번의 다익스트라 알고리즘으로 답을 구할 수 있다
 */

public class BOJ_9370 {
	
	static class Node implements Comparable<Node> {
		int vertex, weight;
		public Node(int vertex, int weight) {
			this.vertex = vertex; this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int n, m, t, s, g, h, a, b, d;
	private static int[][] graph;
	private static int[] target;
	private static List<Integer> ans;
	private static final int INF = 10_000_000;
	
	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			getInput();
			dijkstra();
			Collections.sort(ans);
			for(int vertex : ans) System.out.print(vertex + " ");
		}
		
		br.close();
	}

	private static void getInput() throws IOException {
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());				// 교차로
		m = Integer.parseInt(st.nextToken());				// 도로
		t = Integer.parseInt(st.nextToken());				// 목적지 후보의 개수
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());				// 예술가들의 출발지
		g = Integer.parseInt(st.nextToken());				// 후각으로 찾은 교차로 g
		h = Integer.parseInt(st.nextToken());				// 후각으로 찾은 교차로 h
		
		graph = new int[n+1][n+1];
		for(int[] row : graph) Arrays.fill(row, INF);
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());			// 시작 노드
			b = Integer.parseInt(st.nextToken());			// 끝 노드
			d = Integer.parseInt(st.nextToken());			// 비용
			
			graph[a][b] = graph[b][a] = d * 2;
		}
		graph[g][h] = graph[h][g] = graph[g][h] - 1;
		
		target = new int[t];
		for(int i=0; i<t; ++i) {
			target[i] = Integer.parseInt(br.readLine());	// 목적지 후보들
		}
	}
	
	
	private static void dijkstra() {
		boolean[] visited = new boolean[n+1];
		int[] dist = new int[n+1]; Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[s] = 0;
		pq.add(new Node(s, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			visited[cur.vertex] = true;
			for(int i=1; i<=n; ++i) {
				if(visited[i]) continue;
				if(dist[i] > dist[cur.vertex] + graph[cur.vertex][i]) {
					dist[i] = dist[cur.vertex] + graph[cur.vertex][i];
					pq.add(new Node(i, dist[i]));
				}
			}

		}
		ans = new ArrayList<>();
		for(int i : target) {
			if(dist[i] % 2 == 1) {
				ans.add(i);
			}
		}

	}
}
