import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 25 
 * @author : "AoN"

 * @Description : BOJ 1309 - 동물원
 * @Link : https://www.acmicpc.net/problem/1309
 * 
 */

public class BOJ_1309 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int dp[][] = new int[N+1][3];
		for(int i=0; i<3; ++i) dp[1][i] = 1;
		
		int mod = 9901;
		for(int i=2; i<=N; ++i) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % mod;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % mod;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		
		int total = 0;
		for(int i=0; i<3; ++i) total += dp[N][i];
		
		System.out.println(total % 9901);
		sc.close();
	}
}
