import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 27 
 * @author : "AoN"

 * @Description : BOJ 11403 - 경로 찾기
 * @Link : https://www.acmicpc.net/problem/11403
 *  
 */

public class BOJ_11403 {
	
	static int N;
	static int graph[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0; k<N; ++k) {
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(graph[i][k] + graph[k][j] > 1) {
						graph[i][j] = 1;
					}
				}
			}
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(graph[i][j] + " ");
			}System.out.println();
		}
		
		br.close();
	}
	

}
