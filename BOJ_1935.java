import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 12 
 * @author : "AoN"

 * @Description : BOJ 1932 - 정수 삼각형
 * @Link : https://www.acmicpc.net/problem/1932
 * 
 */

public class BOJ_1935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int tree[][] = new int[n+1][n+1];
		for(int i=1; i<=n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<=i; ++j) {
				tree[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dp[][] = new int[n+1][n+1];
		dp[1][1] = tree[1][1];
		for(int i=2; i<=n; ++i) {
			for(int j=1; j<=i; ++j) {
				if(j==1) {
					dp[i][j] = dp[i-1][j] + tree[i][j];
				}else if(j==i){
					dp[i][j] = dp[i-1][j-1] + tree[i][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + tree[i][j];
				}
			}
		}
		
		int ans = 0;
		for(int i=1; i<=n; ++i) {
			ans = Math.max(ans, dp[n][i]);
		}
		
		System.out.println(ans);
		br.close();
	}
}
