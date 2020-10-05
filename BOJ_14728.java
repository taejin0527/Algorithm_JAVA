import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 4 
 * @author : "AoN"

 * @Description : BOJ 14728 - 벼락치기
 * @Link : https://www.acmicpc.net/problem/14728
 * 
 */

public class BOJ_14728 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();	// 단원 개수
		int T = sc.nextInt();	// 총 시간
		
		
		Question q[] = new Question[N+1];
		for(int i=1; i<=N; ++i) {
			int K = sc.nextInt();	// 공부 시간
			int S = sc.nextInt();	// 문제 배점
			
			q[i] = new Question(K, S);
		}
		
		int d[] = new int[T+1];
		for(int i=1; i<=N; ++i) {
			for(int j=T; j>=q[i].k; --j) {
				d[j] = Math.max(d[j], d[j - q[i].k] + q[i].s);
			}
		}
		
		System.out.println(d[T]);
		
		sc.close();
	}
	
	static class Question {
		int k, s;
		
		public Question(int k, int s) {
			this.k = k;
			this.s = s;
		}
	}
}
