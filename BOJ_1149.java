import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 12 
 * @author : "AoN"

 * @Description : BOJ 1149 - RGB거리
 * @Link : https://www.acmicpc.net/problem/1149
 * 
 */

public class BOJ_1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int house[][] = new int[N][3];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			house[i][0] = Integer.parseInt(st.nextToken());
			house[i][1] = Integer.parseInt(st.nextToken());
			house[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int dp[][] = new int[N][3];
		for(int i=0; i<3; ++i) dp[0][i] = house[0][i];
		for(int i=1; i<N; ++i) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + house[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + house[i][1];
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + house[i][2];
		}
		
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
		
		br.close();
	}
}
