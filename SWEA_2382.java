package d0908;

import java.io.*;
import java.util.*;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 9. 8 
 * @author : "AoN"

 * @Description : 2382. [모의 SW 역량테스트] 미생물 격리
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl&categoryId=AV597vbqAH0DFAVl&categoryType=CODE
 * 
 */

public class SWEA_2382 {
	
	static BufferedReader br;
	static int N, M, K;
	static Queue<Microbe> q;
	
	static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; ++tc) {
			getInput();
			simulation();
			
			int ans = 0;
			while(!q.isEmpty()) ans += q.poll().cnt;
			output.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(output);
		br.close();
	}
	
	static void getInput() throws IOException {
		String[] row = br.readLine().split(" ");
		N = Integer.parseInt(row[0]);
		M = Integer.parseInt(row[1]);
		K = Integer.parseInt(row[2]);
		
		q = new LinkedList<>();
		for(int i=0; i<K; ++i) {
			row = br.readLine().split(" ");
			int x = Integer.parseInt(row[0]);
			int y = Integer.parseInt(row[1]);
			int c = Integer.parseInt(row[2]);
			int d = Integer.parseInt(row[3]);
			
			q.offer(new Microbe(x, y, c, d));
		}
	}
	
	static void simulation() {
		while(M-- > 0) {
			int size = q.size();
			PriorityQueue<Microbe> pq = new PriorityQueue<>();
			
			for(int i=0; i<size; ++i) {
				Microbe cur = q.poll();
				
				cur.move();
				pq.offer(cur);
			}
			
			Microbe cur = pq.poll();
			while(!pq.isEmpty()) {
				Microbe adj = pq.poll();
				
				if(cur.x == adj.x && cur.y == adj.y) {
					cur.add(adj);
				}else {
					cur.maxCnt = cur.cnt;
					q.offer(cur);
					cur = adj;
				}
			}
			
			cur.maxCnt = cur.cnt;
			q.offer(cur);
		}// end while
	}
	
	static class Microbe implements Comparable<Microbe>{
		int x, y, cnt, dir, maxCnt;

		public Microbe(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
			this.maxCnt = cnt;
		}
		
		public void move() {
			//System.out.println(x + " " + y + " " + cnt + " " + dir);
			x = x + dx[dir];
			y = y + dy[dir];
			
			
			if(x == 0 || x == N-1 || y == 0 || y == N-1) {
				cnt /= 2;
				dir = dir%2 == 0 ? dir-1 : dir+1;
			}
		}
		
		public void add(Microbe o) {
			if(this.maxCnt < o.cnt) {
				this.maxCnt = o.cnt;
				this.dir = o.dir;
			}
			this.cnt += o.cnt;
		}

		@Override
		public int compareTo(Microbe o) {
			return this.x == o.x ? this.y - o.y : this.x - o.x;
		}
		
	}
}
