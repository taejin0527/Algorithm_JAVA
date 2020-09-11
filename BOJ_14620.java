package d0908;

import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 9 
 * @author : "AoN"

 * @Description : BOJ 14620 - 꽃길
 * @Link : https://www.acmicpc.net/problem/14620
 * 
 */

public class BOJ_14620 {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] map;
	static List<Seed> candidates, seeds;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		candidates = new ArrayList<>();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				map[i][j] = sc.nextInt();
				
				if(0 < i && i < N-1 && 0 < j && j < N-1) candidates.add(new Seed(i,j)); 
			}
		}

		seeds = new ArrayList<>();
		plantSeed(0);
		
		System.out.println(ans);
		sc.close();
	}
	
	static void plantSeed(int cnt) {
		if(seeds.size() == 3) {
			totalCost();
			return;
		}
		
		if(cnt == candidates.size()) return;
		
		
		seeds.add(candidates.remove(cnt));
		plantSeed(cnt);
		candidates.add(cnt, seeds.remove(seeds.size()-1));
		plantSeed(cnt+1);
	}
	
	static void totalCost() {
		Seed A = seeds.get(0);
		Seed B = seeds.get(1);
		Seed C = seeds.get(2);
		
		if(!isOK(A, B) || !isOK(B, C) || !isOK(C, A)) return;
		
		int cost = 0;
		cost += map[A.x][A.y] + map[A.x-1][A.y] + map[A.x][A.y-1] + map[A.x+1][A.y] + map[A.x][A.y+1] ;
		cost += map[B.x][B.y] + map[B.x-1][B.y] + map[B.x][B.y-1] + map[B.x+1][B.y] + map[B.x][B.y+1] ;
		cost += map[C.x][C.y] + map[C.x-1][C.y] + map[C.x][C.y-1] + map[C.x+1][C.y] + map[C.x][C.y+1] ;

		ans = Math.min(ans, cost);
	}
	
	static boolean isOK(Seed a, Seed b) {
		return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= 2) ? false : true;
	}
	
	static class Seed {
		int x, y;
		public Seed(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
