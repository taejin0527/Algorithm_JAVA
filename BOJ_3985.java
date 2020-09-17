package d0917;
/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 17 
 * @author : "AoN"

 * @Description : BOJ 3985 - 롤 케이크
 * @Link : https://www.acmicpc.net/problem/3985
 * 
 */

import java.util.Scanner;

public class BOJ_3985 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int L = sc.nextInt();
		int N = sc.nextInt();
		
		int cake[] = new int[L];
		int maxCnt = 0, maxExp = 0, ans1 = 0, ans2 = 0;
		for(int i=1; i<=N; ++i) {
			int P = sc.nextInt() - 1;
			int K = sc.nextInt() - 1;
			int cnt = 0, exp = 0;
			
			for(int j=P; j<=K; ++j) {
				if(j >= L) break;
				++exp;
				if(cake[j] == 0) {
					cake[j] = i; ++cnt;
				}
			}
			if(maxExp < exp) {
				maxExp = exp;
				ans1 = i;
			}
			if(maxCnt < cnt) {
				maxCnt = cnt;
				ans2 = i;
			}
		}
		
		System.out.println(ans1);
		System.out.println(ans2);
		sc.close();
	}
}
