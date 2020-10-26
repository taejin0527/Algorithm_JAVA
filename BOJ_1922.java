import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 26 
 * @author : "AoN"

 * @Description : BOJ 1922 - 네트워크 연결
 * @Link : https://www.acmicpc.net/problem/1922
 * 
 */

public class BOJ_1922 {
	
	static int N, M, dist;
	static List<Node> graph[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) graph[i] = new ArrayList<>();
		for(int i=0; i<M; ++i) {
			String[] abc = br.readLine().split(" ");
			
			int a = Integer.parseInt(abc[0]);
			int b = Integer.parseInt(abc[1]);
			int c = Integer.parseInt(abc[2]);
			
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		
		MST();
		
		System.out.println(dist);
		br.close();
	}
	
	static void MST() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Queue<Integer> q = new LinkedList<>();
		int cost[] = new int[N+1];
		boolean v[] = new boolean[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		q.offer(1);
		cost[1] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			v[cur] = true;
			
			for(Node e : graph[cur]) {
				if(v[e.end]) continue;
				pq.offer(e);
			}
			
			while(!pq.isEmpty()) {
				Node nxt = pq.poll();
				
				if(v[nxt.end]) continue;
				
				v[nxt.end] = true;
				q.add(nxt.end);
				dist += nxt.cost;
				break;
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int end, cost;
		
		public Node(int end, int cost) {
			this.end = end; this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
