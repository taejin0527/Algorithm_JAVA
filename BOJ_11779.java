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
 * @Date : 2020. 10. 21 
 * @author : "AoN"

 * @Description : BOJ 11779 - 최소비용 구하기 2
 * @Link : https://www.acmicpc.net/problem/11779
 * 
 */

public class BOJ_11779 {
	
	static int n, m, start, end;
	static ArrayList<Node> graph[];
	static int cost[], path[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; ++i) graph[i] = new ArrayList<>();
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Node(e, c));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		
		System.out.println(cost[end]);
		ArrayList<Integer> v = new ArrayList<>();

		while(true) {
			if(end == 0) break;
			v.add(end);
			end = path[end];
		}
		System.out.println(v.size());
		for(int i=v.size()-1; i>=0; --i) System.out.print(v.get(i) + " ");
		br.close();
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		cost = new int[n+1]; path = new int[n+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		pq.offer(new Node(start, 0));
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cost[cur.end] < cur.cost) continue;
			
			for(int i=0; i<graph[cur.end].size(); ++i) {
				Node nxt = graph[cur.end].get(i);
				
				if(cost[nxt.end] > cost[cur.end] + nxt.cost) {
					cost[nxt.end] = cost[cur.end] + nxt.cost;
					path[nxt.end] = cur.end;
					pq.offer(new Node(nxt.end, cost[nxt.end]));
				}
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
