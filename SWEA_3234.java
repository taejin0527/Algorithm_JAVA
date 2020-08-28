package d0828;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 8. 28 
 * @author : "AoN"

 * @Description : 3234. 준환이의 양팔저울
 * @Link : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw&categoryId=AWAe7XSKfUUDFAUw&categoryType=CODE
 * 
 * @Note : Memoization을 사용하면 전체적인 수행시간이 많이 줄어든다(맨 아래 답안 코드 참고)
 * 
 */

public class SWEA_3234 {
	
	private int N;
	private int[] arr;
	private int[][] dp;
	
	private void run() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=1; tc<=T; ++tc) {
			N = sc.nextInt();
			
			arr = new int[N];
			int weightSum = 0;
			for(int i=0; i<N; ++i) {
				weightSum += arr[i] = sc.nextInt();
			}
			
			dp = new int[weightSum+1][1<<N];
			int ans = dfs(0, 0, 0, 0);
			System.out.println("#" + tc + " " + ans);
		}
		
		sc.close();
	}
	
	private int dfs(int v, int cnt, int left, int right) {
		if(left < right) return 0;
		if(cnt == N) return 1;
		if(dp[left][v] != 0) return dp[left][v];
		
		int sum=0;
		for(int i=0; i<N; ++i) {
			if((v&1<<i) != 0) continue;
			
			// put weight on left
			sum += dfs(v|(1<<i), cnt+1, left + arr[i], right);
			// put weight on right(if conditions are met)
			sum += dfs(v|(1<<i), cnt+1,left, right+arr[i]);
		}
		
		return dp[left][v] = sum;
	}
	
	public static void main(String[] args) {
		new SWEA_3234().run();
	}
}

/**
public class Solution {
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static StringBuilder output = new StringBuilder();
static StringTokenizer tokens;

static int T, N;
private static int[] weights;

private static int[][] memo;

public static void main(String[] args) throws IOException {

    T = Integer.parseInt(input.readLine());
    for (int t = 1; t <= T; t++) {
        N = Integer.parseInt(input.readLine());
        weights = new int[N];
        tokens = new StringTokenizer(input.readLine(), " ");

        int weightSum = 0;
        for (int i = 0; i < N; i++) {
            weightSum += weights[i] = Integer.parseInt(tokens.nextToken());
        }
        memo = new int[weightSum + 1][(1 << N)];

        int answer = solve(N, 0, 0, 0);

        output.append('#').append(t).append(' ').append(answer).append('\n');
    }

    System.out.print(output);
}

private static int solve(final int toChoose, final int left, final int right, final int visit) {
    if (left < right) {
        return 0;
    }

    if (memo[left][visit] != 0) {
        return memo[left][visit];
    }

    if (toChoose == 0) {
        return memo[left][visit] = 1;
    }

    int cntSum = 0;

    for (int i = 0; i < N; i++) {
        if ((visit & (1 << i)) == 0) {
            cntSum += solve(toChoose - 1, left + weights[i], right, visit | (1 << i));
            cntSum += solve(toChoose - 1, left, right + weights[i], visit | (1 << i));
        }
    }
    return memo[left][visit] = cntSum;
}

}
*/