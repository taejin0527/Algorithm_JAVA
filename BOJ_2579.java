import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 12 
 * @author : "AoN"

 * @Description : BOJ 2579 - 계단 오르기
 * @Link : https://www.acmicpc.net/problem/2579
 * 
 */

public class BOJ_2579 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int score[] = new int[N+1];
		for(int i=1; i<=N; ++i) {
			score[i] = sc.nextInt();
		}
		
		int dp[] = new int[N+1];
		dp[1] = score[1];
		if(N>=2) dp[2] = dp[1] + score[2];
		for(int i=3; i<=N; ++i) {
			dp[i] = Math.max(dp[i-3] + score[i] + score[i-1], dp[i-2] + score[i]);
		}
		
		System.out.println(dp[N]);
		
		sc.close();
	}
}
