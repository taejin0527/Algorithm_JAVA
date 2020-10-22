import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 22 
 * @author : "AoN"

 * @Description : BOJ 2211 - 네트워크 복구
 * @Link : https://www.acmicpc.net/problem/2211
 * 
 */

public class BOJ_2211 {
	
	static int N, M;
	static List<Computer> network[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		network = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) network[i] = new ArrayList<>();
		for(int i=0; i<M; ++i) {
			String[] abt = br.readLine().split(" ");
			int a = Integer.parseInt(abt[0]);
			int b = Integer.parseInt(abt[1]);
			int t = Integer.parseInt(abt[2]);
			
			network[a].add(new Computer(b, t));
			network[b].add(new Computer(a, t));
		}
		
		dijkstra();
		
		br.close();
	}
	
	static void dijkstra() {
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		int minT[] = new int[N+1];
		int conn[] = new int[N+1];
		Arrays.fill(minT, Integer.MAX_VALUE);
		
		pq.offer(new Computer(1, 0));
		minT[1] = 0;
		
		while(!pq.isEmpty()) {
			Computer cur = pq.poll();
			
			if(minT[cur.b] > cur.t) continue;
			
			for(int i=0; i<network[cur.b].size(); ++i) {
				Computer nxt = network[cur.b].get(i);
				
				if(minT[nxt.b] > minT[cur.b]+ nxt.t) {
					minT[nxt.b] = minT[cur.b]+ nxt.t;
					conn[nxt.b]= cur.b; 
					pq.offer(new Computer(nxt.b, minT[nxt.b]));
				}
			}
		}
		
		System.out.println(N-1);
		for(int i=2; i<=N; ++i) {
			System.out.println(i + " " + conn[i]);
		}
	}
	
	static class Computer implements Comparable<Computer> {
		int b, t;
		
		public Computer(int b, int t) {
			this.b = b; this.t = t;
		}

		@Override
		public int compareTo(Computer o) {
			return this.t - o.t;
		}
		
	}
}
