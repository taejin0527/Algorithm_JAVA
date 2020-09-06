package d0906;

import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 6 
 * @author : "AoN"

 * @Description : BOJ 11657 - 타임머신
 * @Link : https://www.acmicpc.net/problem/11657
 * 
 */

public class BOJ_11657 {
	
	private static class Edge {
		int u, v, w;
		
		public Edge(int u, int v, int w) {
			this.u = u; this.v = v; this.w = w;
		}
	}
	
	private static int N, M;
	private static Edge[] graph;
	private static long[] dist;
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new Edge[M];
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[i] = new Edge(from, to, weight);
		}
		
		boolean check = bellmanFord();
		
		if(check) 
			for(int i=2; i<=N; ++i) 
				 System.out.println(dist[i] == INF ? -1 : dist[i]);
		else 
			System.out.println(-1);
		
		br.close();
	}

	private static boolean bellmanFord() {
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		
		dist[1] = 0;
		
		for(int i=0; i<N-1; ++i) {
			for(int j=0; j<M; ++j) {
				if(dist[graph[j].u] == INF) continue;
				if(dist[graph[j].v] > dist[graph[j].u] + graph[j].w) {
					dist[graph[j].v] = dist[graph[j].u] + graph[j].w;
				}
			}
		}
		
		for(int j=0; j<M; ++j) {
			if(dist[graph[j].u] == INF) continue;
			if(dist[graph[j].v] > dist[graph[j].u] + graph[j].w) {
				return false;
			}
		}
		
		return true;
	}
}
