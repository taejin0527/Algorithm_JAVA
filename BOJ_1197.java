import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 23 
 * @author : "AoN"

 * @Description : BOJ 1197 - 최소 스패닝 트리
 * @Link : https://www.acmicpc.net/problem/1197
 * 
 */

public class BOJ_1197 {
	
	static int V, E;
	static List<Node> graph[];
	static int edge[];
	static boolean v[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String ve[] = br.readLine().split(" ");
		V = Integer.parseInt(ve[0]);
		E = Integer.parseInt(ve[1]);
		
		graph = new ArrayList[V+1];
		edge = new int[V+1];
		v = new boolean[V+1];
		for(int i=1; i<=V; ++i) {
			graph[i] = new ArrayList<>();
			edge[i] = Integer.MAX_VALUE;
		}
		for(int i=0; i<E; ++i) {
			String abc[] = br.readLine().split(" ");
			
			int a = Integer.parseInt(abc[0]);
			int b = Integer.parseInt(abc[1]);
			int c = Integer.parseInt(abc[2]);
			
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		
		prim();
		
		br.close();
	}
	
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Deque<Integer> dq = new ArrayDeque<>();
		
		dq.add(1);
		
		long ans = 0;
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			v[cur] = true;
			
			for(int i=0; i<graph[cur].size(); ++i) {
				int ne = graph[cur].get(i).e;
				long nc = graph[cur].get(i).c;
				
				if(v[ne]) continue;
				pq.offer(new Node(ne, nc));
			}
			
			while(!pq.isEmpty()) {
				int ne = pq.peek().e;
				long nc = pq.poll().c;
				
				if(v[ne]) continue;
				
				v[ne] = true;
				dq.add(ne);
				ans += nc;
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static class Node implements Comparable<Node> {
		int e;
		long c;
		
		public Node(int e, long c) {
			this.e = e; this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.c, o.c);
		}
	}
}
