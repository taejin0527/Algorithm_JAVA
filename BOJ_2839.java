import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 11 
 * @author : "AoN"

 * @Description : BOJ 2839 - 설탕 배달
 * @Link : https://www.acmicpc.net/problem/2839
 * 
 */

public class BOJ_2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// 3kg, 5kg bag
		int bag[] = {3, 5};
		int W[] = new int[N+1];
		Arrays.fill(W, 5000);
		W[0] = 0;
		for(int i=1; i<=N; ++i) {
			for(int j=0; j<2; ++j) {
				int idx = i - bag[j];
				if(idx < 0) continue;
				if(W[idx] == 5000) continue;

				W[i] = Math.min(W[i], W[idx]+1);
			}
		}

		System.out.println(W[N] == 5000 ? -1 : W[N]);
		sc.close();
	}
}
