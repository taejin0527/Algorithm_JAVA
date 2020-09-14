package d0913;

import java.util.*;
import java.io.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 13
 * @author : "AoN"
 * 
 * @Description : BOJ 1854 - K번째 최단경로 찾기
 * @Link : https://www.acmicpc.net/problem/1854
 * 
 */

public class BOJ_1854 {
	static int N, M, K;
	static int graph[][];
	static PriorityQueue<Integer> distQ[];
	
	static final int INF = Integer.MAX_VALUE;
	
	static int stoi(String s) {return Integer.parseInt(s);};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] row = br.readLine().split(" ");
		N = stoi(row[0]);
		M = stoi(row[1]);
		K = stoi(row[2]);
		
		graph = new int[N+1][N+1];
		for(int i=0; i<M; ++i) {
			row = br.readLine().split(" ");
			int u = stoi(row[0]);
			int v = stoi(row[1]);
			int w = stoi(row[2]);
			
			graph[u][v] = w;
		}
		
		dijkstra();
		
		for(int i=1; i<N+1; ++i) {
			System.out.println(distQ[i].size() == K ? -1 * distQ[i].peek() : -1);
		}
		
		br.close();
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distQ = new PriorityQueue[N+1]; for(int i=0; i<N+1; ++i) distQ[i] = new PriorityQueue<>();
		
		pq.offer(new Node(1, 0));
		distQ[1].add(0);
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(int i=1; i<N+1; ++i) {
				int adj = graph[cur.v][i];
				
				if(adj != 0) {
					if(distQ[i].size() < K) {
						distQ[i].add(-1 * (cur.w + adj));
						pq.offer(new Node(i, cur.w + adj));
					}else if(-1 * distQ[i].peek() > cur.w + adj) {
						distQ[i].poll();
						distQ[i].offer(-1 * (cur.w + adj));
						pq.offer(new Node(i, cur.w + adj));
					}
				}
			}
//			for(Node n : pq) System.out.print(n.v + " ");
//			System.out.print(" : ");
//			for(int i=0; i<N+1; ++i) System.out.print(distQ[i] + " ");
//			System.out.println();
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
