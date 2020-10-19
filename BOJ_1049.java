import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 19 
 * @author : "AoN"

 * @Description : BOJ 1049 - 기타줄
 * @Link : https://www.acmicpc.net/problem/1049
 * 
 */

public class BOJ_1049 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Gstring> brand = new ArrayList<>();
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			brand.add(new Gstring(p, s));
		}
		
		Gstring minPack = brand
				.stream()
				.min(Comparator.comparing(Gstring::getP))
				.get();
		
		Gstring minSingle = brand
				.stream()
				.min(Comparator.comparing(Gstring::getS))
				.get();
		
		
		int cost = (N/6) * minPack.p + (N%6) * minSingle.s;
		cost = Math.min(cost, (N/6 + 1) * minPack.p);
		cost = Math.min(cost, N * minSingle.s);
		
		System.out.println(cost);
		br.close();
	}
	
	static class Gstring {
		int p, s;	// 패키지, 낱개, 낱개*6개
		
		public Gstring(int p, int s) {
			this.p = p; this.s = s;
		}

		public int getP() {	return p; }		
		public void setP(int p) { this.p = p; }
		public int getS() {	return s; }
		public void setS(int s) { this.s = s; }
	}
}
