package d0917;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 17 
 * @author : "AoN"

 * @Description : BOJ 2999 - 비밀 이메일
 * @Link : https://www.acmicpc.net/problem/2999
 * 
 */

public class BOJ_2999 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String pwd = sc.next();
		
		int N = pwd.length();
		int R=0;
		for(int i=1; i*i <= N; ++i) {
			if(N%i==0) R = i;
		}
		int C = N/R;
		if (C > R) {
			int temp = R;
			R = C;
			C = temp;
		}
		char[][] encoding = new char[R][C];
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				encoding[i][j] = pwd.charAt(i*C + j);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int j=0; j<C; ++j) {
			for(int i=0; i<R; ++i) {
				sb.append(encoding[i][j]);
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}
