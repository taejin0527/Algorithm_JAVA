import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 4 
 * @author : "AoN"

 * @Description : BOJ 7579 - 앱
 * @Link : https://www.acmicpc.net/problem/7579
 * 
 */

public class BOJ_7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        
        App[] apps = new App[N];
        StringTokenizer m = new StringTokenizer(br.readLine());
        StringTokenizer c = new StringTokenizer(br.readLine());
        
        int maxCost = 0;
        for(int i = 0; i < N; ++i){
            apps[i] = new App(Long.parseLong(m.nextToken()), Integer.parseInt(c.nextToken()));
            maxCost += apps[i].c;
        }
        
        long[] dp = new long[maxCost + 1];
        for(int i = 0; i < N; i++){
            for(int j = maxCost; j >= apps[i].c; --j){
                dp[j] = Math.max(dp[j], dp[j - apps[i].c] + apps[i].m);
            }
        }
        
        for(int i = 0; i <= maxCost; ++i){
            if(dp[i] >= M){
                System.out.println(i);
                break;
            }
        }

        br.close();

    }

    static class App{
        long m;
        int c;
        
        public App(long m, int c){
            this.m = m;
            this.c = c;
        }
	}
}
