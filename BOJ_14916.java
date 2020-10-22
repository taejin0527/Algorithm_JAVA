import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 22 
 * @author : "AoN"

 * @Description : BOJ 14916 - 거스름돈
 * @Link : https://www.acmicpc.net/problem/14916
 * 
 */

public class BOJ_14916 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int dp[] = new int[n+1];
		Arrays.fill(dp, (int) 1e5);
		
		dp[0] = 0;
		for(int i=1; i<=n; ++i) {
			if(i>=2) dp[i] = Math.min(dp[i], dp[i-2]+1);
			if(i>=5) dp[i] = Math.min(dp[i], dp[i-5]+1);
		}
		
		System.out.println(dp[n] == (int) 1e5 ? -1 : dp[n]);
		sc.close();
	}
}
