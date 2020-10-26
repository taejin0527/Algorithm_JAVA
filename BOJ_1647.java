import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 26 
 * @author : "AoN"

 * @Description : BOJ 1647 - 도시 분할 계획
 * @Link : https://www.acmicpc.net/problem/1647
 * 
 */

public class BOJ_1647 {
	
	static int N, M, ans;
	static int p[];
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String nm[] = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		pq = new PriorityQueue<>();
		p = new int[N+1];
		for(int i=1; i<=N; ++i) p[i] = i;
		
		for(int i=0; i<M; ++i) {
			String abc[] = br.readLine().split(" ");
			
			int a = Integer.parseInt(abc[0]);
			int b = Integer.parseInt(abc[1]);
			int c = Integer.parseInt(abc[2]);
			
			pq.offer(new Node(a, b, c));
		}
		
		int size = 1;
		while(size < N-1) {
			Node cur = pq.poll();
			if(find(cur.s) != find(cur.e)) {
				union(cur.s, cur.e);
				ans += cur.c;
				++size;
			}
		}
		
		System.out.println(ans);
		br.close();
	}
	
	static int find(int x) {
		if(p[x]==x) return x;
		else return p[x] = find(p[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) return;
		
		if(x>y) p[y] = x;
		else p[x] = y;
	}
	
	static class Node implements Comparable<Node> {
		int s, e, c;
		
		public Node(int s, int e, int c) {
			this.s = s; this.e = e; this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}
	}
}
