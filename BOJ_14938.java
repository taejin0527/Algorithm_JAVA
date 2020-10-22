import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 22 
 * @author : "AoN"

 * @Description : BOJ 14938 - 서강그라운드
 * @Link : https://www.acmicpc.net/problem/14938
 * 
 */

public class BOJ_14938 {
	
	static int n, m, r; // 지역 개수, 수색범위, 길의 개수
	static List<Integer> item;
	static int graph[][];
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 첫 번째 줄
		int[] nmr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = nmr[0];
		m = nmr[1];
		r = nmr[2];
		
		// 두 번째 줄
		item = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
		
		// 세 번째 줄부터 r+2번째 줄
		graph = new int[n+1][n+1];
		for(int i=0; i<=n; ++i) Arrays.fill(graph[i], (int) 1e9);
		for(int i=0; i<=n; ++i) graph[i][i] = 0;
		
		for(int i=0; i<r; ++i) {
			int[] abl = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// a: 지역번호, b: 지역번호, l: 길의 길이
			graph[abl[0]][abl[1]] = abl[2];
			graph[abl[1]][abl[0]] = abl[2];
		}
		
		floydWarshall();
		
//		for(int i=1; i<=n; ++i) {
//			for(int j=1; j<=n; ++j) {
//				System.out.print(dp[i][j] == (int) 1e9 ? "I " : dp[i][j] + " ");
//			}System.out.println();
//		}
		
		int maxRoot = 0;
		for(int i=1; i<=n; ++i) {
			int root = 0;
			
			for(int j=1; j<=n; ++j) {
				if(dp[i][j] != (int) 1e9 && dp[i][j] <= m) {
					root += item.get(j-1);
				}
			}
			maxRoot = Math.max(maxRoot, root);
		}
		
		System.out.println(maxRoot);
		
		br.close();
	}
	
	static void floydWarshall() {
		dp = new int[n+1][n+1];
		
		for(int i=0; i<=n; ++i) Arrays.fill(dp[i], (int) 1e9);
		for(int i=0; i<=n; ++i) dp[i][i] = 0;
		
		for(int i=1; i<=n; ++i) {
			for(int j=1; j<=n; ++j) {
				dp[i][j] = graph[i][j];
			}
		}

		for(int i=1; i<=n; ++i) { // 경유지
			for(int j=1; j<=n; ++j) { // 출발
				for(int k=1; k<=n; ++k) { // 도착
					if(dp[j][k] > dp[j][i] + dp[i][k]) {
						dp[j][k] = dp[j][i] + dp[i][k];
					}
				}
			}
		}
	}

}
