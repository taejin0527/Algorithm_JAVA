import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 15 
 * @author : "AoN"

 * @Description : BOJ 1890 - 점프
 * @Link : https://www.acmicpc.net/problem/1890
 * 
 */

public class BOJ_1890 {
	
	static int N;
	static int board[][];
	static boolean v[][];
	static long dp[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		board= new int[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				board[i][j] = sc.nextInt();
			}
		}
		
		v = new boolean[N][N];	v[0][0] = true;
		dp = new long[N][N];	dp[0][0] = 1;
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(v[i][j])
					jump(i, j);
			}
		}
		
		System.out.println(dp[N-1][N-1]);
		sc.close();
	}
	
	static void jump(int x, int y) {
		if(x == N-1 && y == N-1) return;
		
		int p = board[x][y];
		if(x + p < N) {
			dp[x+p][y] += dp[x][y];
			v[x+p][y] = true;
		}
		
		if(y + p < N) {
			dp[x][y+p] += dp[x][y];
			v[x][y+p] = true;
		}
	}
}
