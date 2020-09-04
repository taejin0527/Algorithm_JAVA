package d0904;

import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 4 
 * @author : "AoN"

 * @Description : BOJ 8979 - 올림픽
 * @Link : https://www.acmicpc.net/problem/8979
 * 
 */

public class BOJ_8979 {
	static class Country implements Comparable<Country>{
		int id, gold, silver, bronze, rank;

		public Country(int id, int gold, int silver, int bronze) {
			this.id = id;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

		@Override
		public int compareTo(Country o) {
			if(this.gold == o.gold) {
				if(this.silver == o.silver) {
					return o.bronze - this.bronze;
				}
				return o.silver - this.silver;
			}
			return o.gold - this.gold;
		}
	}
	
	private static int N, K;
	private static PriorityQueue<Country> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			
			pq.offer(new Country(id, gold, silver, bronze));
		}
		
		olympic();
		
		br.close();
	}
	
	private static void olympic() {
		int rank = 1;
		
		Country pre = pq.poll();
		pre.rank = rank;
		if(pre.id == K) {
			System.out.println(pre.rank);
			return;
		}
		rank=2;
		while(!pq.isEmpty()) {
			Country nxt = pq.poll();
			
			if(pre.gold == nxt.gold && pre.silver == nxt.silver && pre.bronze == nxt.bronze) {
				nxt.rank = pre.rank;
			}else {
				nxt.rank = rank;
			}
			if(nxt.id == K) {
				System.out.println(nxt.rank);
				return;
			}
			pre = nxt;
			++rank;
		}
	}
}
