package d0906;

import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 6 
 * @author : "AoN"

 * @Description : BOJ 1219 - 오민식의 고민
 * @Link : https://www.acmicpc.net/problem/1219
 * 
 */

public class BOJ_1219 {
	
	private static class City {
		int s, e, p;
		
		public City(int s, int e, int p) {
			this.s=s; this.e=e; this.p=p;
		}
	}
	
	private static int N, M, S, E;
	private static City[] cities;
	private static int[] money;
	private static long[] dist;
	
	private static final long INIT = Long.MIN_VALUE;
	private static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		S = sc.nextInt();
		E = sc.nextInt();
		M = sc.nextInt();
		
		cities = new City[M];
		for(int i=0; i<M; ++i) {
			cities[i] = new City(sc.nextInt(), sc.nextInt(), -sc.nextInt());
		}
		
		money = new int[N];
		for(int i=0; i<N; ++i) {
			money[i] = sc.nextInt();
		}
		
		bellmanFord();
		
		if (dist[E] == INIT) System.out.println("gg");
        else if (dist[E] == INF) System.out.println("Gee");
        else System.out.println(dist[E]);
		
		sc.close();
	}
	
	private static void bellmanFord() {
		dist = new long[N];
		Arrays.fill(dist, INIT);
		dist[S] = money[S];
		
		for(int i=0; i<N+100; ++i) {
			for(int j=0; j<M; ++j) {
				if(dist[cities[j].s] == INIT) continue;
				else if(dist[cities[j].s] == INF) dist[cities[j].e] = INF;
				else if(dist[cities[j].e] < dist[cities[j].s] + cities[j].p + money[cities[j].e]) {
					dist[cities[j].e] = dist[cities[j].s] + cities[j].p + money[cities[j].e];
					if(i >= N-1) dist[cities[j].e]= INF; 
				}
			}
		}
	}
}
