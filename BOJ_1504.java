package d0904;

import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 3 
 * @author : "AoN"

 * @Description : BOJ 1504 - 특정한 최단 경로
 * @Link : https://www.acmicpc.net/problem/1504
 * 
 */

public class BOJ_1504 {
	private static int N, E, v1, v2;
	private static List<Node>[] graph;
	private static int []dist;
	private static boolean[] visited;
	private static final int INF =  200_000_000;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; ++i) graph[i] = new ArrayList<>();
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[f].add(new Node(t, w));
			graph[t].add(new Node(f,w));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		dist = new int[N+1];
		int ans = solve();
		
		System.out.println(ans);
		
		br.close();
	}
	
    private static int solve(){
        int r1 = 0, r2 = 0;

        // 1 -> 필수1 -> 필수2 -> n 최단거리
        r1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        // 1 -> 필수2 -> 필수1 -> n 최단거리
        r2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        // 경로1 && 경로2 -> 가는길이 없는 경우
        if(r1 >= INF && r2 >= INF) return -1;
        else return Math.min(r1, r2);
    }
	
	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
	
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			visited[cur.vertex] = true;
			
			for(Node adj : graph[cur.vertex]) {
				if (!visited[adj.vertex] && dist[adj.vertex] > dist[cur.vertex] + adj.weight) {
					dist[adj.vertex] = dist[cur.vertex] + adj.weight;
					pq.add(new Node(adj.vertex, dist[adj.vertex]));
				}
			}
		}
		return dist[end];
	}
	
	static class Node implements Comparable<Node>{
		int vertex, weight;
		public Node(int vertex, int weight) {
			this.vertex=vertex; this.weight=weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
