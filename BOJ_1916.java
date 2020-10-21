import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 19 
 * @author : "AoN"

 * @Description : BOJ 1916 - 최소비용 구하기
 * @Link : https://www.acmicpc.net/problem/1916
 * 
 */

public class BOJ_1916 {
	
	static int N, M, start, end;
	static ArrayList<Node>[] city;
	static int cost[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		city = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) city[i] = new ArrayList<>();
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			city[u].add(new Node(v, w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dijkstra();
		
		System.out.println(cost[end]);
		br.close();
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		cost = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		pq.add(new Node(start, 0));
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cost[cur.v] < cur.w) continue;
			
			for(int i=0; i < city[cur.v].size(); ++i) {
				Node nxt = city[cur.v].get(i);
				
				if(cost[nxt.v] > cost[cur.v] + nxt.w) {
					cost[nxt.v] = cost[cur.v] + nxt.w;
					pq.add(new Node(nxt.v, cost[nxt.v]));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int v, w;
		
		public Node(int v, int w) {
			this.v = v; this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
}
