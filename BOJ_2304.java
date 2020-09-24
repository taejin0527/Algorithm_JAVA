package IM;

import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 24 
 * @author : "AoN"

 * @Description : BOJ 2304 - 창고 다각형
 * @Link : https://www.acmicpc.net/problem/2304
 * 
 */

public class BOJ_2304 {
	
	static int N, maxH;
	static PriorityQueue<Building> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			pq.add(new Building(L, H));
		}
		
		Building cur = pq.poll();
		int area = cur.H;
		int right = cur.L, left = cur.L;
		while(!pq.isEmpty()) {
			Building nxt = pq.poll();
			if(nxt.L < right) {
				area += (right - nxt.L)*nxt.H;
				right = nxt.L;
			} else if(nxt.L > left) {
				area += (nxt.L - left)*nxt.H;
				left= nxt.L;
			}
		}
		
		System.out.println(area);
		br.close();
	}
	
	static class Building implements Comparable<Building> {
		int L, H;
		
		public Building(int L, int H) {
			this.L = L; this.H = H;
		}
		
		@Override
		public int compareTo(Building o) {
			return o.H - this.H;
		}
	}
}


