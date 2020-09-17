package d0917;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 17 
 * @author : "AoN"

 * @Description : BOJ 11399 - ATM
 * @Link : https://www.acmicpc.net/problem/11399
 * 
 */

public class BOJ_11399 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int P[] = new int[N];
		for(int i=0; i<N; ++i) {
			P[i] = sc.nextInt();
		}
		
		Arrays.sort(P);
		
		int total = 0;
		for(int i=0; i<N; ++i) {
			total += P[i] * (N-i);
		}
		
		System.out.println(total);
		sc.close();
	}
}
