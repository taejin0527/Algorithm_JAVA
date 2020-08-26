import java.util.Scanner;

/**
 * @FileName : BOJ_1010.java
 * @Project : 0825
 * @Date : 2020. 8. 25 
 * @author : "AoN"

 * @Description : 다리 놓기
 * 다리끼리는 서로 겹쳐질 수 없다는 조건이 있어서 순열이 아닌 조합 문제이다
 * 또한 DP로 풀어야지 시간 제한에 걸리지 않는다
 * @Link : https://www.acmicpc.net/problem/1010
 * 
 */

public class BOJ_1010 {
	
	private int N, M;
	private int[][] dp = new int[31][31];	// N, M 이 최대 30까지
	
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int m=1; m<31; ++m) {
			for(int n=0; n<=m; ++n) {
				if(n == 0 || m == n ){
                    dp[m][n] = 1;
                } else {
                    dp[m][n] = dp[m - 1][n - 1] + dp[m - 1][n];
                }
			}
		}
		while(T-- > 0) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			System.out.println(dp[M][N]);
		}
		
		sc.close();
	}
	
	public static void main(String[] args) {
		new BOJ_1010().solution();
	}
}
